package com.BancoJMGO.springboot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.BancoJMGO.springboot.app.models.DAO.ICasaInversionistaDAO;
import com.BancoJMGO.springboot.app.models.entity.CasaInversionista;

@Controller
@RequestMapping("casainversionista")
@SessionAttributes("casainversionista")
public class CasaInversionistaController {

	@Autowired
	private ICasaInversionistaDAO casaInversionistaDao;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("titulo", "Lista de Casas Inversionistas");
		model.addAttribute("casaInversionistas", casaInversionistaDao.findALL());
		return "casainversionista/casainversionistas-list";
	}

	@RequestMapping(value = "form")
	public String crear(Map<String, Object> model) {
		CasaInversionista casaInversionista = new CasaInversionista();
		model.put("casaInversionista", casaInversionista);
		model.put("titulo", "Llenar los datos de una casa inversionista");
		return "casainversionista/casainversionista_form";
	}

	@RequestMapping(value = "form/{id}")
	public String editar(@PathVariable(value = "id") long id, Map<String, Object> model) {
		CasaInversionista casaInversionista = null;
		if (id > 0) {
			casaInversionista = casaInversionistaDao.findOne(id);
		} else {
			return "redirect:/";
		}

		model.put("casaInversionista", casaInversionista);
		model.put("titulo", "Editar casa inversionista");

		return "casainversionista/casainversionista_form";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String guardar(@Valid CasaInversionista casaInversionista, BindingResult result, Model model,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Llene correctamente los datos");
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
			return "casainversionista/casainversionista_form";
		} else {
			model.addAttribute("result, false");
		}

		model.addAttribute("titulo", "formulario de banco");
		model.addAttribute("mensaje", "Se envio la informacion correctamente");
		casaInversionistaDao.save(casaInversionista);
		status.setComplete();

		return "redirect:list";
	}

	@RequestMapping(value = "eliminar/{id}")
	public String Eliminar(@PathVariable(value = "id") long id) {

		if (id > 0) {
			casaInversionistaDao.delete(id);
		}

		return "redirect:../list";
	}
}
