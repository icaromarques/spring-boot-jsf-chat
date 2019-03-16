package br.com.icaro.chat.config;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;

import br.com.icaro.chat.controller.LoginController;
import br.com.icaro.chat.service.WebSocketService;

public class UserInterceptor extends ChannelInterceptorAdapter {

	@Autowired
	WebSocketService wsService;
	
	@Autowired
	LoginController loginController;
	
    @Override
    @SuppressWarnings("rawtypes")
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message
                    .getHeaders()
                    .get(SimpMessageHeaderAccessor.NATIVE_HEADERS);

            if (raw instanceof Map) {
				Object name = ((Map) raw).get("username");

                if (name instanceof LinkedList) {
                    accessor.setUser(new User(((LinkedList) name).get(0).toString()));
                }
            }
        } 
        
        return message;
    }
}
