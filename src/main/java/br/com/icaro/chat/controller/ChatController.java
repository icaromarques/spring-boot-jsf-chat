package br.com.icaro.chat.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.icaro.chat.config.MessageMode;
import br.com.icaro.chat.dto.Message;
import br.com.icaro.chat.model.ChatUser;
import br.com.icaro.chat.model.RestrictWord;
import br.com.icaro.chat.service.ChatUserService;
import br.com.icaro.chat.service.RestrictWordService;
import br.com.icaro.chat.service.WebSocketService;

@Scope ("view")
@Component (value = "chatController")
@ELBeanName(value = "chatController")
@Join(path = "/chat", to = "/principal/chat.jsf")
public class ChatController {

	private List<ChatUser> users;
	
	private String message;
	
	private String privateMessage;
	
	
	@Autowired
	ChatUserService userService;
	
	@Autowired
	RestrictWordService restrictWordService;
	
	@Autowired
	LoginController loginController;
	
	ChatUser toUser;
	
	@Autowired
	WebSocketService wsService;
	
	public String sendMessage() throws Exception  {		
		 
		String [] wordsFromText = message.split(" ");	
		 
		 if (badWordsValidation(wordsFromText)) {
		     Message chatMessage = new Message(null, loginController.getUser(), message, MessageMode.PUBLIC);
			 wsService.send("/topic/chat", chatMessage);
		 
		 }
		 
	     message = "";
		 return message;	
	}
	
	
	public String sendPrivate() throws Exception  {		
		 
		String [] wordsFromText = privateMessage.split(" ");	
		 
		 if (badWordsValidation(wordsFromText)) {
		     Message chatMessage = new Message(toUser, loginController.getUser(), privateMessage, MessageMode.PRIVATE);
			 wsService.send(toUser.getEmail(),"/queue/chat", chatMessage);
			 wsService.send(loginController.getUser().getEmail(),"/queue/chat", chatMessage);
		 
			 toUser = new ChatUser();
		 }
		 
	     privateMessage = "";
		 return privateMessage;	
	}
	
	private Boolean badWordsValidation(String [] wordsFromText ) {
		 List<RestrictWord> restrictWords = restrictWordService.findAll();
		 Boolean thatsOk = true;
		 if (!restrictWords.isEmpty())
			 for (int i = 0; i < wordsFromText.length;i++) {
				 String wordFromText = wordsFromText[i];
				 if (restrictWords.stream().filter(word-> word.getWord().equalsIgnoreCase(wordFromText)).count() > 0) {
					 thatsOk = false;
					 FacesContext context = FacesContext.getCurrentInstance();			         
				     context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro", "Não são permitidas palavras de baixo calão."));
				     break;
				 }
			 }		
		return thatsOk;
		
	}
	
	public  List<ChatUser> refreshUsers() {
		return getUsers();
	}
	
	private List<ChatUser> findAllUsers() {
		return userService.findAll();
	}

	public List<ChatUser> getUsers() {
		setUsers(findAllUsers());
		return users;
	}

	public void setUsers(List<ChatUser> users) {
		this.users = users;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public ChatUser getToUser() {
		return toUser;
	}


	public void setToUser(ChatUser toUser) {
		this.toUser = toUser;
	}


	public String getPrivateMessage() {
		return privateMessage;
	}


	public void setPrivateMessage(String privateMessage) {
		this.privateMessage = privateMessage;
	}
	
	
	
}
