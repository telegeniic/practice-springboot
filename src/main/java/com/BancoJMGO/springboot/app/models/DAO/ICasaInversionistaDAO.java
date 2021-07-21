package com.BancoJMGO.springboot.app.models.DAO;

import java.util.List;

import com.BancoJMGO.springboot.app.models.entity.CasaInversionista;

public interface ICasaInversionistaDAO {

	public List<CasaInversionista> findALL();

	public void save(CasaInversionista banco);

	public CasaInversionista findOne(Long id);

	public void delete(Long id);
}
