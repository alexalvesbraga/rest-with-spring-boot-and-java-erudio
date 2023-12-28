package br.com.erudio.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controler.PersonController;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozzerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.model.data.vo.v1.PersonVO;
import br.com.erudio.model.data.vo.v2.PersonVOV2;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	public PersonRepository repository;
	
	@Autowired
	public PersonMapper mapper;
	
	public PersonVO findById(Long id) {		
		logger.info("Finding one PersonVO!");	
		var entity = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("No records found for this!"));
		var vo = DozzerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
		
	}
	public List<PersonVO> findAll() {
		logger.info("Finding all PersonVOs!");					
		var persons = DozzerMapper.parseListObject(repository.findAll(), PersonVO.class);
		persons
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
		
	}
	
	public PersonVO create(PersonVO personVO) {
		if(personVO == null) throw new ResourceNotFoundException("Pessoa não encontrada");
		logger.info("Creating one PersonVO!");
		var entity = DozzerMapper.parseObject(personVO, Person.class);
		var vo = DozzerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 personVO2) {
		logger.info("Creating one PersonVOV2!");
		var entity = mapper.convertVoToEntity(personVO2);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO personVO) {
		logger.info("Update one PersonVO!");
		var entity = repository.findById(personVO.getKey()).orElseThrow(() ->new ResourceNotFoundException("No records found for this!"));		
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setGender(personVO.getGender());
		entity.setAddres(personVO.getLastName());
		var vo = DozzerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Delete one PersonVO!");
		var entity = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("No records found for this!"));
		repository.delete(entity);
	}
	
	

}
