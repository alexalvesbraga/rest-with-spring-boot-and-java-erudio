package br.com.erudio.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.controler.PersonController;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozzerMapper;
import br.com.erudio.model.data.vo.v1.PersonVO;
import br.com.erudio.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	

	private Logger logger = Logger.getLogger(UserService.class.getName());
	
	@Autowired
	public UserRepository repository;
	
		
	public UserService(UserRepository repository) {
		this.repository = repository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		logger.info("Finding one User By Name!");
		var user = repository.findByUserName(username);
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
	}
	
	
	

}
