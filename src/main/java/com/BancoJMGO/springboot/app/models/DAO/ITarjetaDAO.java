package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import com.BancoJMGO.springboot.app.models.entity.Tarjeta;

public interface ITarjetaDAO {

    public List<Tarjeta> findALL();

    public void save(Tarjeta banco);

    public Tarjeta findOne(String id);

    public void delete(String id);

}
