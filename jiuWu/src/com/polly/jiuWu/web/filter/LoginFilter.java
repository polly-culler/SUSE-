package com.polly.jiuWu.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		/*
		 * 1. 获取session中的user
		 * 2. 判断是否为null
		 *   > 如果为null：保存错误信息，转发到msg.jsp
		 *   > 如果不为null：放行
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		Object user = req.getSession().getAttribute("sessionUser");
		if(user == null) {
			req.setAttribute("code", "error");//为了显示X图片
			req.setAttribute("msg", "您还没有登录，不能访问本资源");
			req.getRequestDispatcher("/user/500.jsp").forward(req, response);
		} else {
			chain.doFilter(request, response);//放行
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
