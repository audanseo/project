package com.ezen.massagemall.interceptor;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ezen.massagemall.user.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	// 로그인 체크
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean result = false;

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("login_auth");

		if (userVO == null) {
			result = false;

			// ajax요청주소인지 구분하는 작업
			if (isAjaxRequest(request)) {
				String originalUrl = request.getRequestURI();
				String postData = getPostData(request);

				System.out.println("데이터" + postData);

				session.setAttribute("targetUrl", originalUrl);
				session.setAttribute("postData", postData);

				response.sendError(400);
			} else {
				getTargetUrl(request);
				response.sendRedirect("/user/login");
			}
		} else {
			result = true;
		}
		return result;
	}

	private String getPostData(HttpServletRequest request) throws IOException {

		StringBuilder postData = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			postData.append(line);
		}
		return postData.toString();
	}

	// ajax 체크
	private boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjax = false;

		String header = request.getHeader("AJAX");

		if (header != null && header.equals("true")) {
			isAjax = true;
		}
		return isAjax;
	}

	// 주소체크
	private void getTargetUrl(HttpServletRequest request) {

		String uri = request.getRequestURI();
		String query = request.getQueryString();

		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		String targetUrl = uri + query;

		if (request.getMethod().equals("GET")) {
			request.getSession().setAttribute("targetUrl", targetUrl);
		}
	}
}
