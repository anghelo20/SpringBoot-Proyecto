package com.example.demo.dao.Memo;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Memo;
import com.example.demo.model.Person;

@Repository
@Transactional
public class MemoImp implements MemoDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Memo> getMemosActive() {
		String query = "FROM Memo WHERE estado = 1";
        List<Memo> listaM = entityManager.createQuery(query)
                .getResultList();
		return listaM;
		
	}

	@Override
	public void createMemo(Person person) {
		entityManager.persist(person);
		
	}
	
	
	@Override
	public void deleteMemo(Long id) {
		Query query = entityManager.createQuery("UPDATE Memo  SET estado = 0 WHERE id = :id");
			    query.setParameter("id", id);
			    int rowsUpdated = query.executeUpdate();
			    System.out.println("entities Updated: " + rowsUpdated);
	}

	@Override
	public void updateMemo(Memo memo) {
		Query query = entityManager.createQuery("UPDATE Memo  SET asunto = :asunto, razon = :razon  WHERE id = :id");
	    query.setParameter("id", memo.getId());
	    query.setParameter("asunto", memo.getAsunto());
	    query.setParameter("razon",memo.getRazon() );
	    int rowsUpdated = query.executeUpdate();
	    System.out.println("entities Updated: " + rowsUpdated);
		
	}

	@Override
	public int getNumberMemos() {
		int maxNumber = 0;
		@SuppressWarnings("unchecked")
		List<Integer> listI = entityManager.createQuery("SELECT n_memo FROM Memo  WHERE estado = 1 ORDER BY n_memo")
				.getResultList();
		if (listI.size() == 0) {
			maxNumber = 1;
		}else {
			maxNumber = listI.get(listI.size()-1);
		}
		
		for (int i = 1; i < maxNumber; i++) {
            boolean isNumeroFaltante = true;

            for (int j = 0; j < listI.size(); j++) {
                if (listI.get(j) == i) {
                    isNumeroFaltante = false;
                }
            }

            if (isNumeroFaltante) {
                return i;
            }
        }
		return 0;
		
		
	}

}
