package comspringtoolsappproyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import comspringtoolsappproyecto.model.entity.Clientes;
import comspringtoolsappproyecto.model.sevices.IClientesService;
import comspringtoolsappproyecto.utils.PageRender;

@Controller
public class ClientesController {
	@Autowired
	private IClientesService clienteservice;
@GetMapping("/clientes")
public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
	Pageable  pageable = PageRequest.of(page, 4);
	Page<Clientes> clientes = clienteservice.ListarClientesPaginados(pageable);
	PageRender<Clientes> pageRender = new PageRender<>("/clientes", clientes);
	model.addAttribute("clientes", clientes);
	model.addAttribute("page", pageRender);
	return "clientes/listar";
}
@GetMapping("/clientes/crear")
public String crear(Model model) {
	model.addAttribute("cliente", new Clientes());
	return "clientes/formulario";
}
@PostMapping("/clientes/guardar")
public String guardar(Clientes clientes, RedirectAttributes flash) {
	clienteservice.GuardarCliente(clientes);
	flash.addFlashAttribute("success", "Se registro el cliente de forma correcta!");
	
	return "redirect:/clientes";
}
@GetMapping("/clientes/editar/{id}")
public String editar(@PathVariable(value="id") Long id, Model model) {
	Clientes clientes = null;
	if(id>0) {
		clientes=clienteservice.BuscarCliente(id);
	}
	model.addAttribute("cliente", clientes);
	return "clientes/formulario";
}
@GetMapping("/clientes/detalle/{id}")
public String detalle (@PathVariable(value="id") Long id, Model model) {
	Clientes  clientes = null;
	if(id>0) {
		clientes=clienteservice.BuscarCliente(id);
	}else {
		return "redirect:/clientes";
	}
	model.addAttribute("cliente", clientes);
	return "clientes/detalle";
}
}
