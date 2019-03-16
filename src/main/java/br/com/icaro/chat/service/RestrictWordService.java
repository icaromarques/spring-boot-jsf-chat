package br.com.icaro.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.icaro.chat.model.RestrictWord;
import br.com.icaro.chat.persistence.RestrictWordRepository;


@Service
public class RestrictWordService {

	@Autowired
	RestrictWordRepository repository;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<RestrictWord> findAll() {
		List<RestrictWord> lista = repository.findAll();
		return lista != null ?lista:new ArrayList<>();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public RestrictWord findById(Long id) {
		RestrictWord word = repository.findById(id);
		
		return word;
	}
	
}
