package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import com.BancoJMGO.springboot.app.models.entity.Banco;

public interface IBancoDAO {

	public List<Banco> findALL();
	
	public void save(Banco banco);
	
	public Banco findOne(Long id);
	
	public void delete(Long id);
	
}
