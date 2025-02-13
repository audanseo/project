package com.ezen.massagemall.admin.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.massagemall.admin.AdminDto;
import com.ezen.massagemall.admin.utils.FileUtils;
import com.ezen.massagemall.admin.utils.PageMaker;
import com.ezen.massagemall.admin.utils.SearchCriteria;
import com.ezen.massagemall.review.ReviewReply;
import com.ezen.massagemall.review.ReviewService;
import com.ezen.massagemall.review.ReviewVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/review/*")
@Controller
public class AdReviewController {

	private final AdReviewService adReviewService;
	private final ReviewService reviewService;

	private final FileUtils fileUtils;

	@Value("${com.ezen.upload.path}")
	private String uploadPath;

	@GetMapping("/review_list")
	public void review_list(@ModelAttribute("cri") SearchCriteria cri, @ModelAttribute("rev_rate") String rev_rate,
			@ModelAttribute("rev_content") String rev_content, Model model) throws Exception {
		log.info("검색정보" + cri.toString());

		List<ReviewVO> review_list = adReviewService.review_list(cri, rev_rate, rev_content);

		review_list.forEach(reviewVO -> {
			reviewVO.getProduct().setPro_upfolder((reviewVO.getProduct().getPro_upfolder().replace("\\", "/")));
		});

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(adReviewService.review_count(cri, rev_rate, rev_content));

		System.out.println("hello world hello world" + review_list.size());
		model.addAttribute("review_list", review_list);
		model.addAttribute("pageMaker", pageMaker);
	}

	@GetMapping("/review_info/{rev_code}")
	public ResponseEntity<ReviewVO> review_info(@PathVariable("rev_code") Integer rev_code) throws Exception {
		ResponseEntity<ReviewVO> entity = null;

		ReviewVO review_info = reviewService.review_info(rev_code);

		log.info("" + review_info);

		entity = new ResponseEntity<ReviewVO>(review_info, HttpStatus.OK);
		return entity;
	}

	// 답변내용 저장
	@PostMapping(value = "/reply_insert", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> reply_insert(@RequestBody ReviewReply vo, HttpSession session) throws Exception {
		ResponseEntity<String> entity = null;

		log.info("상품후기답변" + vo);
		AdminDto adminDto = ((AdminDto) session.getAttribute("admin_auth"));
		vo.setManager_id(adminDto.getAd_userid());

		reviewService.reply_insert(vo);

		entity = new ResponseEntity<String>("success", HttpStatus.OK);

		return entity;
	}

	@GetMapping("/reply_info/{reply_id}")
	public ResponseEntity<ReviewReply> reply_info(@PathVariable("reply_id") Integer reply_id) throws Exception {
		ResponseEntity<ReviewReply> entity = null;

		entity = new ResponseEntity<ReviewReply>(adReviewService.reply_info(reply_id), HttpStatus.OK);
		return entity;
	}

	// 답변 수정
	@PostMapping(value = "/reply_modify", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> reply_modify(@RequestBody ReviewReply vo) throws Exception {
		ResponseEntity<String> entity = null;

		adReviewService.reply_modify(vo.getReply_id(), vo.getReply_text());
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		return entity;
	}

	// 답변삭제
	@DeleteMapping("/reply_delete/{reply_id}")
	public ResponseEntity<String> reply_delete(@PathVariable("reply_id") Integer reply_id) throws Exception {

		ResponseEntity<String> entity = null;

		adReviewService.reply_delete(reply_id);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		return entity;
	}

	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {

		return fileUtils.getFile(uploadPath + "\\" + dateFolderName, fileName);
	}
}
