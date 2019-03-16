package br.com.icaro.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import br.com.icaro.chat.dto.Message;
import br.com.icaro.chat.model.ChatUser;
import br.com.icaro.chat.service.WebSocketService;
@Component
public class WebSocketEventListener {


	@Autowired
	WebSocketService wsService;
	    
	
	    @EventListener
	    public void handleWebSocketConnectListener(SessionConnectedEvent event) throws Exception {
	    	
	    	ChatUser user = new ChatUser(event.getUser().getName(), "", true);
	    	Message sendMessage = new Message(null, user, "", MessageMode.JOIN);
	    	wsService.send("/topic/chat", sendMessage);
			
	    }

	    @EventListener
	    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) throws Exception {
	    	ChatUser user = new ChatUser(event.getUser().getName(), "", true);
	    	Message sendMessage = new Message(null, user, "", MessageMode.LEAVE);
	    	wsService.send("/topic/chat", sendMessage);
	    
	    }
	    
}
