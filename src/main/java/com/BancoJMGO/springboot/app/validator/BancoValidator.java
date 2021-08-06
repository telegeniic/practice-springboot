package com.BancoJMGO.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.BancoJMGO.springboot.app.models.entity.Banco;

@Component
public class BancoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		//Con este metodo aseguramos que esta clase es asignable
		return Banco.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Banco banco = (Banco)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sucursal", "NotEmpty.banco.sucursal");

		if(!banco.getSucursal().matches("[a-z,A-Z]{1,15}?[ ]?[a-z,A-Z]{1,15}")) {
			
		}
	}

}
