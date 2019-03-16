package br.com.icaro.chat.dto;

import br.com.icaro.chat.config.MessageMode;
import br.com.icaro.chat.model.ChatUser;

public class Message {

	private ChatUser toUser;
	private ChatUser fromUser;
	private String text;
	private MessageMode messageMode;

	public Message() {}
	
	public Message(ChatUser toUser, ChatUser fromUser, String text, MessageMode messageMode) {
		super();
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.text = text;
		this.messageMode = messageMode;
	}

	public MessageMode getMessageMode() {
		return messageMode;
	}

	public void setMessageMode(MessageMode messageMode) {
		this.messageMode = messageMode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ChatUser getToUser() {
		return toUser;
	}

	public void setToUser(ChatUser toUser) {
		this.toUser = toUser;
	}

	public ChatUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(ChatUser fromUser) {
		this.fromUser = fromUser;
	}
	
}
