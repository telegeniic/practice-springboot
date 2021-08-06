package com.BancoJMGO.springboot.app.validator;

import com.BancoJMGO.springboot.app.models.entity.Cuenta;
import com.BancoJMGO.springboot.app.models.entity.Tarjeta;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TarjetaValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Tarjeta.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Tarjeta tarjeta = (Tarjeta) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroTarjeta", "NotEmpty.Tarjeta.numeroTarjeta");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "icv", "NotEmpty.Tarjeta.icv");

        if (!tarjeta.getNumeroTarjeta().matches("[0-9]{16}")) {
            errors.rejectValue("numeroTarjeta", "Format.Tarjeta.numeroTarjeta");
        }
        if (!tarjeta.getIcv().matches("[0-9]{3}")) {
            errors.rejectValue("icv", "Format.Tarjeta.icv");
        }
        Cuenta cuenta = tarjeta.getCuenta();
        if (cuenta.getTarjetas().size() >= 2) {
            errors.rejectValue("cuenta", "StackOverflow.Tarjeta.cuenta");
        }
        for (Tarjeta tar : cuenta.getTarjetas()) {
            if (tar.getNombre().equals(tarjeta.getNombre())) {
                if (tar.getNombre().equals("fisica")) {
                    errors.rejectValue("nombre", "StackOverflow.Tarjeta.nombre.fisica");
                } else {
                    errors.rejectValue("nombre", "StackOverflow.Tarjeta.nombre.digital");
                }

            }
        }

    }

}
