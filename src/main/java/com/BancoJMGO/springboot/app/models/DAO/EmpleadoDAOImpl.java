package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.BancoJMGO.springboot.app.models.entity.Empleado;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmpleadoDAOImpl implements IEmpleadoDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Empleado> findALL() {
        return em.createQuery("from Empleado").getResultList();
    }

    @Transactional
    @Override
    public void save(Empleado empleado) {
        if (empleado.getId() != null && empleado.getId() > 0) {
            em.merge(empleado);
        } else {
            em.persist(empleado);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Empleado findOne(Long id) {
        return em.find(Empleado.class, id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Empleado> findByCharge(String Charge) {
        return em.createQuery("SELECT puesto FROM Empleado WHERE puesto = :value").setParameter("value", Charge)
                .getResultList();
    }

}
