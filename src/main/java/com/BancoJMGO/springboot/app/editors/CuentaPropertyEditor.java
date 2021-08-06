package com.BancoJMGO.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import com.BancoJMGO.springboot.app.models.DAO.ICuentaDAO;
import com.BancoJMGO.springboot.app.services.ICuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CuentaPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private ICuentaService cuentaService;

    @Autowired
    private ICuentaDAO cuentaDAO;

    @Override
    public void setAsText(String idStr) throws IllegalArgumentException {
        try {
            Long id = Long.parseLong(idStr);
            this.setValue(cuentaService.getById(id, cuentaDAO.findALL()));
        } catch (Exception e) {
            System.out.println("Hubo un error al asignar el objeto cuenta a la tarjeta");
        }
    }
}
