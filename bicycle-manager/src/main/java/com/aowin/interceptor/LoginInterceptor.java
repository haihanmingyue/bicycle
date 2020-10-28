package com.aowin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor{
	
	/**
	 * 预处理程序 调用controller方法之前
	 * 返回false 不继续执行
	 * 返回true 继续执行
	 */
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object user = request.getSession().getAttribute("syuser");
		if(user == null) {
			//未登录
			response.sendRedirect(request.getContextPath()+"/index.html");
			return false;
		}
		return true;
	}

}
