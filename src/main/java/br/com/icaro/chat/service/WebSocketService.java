
package br.com.icaro.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

	@Autowired
	private SimpMessagingTemplate template;

	public <T> void send(String url, T message) throws Exception {
		this.template.convertAndSend(url, message);		
	}

	public <T> void send(String user, String url, T message) throws Exception {
		this.template.convertAndSendToUser(user, url, message);		
	}

}


