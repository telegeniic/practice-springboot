package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.BancoJMGO.springboot.app.models.entity.Banco;

@Repository("bancoDao")
public class BancoDAOImpl implements IBancoDAO{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Banco> findALL() {
		return em.createQuery("from Banco").getResultList();
	}

	@Transactional
	@Override
	public void save(Banco banco) {
		if(banco.getId() != null && banco.getId() > 0) {
			em.merge(banco);
		}
		else {
			em.persist(banco);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Banco findOne(Long id) {
		return em.find(Banco.class, id);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
		
	}
	
	

}
