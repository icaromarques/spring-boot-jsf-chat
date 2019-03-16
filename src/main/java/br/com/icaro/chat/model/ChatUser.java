package br.com.icaro.chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "chat_user")
@Component
public class ChatUser extends Persistent {
	
	private String email;
	private String cpf;
	private Boolean online;
	
	public ChatUser() {
		
	}
	
	public ChatUser(String email, String cpf, Boolean online) {
		super();
		this.email = email;
		this.cpf = cpf;
		this.online = online;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "chat_user_seq")
	@SequenceGenerator(name = "chat_user_seq", sequenceName = "chat_user_seq")
	@Column(name="id")
	public Long getId() {
		return id;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cpf", nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "is_online", nullable = false)
	@Type(type="true_false")
	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	
}
