package com.ezen.massagemall.admin.order;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.ezen.massagemall.admin.utils.SearchCriteria;

public interface AdOrderMapper {

	List<Map<String, Object>> order_list(SearchCriteria cri, Model model);
}
