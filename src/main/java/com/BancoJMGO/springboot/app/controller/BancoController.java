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

import com.BancoJMGO.springboot.app.models.DAO.IBancoDAO;
import com.BancoJMGO.springboot.app.models.entity.Banco;

@Controller
@RequestMapping("banco")
@SessionAttributes("banco")
public class BancoController {

	@Autowired
	private IBancoDAO bancoDao;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("titulo", "Lista de Bancos");
		model.addAttribute("bancos", bancoDao.findALL());
		return "banco/bancos-list";
	}

	@RequestMapping(value = "form")
	public String crear(Map<String, Object> model) {
		Banco banco = new Banco();
		model.put("banco", banco);
		model.put("titulo", "Llenar los datos de una cuenta");
		return "banco/banco_form";
	}

	@RequestMapping(value = "form/{id}")
	public String editar(@PathVariable(value = "id") long id, Map<String, Object> model) {
		Banco banco = null;
		if (id > 0) {
			banco = bancoDao.findOne(id);
		} else {
			return "redirect:/";
		}

		model.put("banco", banco);
		model.put("titulo", "Editar Banco");

		return "banco/banco_form";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String guardar(@Valid Banco banco, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Llene correctamente los datos");
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
			return "banco/banco_form";
		} else {
			model.addAttribute("result, false");
		}

		model.addAttribute("titulo", "formulario de banco");
		model.addAttribute("mensaje", "Se envio la informacion correctamente");
		bancoDao.save(banco);
		status.setComplete();

		return "redirect:list";
	}

	@RequestMapping(value = "eliminar/{id}")
	public String Eliminar(@PathVariable(value = "id") long id) {

		if (id > 0) {
			bancoDao.delete(id);
		}

		return "redirect:../list";
	}
}
