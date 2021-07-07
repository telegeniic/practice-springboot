package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import com.BancoJMGO.springboot.app.models.entity.Cuenta;

public interface ICuentaDAO {

    public List<Cuenta> findALL();

    public void save(Cuenta banco);

    public Cuenta findOne(String id);

    public void delete(String id);
}
