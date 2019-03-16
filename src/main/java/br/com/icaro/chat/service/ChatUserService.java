package br.com.icaro.chat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.icaro.chat.model.ChatUser;
import br.com.icaro.chat.persistence.UserRepository;


@Service
public class ChatUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatUserService.class);

	@Autowired
	UserRepository repository;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ChatUser> findAll() {
		List<ChatUser> lista = repository.findAll();
		return lista;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public ChatUser findByEmailAndCpf(String email,String cpf) {
		ChatUser user = repository.findByEmailAndCpf(email,cpf);
		return user;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public ChatUser findById(Long id) {
		ChatUser catUser = repository.findById(id);
		
		return catUser;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public ChatUser insertOrUpdate(ChatUser user) throws Exception {	
		try {
			ChatUser aux = (ChatUser)repository.save(user);
			return aux;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}	
}
