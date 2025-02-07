package com.ezen.massagemall.admin.review;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.massagemall.admin.utils.FileUtils;
import com.ezen.massagemall.admin.utils.PageMaker;
import com.ezen.massagemall.admin.utils.SearchCriteria;
import com.ezen.massagemall.review.ReviewService;

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

		List<Map<String, Object>> review_list = adReviewService.review_list(cri, rev_rate, rev_content);

		review_list.forEach(review_Info -> {
			review_Info.put("pro_upfolder", review_Info.get("pro_upfolder").toString().replace("\\", "/"));
		});

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(adReviewService.review_count(cri, rev_rate, rev_content));

		model.addAttribute("review_list", review_list);
		model.addAttribute("pageMaker", pageMaker);
	}

	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {

		return fileUtils.getFile(uploadPath + "\\" + dateFolderName, fileName);
	}
}
