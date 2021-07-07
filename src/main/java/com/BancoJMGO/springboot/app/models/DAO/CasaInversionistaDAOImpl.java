package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.BancoJMGO.springboot.app.models.entity.CasaInversionista;

public class CasaInversionistaDAOImpl implements ICasaInversionistaDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<CasaInversionista> findALL() {
        return em.createQuery("from CasaInversionista").getResultList();
    }

    @Transactional
    @Override
    public void save(CasaInversionista casaInversionista) {
        if (casaInversionista.getIdOferta() != null) {
            em.merge(casaInversionista);
        } else {
            em.persist(casaInversionista);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public CasaInversionista findOne(String id) {
        return em.find(CasaInversionista.class, id);
    }

    @Transactional
    @Override
    public void delete(String id) {
        em.remove(findOne(id));

    }

}
