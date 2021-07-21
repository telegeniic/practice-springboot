package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.BancoJMGO.springboot.app.models.entity.Tarjeta;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TarjetaDAOImpl implements ITarjetaDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Tarjeta> findALL() {
        return em.createQuery("from tarjetas").getResultList();
    }

    @Transactional
    @Override
    public void save(Tarjeta tarjeta) {
        if (tarjeta.getId() != null && tarjeta.getId() > 0) {
            em.merge(tarjeta);
        } else {
            em.persist(tarjeta);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Tarjeta findOne(Long id) {
        return em.find(Tarjeta.class, id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }

    @Transactional(readOnly = true)
	@Override
	public Tarjeta findByAccountNumber(String AccountNumber) {
		return (Tarjeta) em.createQuery("SELECT numedo_de_cuenta FROM tarjetas WHERE numedo_de_cuenta = :value").setParameter("value", AccountNumber).getSingleResult();
	}

}
