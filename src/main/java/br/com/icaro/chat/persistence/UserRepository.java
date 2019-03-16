package br.com.icaro.chat.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.icaro.chat.model.ChatUser;

@Repository
public interface UserRepository extends CrudRepository<ChatUser, Integer> {

	public ChatUser findById(Long id);
	public ChatUser findByEmail(String email);
	public ChatUser findByEmailAndCpf(String email, String cpf);
	public ChatUser findByCpf(String cpf);
	public List<ChatUser> findAll();	
	
}


