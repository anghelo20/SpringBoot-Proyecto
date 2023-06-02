package com.example.demo.dao.Person;

import java.util.List;

import com.example.demo.model.Person;

public interface PersonDao {
	
	public void CreatePerson(Person person);
	
	public Person getPerson (Person person);
	
	public void updatePerson(Person person);
	
	public void removePerson(Person person);
	
	public Person getPersonById(Long id);
	
	public List<Object> getQueryDataPerson(String query);
	
	
	
}
