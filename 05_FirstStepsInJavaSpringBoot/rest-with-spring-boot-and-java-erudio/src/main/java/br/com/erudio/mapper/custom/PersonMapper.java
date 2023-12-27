package br.com.erudio.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;
import br.com.erudio.model.data.vo.v2.PersonVOV2;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVo(Person person) {
		
		PersonVOV2 vo = new PersonVOV2();
		vo.setKey(person.getId());
		vo.setAddres(person.getAddres());
		vo.setBirthDay(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOV2 personVOV2) {
		
		Person entity = new Person();
		entity.setId(personVOV2.getKey());
		entity.setAddres(personVOV2.getAddres());
		//entity.setBirthDay(new Date());
		entity.setFirstName(personVOV2.getFirstName());
		entity.setLastName(personVOV2.getLastName());
		entity.setGender(personVOV2.getGender());
		return entity;
	}
}
