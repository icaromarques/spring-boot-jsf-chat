package br.com.icaro.chat.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.icaro.chat.controller.LoginController;

public class LoginFilter implements Filter {

	@Override
	  public void doFilter(ServletRequest servletRequest,
	      ServletResponse servletResponse, FilterChain filterChain)
	      throws IOException, ServletException {

	    HttpServletRequest httpServletRequest =
	        (HttpServletRequest) servletRequest;
	    HttpServletResponse httpServletResponse =
	        (HttpServletResponse) servletResponse;

	    // managed bean name is exactly the session attribute name
	    LoginController loginController = (LoginController) httpServletRequest.getSession().getAttribute("loginController");

	    if (loginController != null) {
	      if (loginController.getUser() != null && loginController.getUser().getOnline()) {
	        // user is logged in, continue request
	        filterChain.doFilter(servletRequest, servletResponse);
	      } else {
	        // user is not logged in, redirect to login page
	        httpServletResponse.sendRedirect(
	            httpServletRequest.getContextPath() + "/login.jsf");
	      }
	    } else {
	      // user is not logged in, redirect to login page
	      httpServletResponse.sendRedirect(
	          httpServletRequest.getContextPath() + "/login.jsf");
	    }
	  }

	  @Override
	  public void init(FilterConfig arg0) throws ServletException {
		  System.out.println("LoginFilter inicializado");
	  }

	  @Override
	  public void destroy() {
	    // close resources
	  }

}
