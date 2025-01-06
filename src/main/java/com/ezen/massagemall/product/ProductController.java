package com.ezen.massagemall.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.massagemall.admin.product.AdProductService;
import com.ezen.massagemall.admin.product.ProductVO;
import com.ezen.massagemall.admin.utils.FileUtils;
import com.ezen.massagemall.admin.utils.SearchCriteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/product/*")
@RequiredArgsConstructor
@Controller
public class ProductController {

	private final ProductService productService;
	private final AdProductService adProductService;
	private final FileUtils fileUtils;

	@Value("${com.ezen.upload.path}")
	private String uploadPath;

	@Value("${com.ezen.upload.ckeditor.path}")
	private String uploadCKPath;

	// 상품리스트 추가
	@GetMapping("/pro_list")
	public void pro_list(SearchCriteria cri, @ModelAttribute("cate_name") String cate_name, Integer cate_code,
			Model model) throws Exception {

		log.info("카테고리 명 " + cate_name);

		// 1차 카테고리
		model.addAttribute("cate_list", adProductService.getFirstCategoryList());

		// 2차 카테고리
		model.addAttribute("pro_list", productService.getProductListBysecondCategory(cri, cate_code));

	}

	// pro_info?pro_name=파라오&pro_num=9
	// 상품 상세페이지
	@GetMapping("/pro_info")
	public void pro_info(@ModelAttribute("pro_name") String pro_name, Integer pro_num, Model model) throws Exception {
		// log.info("카테고리명" + pro_name);

		model.addAttribute("cate_list", adProductService.getFirstCategoryList());

		ProductVO productVO = productService.pro_info(pro_num);

		// 이미지파일의 날짜폴더\를 /변환작업
		productVO.setPro_upfolder(productVO.getPro_upfolder().replace("\\", "/"));
		model.addAttribute("productVO", productVO);

	}

	// 이미지 파일을 보낼때는 바이트로 해줘야한다
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {

		return fileUtils.getFile(uploadPath + "\\" + dateFolderName, fileName);
	}
}
