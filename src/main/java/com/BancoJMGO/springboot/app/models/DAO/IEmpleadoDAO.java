package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import com.BancoJMGO.springboot.app.models.entity.Empleado;

public interface IEmpleadoDAO {

    public List<Empleado> findALL();

    public void save(Empleado banco);

    public Empleado findOne(Long id);

    public void delete(Long id);
    
    public List<Empleado> findByCharge(String Charge);
}
