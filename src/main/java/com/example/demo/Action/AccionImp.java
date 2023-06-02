package com.example.demo.Action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Accion;

@Repository
@Transactional
public class AccionImp implements AccionDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Accion> getAccions() {
		String query = "FROM Accion";
        List<Accion> listaM = entityManager.createQuery(query)
                .getResultList();
		return listaM;
	}

	@Override
	public void saveAccions(Accion accion) {
		entityManager.persist(accion);
		
	}

	@Override
	public Accion getAccionById(int id) {
		return entityManager.find(Accion.class, id);
	}

	@Override
	public void createBitacAccion(Accion accion) {
		entityManager.persist(accion);
		
	}

}
