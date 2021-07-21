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
        return em.createQuery("from cuentas").getResultList();
    }

    @Transactional
    @Override
    public void save(Cuenta cuenta) {
        if (cuenta.getId() != null && cuenta.getId() > 0) {
            em.merge(cuenta);
        } else {
            em.persist(cuenta);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Cuenta findOne(Long id) {
        return em.find(Cuenta.class, id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }

}
