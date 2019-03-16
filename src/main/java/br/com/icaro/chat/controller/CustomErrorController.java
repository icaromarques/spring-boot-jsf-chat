package br.com.icaro.chat.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope (value = "session")
@Component (value = "customErrorController")
@ELBeanName(value = "customErrorController")
@Join(path = "/error", to = "/404.jsf")
public class CustomErrorController implements ErrorController{

	 @RequestMapping("/error")
	    public String handleError() {
	        //do something like logging
	        return "error";
	    }
	 
	    @Override
	    public String getErrorPath() {
	        return "/error";
	    }

	
	
}
