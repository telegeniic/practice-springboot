package com.BancoJMGO.springboot.app.validator;

import com.BancoJMGO.springboot.app.models.entity.Cliente;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ClienteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Cliente.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Cliente cliente = (Cliente) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.cliente.nombre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty.cliente.apellido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroTelefonico", "NotEmpty.cliente.numeroTelefonico");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.cliente.email");

        if (!cliente.getNombre().matches("([a-zA-Z]+[ ]?)") && !cliente.getNombre().matches(".{0,35}")) {
            errors.rejectValue("nombre", "Format.cliente.nombre");
        }
        if (!cliente.getApellido().matches("([a-zA-Z]+[ ]?)+") && !cliente.getNombre().matches(".{0,35}")) {
            errors.rejectValue("apellido", "Format.cliente.nombre");
        }
        if (!cliente.getNumeroTelefonico().matches("[0-9]{10}")) {
            errors.rejectValue("numeroTelefonico", "Format.cliente.numeroTelefonico");
        }
        if (!cliente.getEmail().matches(".+@.+")) {
            errors.rejectValue("email", "Format.cliente.email");
        }
    }
}
