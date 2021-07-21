package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.BancoJMGO.springboot.app.models.entity.Cuenta;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CuentaDAOImpl implements ICuentaDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Cuenta> findALL() {
        return em.createQuery("from Cuenta").getResultList();
    }

    @Transactional
    @Override
    public void save(Cuenta cuenta) {
        if (cuenta.getIdCuenta() != null) {
            em.merge(cuenta);
        } else {
            em.persist(cuenta);
        }
    }

    @Transactional
    @Override
    public Cuenta findOne(String id) {
        return em.find(Cuenta.class, id);
    }

    @Override
    public void delete(String id) {
        em.remove(findOne(id));
    }

}
