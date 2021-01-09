package comspringtoolsappproyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import comspringtoolsappproyecto.model.sevices.ICategoriasService;
import comspringtoolsappproyecto.model.sevices.IClientesService;
import comspringtoolsappproyecto.model.sevices.IProductosService;
import comspringtoolsappproyecto.model.sevices.IVentasService;

@Controller
public class HomeController {
	@Autowired
	private ICategoriasService catedao;
	@Autowired
	private IClientesService clienteser;
	@Autowired
	private IProductosService proser;
	@Autowired
	private IVentasService ventser;
@GetMapping("/")
public String home(Model model) {
	model.addAttribute("contarcate", catedao.contarCategorias());
	model.addAttribute("contarclientes", clienteser.contarClientes());
	model.addAttribute("contarproducto", proser.contarProductos());
	model.addAttribute("contarventas", ventser.contarVentas());
	
	return "dashboard";
}


}
