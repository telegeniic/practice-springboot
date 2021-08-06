package com.BancoJMGO.springboot.app.services;

import java.util.List;

import com.BancoJMGO.springboot.app.models.entity.Cuenta;

import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements ICuentaService {

    private List<Cuenta> lista;

    public CuentaServiceImpl() {

    }

    @Override
    public Cuenta getById(Long id, List<Cuenta> lista) {

        this.lista = lista;
        Cuenta cuentaResult = null;
        for (Cuenta cuenta : this.lista) {
            if (id == cuenta.getId()) {
                cuentaResult = cuenta;
                break;
            }
        }

        return cuentaResult;
    }
}
