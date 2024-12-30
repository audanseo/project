package com.ezen.massagemall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ezen.massagemall.admin.product.AdProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {

	private final AdProductService adProductService;

	@GetMapping("/")
	public String home(@ModelAttribute("cate_name") String cate_name, Integer cate_code, Model model) {

		// 1차카테고리 목록
		model.addAttribute("cate_list", adProductService.getFirstCategoryList());

		return "index";
	}
}
