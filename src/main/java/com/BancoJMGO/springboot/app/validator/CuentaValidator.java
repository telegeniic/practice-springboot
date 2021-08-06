package com.BancoJMGO.springboot.app.validator;

import com.BancoJMGO.springboot.app.models.entity.Cuenta;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CuentaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Cuenta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Cuenta cuenta = (Cuenta) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroDeCuenta", "NotEmpty.cuenta.numeroDeCuenta");

		if (!cuenta.getNumeroDeCuenta().matches("[0-9]{8}")) {
			errors.rejectValue("numeroDeCuenta", "Format.Cuenta.numeroDeCuenta");
		}

	}

}
