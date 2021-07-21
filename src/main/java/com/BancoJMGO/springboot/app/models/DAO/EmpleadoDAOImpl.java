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
        return em.createQuery("from empleados").getResultList();
    }

    @Transactional
    @Override
    public void save(Empleado empleado) {
        if (empleado.getIdEmpleado() != null) {
            em.merge(empleado);
        } else {
            em.persist(empleado);
        }
    }

    @Override
    public Empleado findOne(String id) {
        return em.find(Empleado.class, id);
    }

    @Override
    public void delete(String id) {
        em.remove(findOne(id));
    }

}
