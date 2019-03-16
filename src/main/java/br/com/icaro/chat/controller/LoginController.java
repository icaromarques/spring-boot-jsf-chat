package br.com.icaro.chat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.icaro.chat.model.ChatUser;
import br.com.icaro.chat.service.ChatUserService;
import br.com.icaro.chat.service.EmailService;

@Scope (value = "session")
@Component (value = "loginController")
@ELBeanName(value = "loginController")
@Join(path = "/", to = "/login.jsf")
public class LoginController {

	@Autowired
	ChatUserService userService;

	@Autowired EmailService sendGridEmailService;
	
	@Email(message = "Digite um e-mail válido")
	private String email;
	
	@CPF
	private String cpf;

	private ChatUser user;

	public ChatUser getUser() {
		return user;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String login() throws Exception {
		user = userService.findByEmailAndCpf(email, cpf);
		if (user != null) {
			user.setOnline(true);
			user = userService.insertOrUpdate(user);			
			
		} else {
			ChatUser newUser = new ChatUser(email, cpf, true);
			user = userService.insertOrUpdate(newUser);
			
			enviarEmail();
			
		}
		
		return "/principal/chat.jsf?faces-redirect=true";
	}

	public String isLoggedInForwardHome() {

		if (this.user != null && this.user.getOnline())
			return "/principal/chat.jsf?faces-redirect=true";
		else
			return "/login.jsf?faces-redirect=true";

	}

	public String logout() throws Exception{
		user.setOnline(false);
		userService.insertOrUpdate(user);

		user = null;

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/login.jsf?faces-redirect=true";
	}

	
	private void enviarEmail(){
		  new Thread() {
		     
		    @Override
		    public void run() {
		    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    	
		    	sendGridEmailService.sendHTML("no-reply@icarochat.com", user.getEmail(), "Bem vindo, "+ getEmail(), 
						 "Olá, <strong>"+ user.getEmail() +"</strong> <br/>"
						 + "Você acaba de se cadastrar no serviço de chat. <br/>"
						 + "<strong> Usuário: </strong> "+ user.getEmail() +"<br/>" 
						 + "<strong> CPF: </strong> "+ user.getCpf() +"<br/><br/>"
						 + "<strong> IP: </strong> "+getEnderecoIP() +"<br/>" 
						 + "<strong> Hora: </strong> "+sdf.format(new Date()) +"<br/>"   );
		       
		    }
		  }.start();
		 
		}
	
	
	
	private String getEnderecoIP() {
		 String ipAddress = "";
		try {
			
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		    
		 ipAddress = request.getHeader("x-forwarded-for");
		  if (ipAddress == null) {
		      ipAddress = request.getHeader("X-FORWARDED-FOR");
		      if (ipAddress == null){
		          ipAddress = request.getRemoteAddr();
		      }
		  }	  
		} catch (Exception e) {
			// TODO: handle exception
		} 
	       
		return ipAddress;
	}

}
