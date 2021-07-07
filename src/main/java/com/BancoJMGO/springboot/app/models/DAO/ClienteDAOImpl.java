package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.BancoJMGO.springboot.app.models.entity.Cliente;

import org.springframework.transaction.annotation.Transactional;

public class ClienteDAOImpl implements IClienteDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findALL() {
        return em.createQuery("from Cliente").getResultList();
    }

    @Transactional
    @Override
    public void save(Cliente cliente) {
        if (cliente.getIdUser() != null) {
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findOne(String id) {
        return em.find(Cliente.class, id);
    }

    @Transactional
    @Override
    public void delete(String id) {
        em.remove(findOne(id));
    }

}
