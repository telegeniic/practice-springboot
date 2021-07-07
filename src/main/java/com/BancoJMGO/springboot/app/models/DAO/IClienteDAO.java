package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import com.BancoJMGO.springboot.app.models.entity.Cliente;

public interface IClienteDAO {

    public List<Cliente> findALL();

    public void save(Cliente banco);

    public Cliente findOne(String id);

    public void delete(String id);
}
