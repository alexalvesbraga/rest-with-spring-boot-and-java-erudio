package br.com.erudio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	public PersonRepository repository;
	
	public Person findById(Long id) {		
		logger.info("Finding one person!");	
		Person person = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("No records found for this!"));
		return person;
		
	}
	public List<Person> findAll() {
		logger.info("Finding all persons!");		
		List<Person> persons = repository.findAll();		
		return persons;
		
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Update one person!");
		Person entity = this.findById(person.getId());		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddres(person.getLastName());
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Delete one person!");
		Person entity = this.findById(id);
		repository.delete(entity);
	}
	
	

}
