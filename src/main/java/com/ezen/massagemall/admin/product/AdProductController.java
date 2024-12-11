package com.ezen.massagemall.admin.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.massagemall.category.AdCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/admin/product/*")
@Slf4j
@Controller
public class AdProductController {

	private final AdCategoryService adCategoryService;

	@GetMapping("/pro_insert")
	public void pro_insert(Model model) {

		// List<CategoryVO> getFirstCategoryList =
		// adCategoryService.getFirstCategoryList();

		model.addAttribute("cate_list", adCategoryService.getFirstCategoryList());
	}
}
