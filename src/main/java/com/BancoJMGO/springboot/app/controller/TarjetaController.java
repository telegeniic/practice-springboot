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

import com.BancoJMGO.springboot.app.editors.CuentaPropertyEditor;
import com.BancoJMGO.springboot.app.errors.DataBaseBancoException;
import com.BancoJMGO.springboot.app.models.DAO.ICuentaDAO;
import com.BancoJMGO.springboot.app.models.DAO.ITarjetaDAO;
import com.BancoJMGO.springboot.app.models.entity.Cuenta;
import com.BancoJMGO.springboot.app.models.entity.Tarjeta;
import com.BancoJMGO.springboot.app.validator.TarjetaValidator;

@Controller
@RequestMapping("tarjeta")
@SessionAttributes("tarjeta")
public class TarjetaController {

	@Autowired
	private ICuentaDAO cuentaDao;

	@Autowired
	private ITarjetaDAO tarjetaDao;

	@Autowired
	private TarjetaValidator tarjetaValidator;

	@Autowired
	private CuentaPropertyEditor cuentaEditor;

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cuenta.class, "cuenta", cuentaEditor);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("tarjetas", tarjetaDao.findALL());
		return "tarjeta/tarjetas-list";
	}

	@RequestMapping(value = "form")
	public String crear(Map<String, Object> model, Model modelList) {
		Tarjeta tarjeta = new Tarjeta();
		List<Cuenta> cuentas = cuentaDao.findALL();
		modelList.addAttribute("cuentas", cuentas);
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Llenar los datos de una cuenta");
		return "/tarjeta/tarjeta_form";
	}

	@RequestMapping(value = "form/{id}")
	public String editar(@PathVariable(value = "id") long id, Map<String, Object> model, Model modelList) {
		Tarjeta tarjeta = null;
		if (id > 0) {
			tarjeta = tarjetaDao.findOne(id);
		} else {
			return "redirect:/";
		}
		List<Cuenta> cuentas = cuentaDao.findALL();
		modelList.addAttribute("cuentas", cuentas);
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Editar cuenta");

		return "/tarjeta/tarjeta_form";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String guardar(@Valid Tarjeta tarjeta, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "formulario de tarjeta");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
			return "redirect:form";
		}

		Cuenta cuenta = cuentaDao.findOne(tarjeta.getCuenta().getId());
		List<Tarjeta> tarjetas = cuenta.getTarjetas();

		if (tarjetas.size() < 2) {
			for (Tarjeta tar : tarjetas) {
				if (tar.getNombre().equals(tarjeta.getNombre())) {
					if (tar.getNombre().equals("fisica")) {
						flash.addFlashAttribute("mensaje",
								"Su cuenta ya tiene una tarjeta fisica, favor de pedir una digital");
						return "redirect:form";
					}
					if (tar.getNombre().equals("digital")) {
						flash.addFlashAttribute("mensaje",
								"Su cuenta ya tiene una tarjeta digital, favor de pedir una fisica");
						return "redirect:form";
					}
				}
			}
			tarjetas.add(tarjeta);
			cuenta.setTarjetas(tarjetas);
		} else {
			flash.addFlashAttribute("mensaje", "La cuenta no puede tener mas de 2 tajetas");
			return "redirect:form";
		}

		model.addAttribute("titulo", "formulario de tarjeta");
		// tarjeta.setCuenta(cuentaDao.findOne(tarjeta.getIdCuentaAux()));
		tarjetaValidator.validate(tarjeta, result);

		try {
			tarjetaDao.save(tarjeta);
		} catch (DataBaseBancoException e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}

		status.setComplete();

		return "redirect:list";
	}

	@RequestMapping(value = "/tarjeta/eliminar/{id}")
	public String Eliminar(@PathVariable(value = "id") long id) {

		if (id > 0) {
			tarjetaDao.delete(id);
		}

		return "redirect:../list";
	}
}
