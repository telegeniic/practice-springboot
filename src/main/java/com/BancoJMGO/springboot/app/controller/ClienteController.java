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
import com.BancoJMGO.springboot.app.models.DAO.IClienteDAO;
import com.BancoJMGO.springboot.app.models.entity.Banco;
import com.BancoJMGO.springboot.app.models.entity.Cliente;
import com.BancoJMGO.springboot.app.validator.ClienteValidator;

@Controller
@RequestMapping("cliente")
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IBancoDAO bancoDao;

	@Autowired
	private IClienteDAO clienteDao;

	@Autowired
	private ClienteValidator clienteValidator;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("clientes", clienteDao.findALL());
		return "cliente/clientes-list";
	}

	@RequestMapping(value = "form")
	public String crear(Map<String, Object> model, Model modelList) {
		Cliente cliente = new Cliente();
		List<Banco> bancos = bancoDao.findALL();
		modelList.addAttribute("bancos", bancos);
		model.put("cliente", cliente);
		model.put("titulo", "Llenar los datos de una cuenta");
		return "cliente/cliente_form";
	}

	@RequestMapping(value = "form/{id}")
	public String editar(@PathVariable(value = "id") long id, Map<String, Object> model, Model modelList) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteDao.findOne(id);
		} else {
			return "redirect:/";
		}

		List<Banco> bancos = bancoDao.findALL();
		modelList.addAttribute("bancos", bancos);
		model.put("cliente", cliente);
		model.put("titulo", "Editar cuenta");

		return "cliente/cliente_form";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status,
			Model modelList) {

		clienteValidator.validate(cliente, result);

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Llene correctamente los datos");
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
			List<Banco> bancos = bancoDao.findALL();
			modelList.addAttribute("bancos", bancos);
			return "cliente/cliente_form";
		} else {
			model.addAttribute("result, false");
		}

		model.addAttribute("titulo", "formulario de Cliente");
		cliente.setBanco(bancoDao.findOne(cliente.getIdBancoAux()));
		clienteDao.save(cliente);
		status.setComplete();

		return "redirect:list";
	}

	@RequestMapping(value = "eliminar/{id}")
	public String Eliminar(@PathVariable(value = "id") long id) {

		if (id > 0) {
			clienteDao.delete(id);
		}

		return "redirect:../list";
	}
}
