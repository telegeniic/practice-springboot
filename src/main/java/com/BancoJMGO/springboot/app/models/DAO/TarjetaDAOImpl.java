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
        if (tarjeta.getIdTarjeta() != null) {
            em.merge(tarjeta);
        } else {
            em.persist(tarjeta);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Tarjeta findOne(String id) {
        return em.find(Tarjeta.class, id);
    }

    @Transactional
    @Override
    public void delete(String id) {
        em.remove(findOne(id));
    }

}
