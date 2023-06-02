package com.example.demo.dao.Person;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;


import com.example.demo.model.Person;

@Repository
@Transactional
public class PersonImp implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void CreatePerson(Person person) {
		entityManager.persist(person);
	}

	@Override
	public Person getPerson(Person person) {
		
		String query = "FROM Person WHERE dni = :dni AND estado = 1";
        @SuppressWarnings("unchecked")
		List<Person> lista = entityManager.createQuery(query)
                .setParameter("dni", person.getDni())
                .getResultList();
        if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	@Override
	public void updatePerson(Person person) {
		entityManager.merge(person);
		
	}
	
	@Override
	public void removePerson(Person person) {
		entityManager.merge(person);
		
	}
	
	
	
	@Override
	public Person getPersonById(Long id) {
		return entityManager.find(Person.class, id);
		
	}

	@Override
	public List<Object> getQueryDataPerson(String query) {
        @SuppressWarnings("unchecked")
		List<Object> lista = entityManager.createQuery(query)
                .getResultList();
		return lista;
		
	}

	

}
