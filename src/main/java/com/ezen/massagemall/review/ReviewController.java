package com.ezen.massagemall.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.massagemall.admin.utils.PageMaker;
import com.ezen.massagemall.admin.utils.SearchCriteria;
import com.ezen.massagemall.product.ProductService;
import com.ezen.massagemall.user.UserVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/review/*")
@Slf4j
@RestController
public class ReviewController {

	private final ReviewService reviewService;
	private final ProductService productService;

	@GetMapping("/rev_list/{pro_num}/{page}")
	public ResponseEntity<Map<String, Object>> rev_list(@PathVariable("pro_num") Integer pro_num,
			@PathVariable("page") int page) throws Exception {

		// url /review/rev_list/8/1
		// log.info("상품코드" + pro_num);
		// log.info("page" + page);
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<>();
		// 상품후기
		SearchCriteria cri = new SearchCriteria();
		cri.setPerPageNum(5);
		cri.setPage(page);

		List<ReviewVO> rev_list = reviewService.rev_list(pro_num, cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reviewService.getCountReviewByPro_num(pro_num));

		// key 값이 ajax 변수에서 참조
		map.put("rev_list", rev_list);
		map.put("pageMaker", pageMaker);

		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		return entity;
	}

	// 리뷰등록
	@PostMapping(value = "/review_save", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> review_save(@RequestBody ReviewVO vo, HttpSession session) throws Exception {

		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		vo.setMc_email(mc_email);

		log.info("상품리뷰" + vo);
		ResponseEntity<String> entity = null;
		// 상품 후기 등록
		reviewService.review_save(vo);

		return entity;
	}
}
