package com.ezen.massagemall.statistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/admin/statist/*")
@RequiredArgsConstructor
@Slf4j
@Controller
public class StatisticsController {

	private final StatisticsService statisticsService;
}
