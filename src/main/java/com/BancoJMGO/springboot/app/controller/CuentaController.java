package com.BancoJMGO.springboot.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.BancoJMGO.springboot.app.models.DAO.ICasaInversionistaDAO;
import com.BancoJMGO.springboot.app.models.DAO.IClienteDAO;
import com.BancoJMGO.springboot.app.models.DAO.ICuentaDAO;
import com.BancoJMGO.springboot.app.models.entity.CasaInversionista;
import com.BancoJMGO.springboot.app.models.entity.Cliente;
import com.BancoJMGO.springboot.app.models.entity.Cuenta;
import com.BancoJMGO.springboot.app.validator.CuentaValidator;

@Controller
@RequestMapping("cuenta")
@SessionAttributes("cuenta")
public class CuentaController {

	@Autowired
	private ICasaInversionistaDAO casaInversionistaDAO;

	@Autowired
	private IClienteDAO clienteDAO;

	@Autowired
	private ICuentaDAO cuentaDAO;

	@Autowired
	private CuentaValidator cuentaValidator;

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(cuentaValidator);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("cuentas", cuentaDAO.findALL());
		model.addAttribute("eliminar", false);
		return "cuenta/cuentas-list";
	}

	@RequestMapping(value = "form")
	public String crear(Map<String, Object> model, Model modelList) {
		Cuenta cuenta = new Cuenta();
		List<CasaInversionista> casaInversionistas = casaInversionistaDAO.findALL();
		List<Cliente> clientes = clienteDAO.findALL();
		modelList.addAttribute("clientes", clientes);
		modelList.addAttribute("casaInversionistas", casaInversionistas);
		model.put("cuenta", cuenta);
		model.put("titulo", "Llenar los datos de una cuenta");
		return "cuenta/cuenta_form";
	}

	@RequestMapping(value = "form/{id}")
	public String editar(@PathVariable(value = "id") long id, Map<String, Object> model, Model modelList) {
		Cuenta cuenta = null;
		if (id > 0) {
			cuenta = cuentaDAO.findOne(id);
		} else {
			return "redirect:/";
		}

		List<CasaInversionista> casaInversionistas = casaInversionistaDAO.findALL();
		List<Cliente> clientes = clienteDAO.findALL();
		modelList.addAttribute("clientes", clientes);
		modelList.addAttribute("casaInversionistas", casaInversionistas);
		model.put("cuenta", cuenta);
		model.put("titulo", "Editar cuenta");

		return "cuenta/cuenta_form";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String guardar(@Valid Cuenta cuenta, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "formulario de tarjeta");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
			return "redirect:/cuenta/form";
		}

		Cliente cliente = clienteDAO.findOne(cuenta.getIdClienteAux());
		if (cliente.getCuenta() == null) {
			cliente.setCuenta(cuenta);
		} else {
			flash.addFlashAttribute("mensaje", "El cliente que selecciono, ya tiene una cuenta");
			return "redirect:form";
		}

		model.addAttribute("titulo", "formulario de tarjeta");
		cuenta.setCasaInversionista(casaInversionistaDAO.findOne(cuenta.getIdCasaInversionistaAux()));
		cuenta.setCliente(clienteDAO.findOne(cuenta.getIdClienteAux()));
		cuentaDAO.save(cuenta);
		status.setComplete();

		return "redirect:/index";
	}

	@RequestMapping(value = "cuenta/eliminar/{id}")
	public String Eliminar(@PathVariable(value = "id") long id, RedirectAttributes flash) {

		if (id > 0) {
			Cuenta cuenta = cuentaDAO.findOne(id);
			if (!cuenta.getTarjetas().isEmpty()) {
				flash.addFlashAttribute("mensaje", "La cuenta tiene tarjetas pendientes por eliminar");
				flash.addFlashAttribute("eliminar", true);
			} else {
				cuentaDAO.delete(id);
			}
		}

		return "redirect:../list";
	}
}
