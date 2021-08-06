package com.BancoJMGO.springboot.app.controller;

import java.util.List;
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
import com.BancoJMGO.springboot.app.models.DAO.IEmpleadoDAO;
import com.BancoJMGO.springboot.app.models.entity.Banco;
import com.BancoJMGO.springboot.app.models.entity.Empleado;

@Controller
@RequestMapping("empleado")
@SessionAttributes("empleado")
public class EmpleadoController {

	@Autowired
	private IBancoDAO bancoDao;

	@Autowired
	private IEmpleadoDAO empleadoDao;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("empleados", empleadoDao.findALL());
		return "empleado/empleados-list";
	}

	@RequestMapping(value = "form")
	public String crear(Map<String, Object> model, Model modelList) {
		Empleado empleado = new Empleado();
		List<Banco> bancos = bancoDao.findALL();
		modelList.addAttribute("bancos", bancos);
		model.put("empleado", empleado);
		model.put("titulo", "Llenar los datos de una cuenta");
		return "/empleado/empleado_form";
	}

	@RequestMapping(value = "form/{id}")
	public String editar(@PathVariable(value = "id") long id, Map<String, Object> model, Model modelList) {
		Empleado empleado = null;
		if (id > 0) {
			empleado = empleadoDao.findOne(id);
		} else {
			return "redirect:/";
		}

		List<Banco> bancos = bancoDao.findALL();
		modelList.addAttribute("bancos", bancos);
		model.put("empleado", empleado);
		model.put("titulo", "Editar cuenta");

		return "/empleado/empleado_form";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String guardar(@Valid Empleado empleado, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "formulario de tarjeta");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
			return "redirect:/clientes/clientes_form";
		}

		model.addAttribute("titulo", "formulario de tarjeta");
		empleado.setBanco(bancoDao.findOne(empleado.getIdBancoAux()));
		empleadoDao.save(empleado);
		status.setComplete();

		return "redirect:/index";
	}

	@RequestMapping(value = "/empleado/eliminar/{id}")
	public String Eliminar(@PathVariable(value = "id") long id) {

		if (id > 0) {
			empleadoDao.delete(id);
		}

		return "redirect:../list";
	}
}
