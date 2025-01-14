package com.ezen.massagemall.admin.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.massagemall.admin.utils.SearchCriteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/admin/order/*")
@RequiredArgsConstructor
@Slf4j
@Controller
public class AdOrderController {

	private final AdOrderService adOrderService;

	@GetMapping("/order_list")
	public void order_list(SearchCriteria cri, Model model) throws Exception {

	}
}
