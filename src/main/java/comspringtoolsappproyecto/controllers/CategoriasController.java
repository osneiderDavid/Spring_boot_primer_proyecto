package comspringtoolsappproyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comspringtoolsappproyecto.model.entity.Categoria;
import comspringtoolsappproyecto.model.sevices.ICategoriasService;
import comspringtoolsappproyecto.utils.PageRender;

@Controller
public class CategoriasController {
	@Autowired
	private ICategoriasService cateser;
@GetMapping("/categorias")
public String listar(@RequestParam(name="page", defaultValue="0")int page,Model model) {
	Pageable pageable = PageRequest.of(page, 4);
	Page<Categoria> categorias = cateser.ListarPaginado(pageable);
	PageRender<Categoria> pageRender = new PageRender<>("/categorias", categorias);
	model.addAttribute("categorias", categorias);
	model.addAttribute("page", pageRender);
	return "categorias/listar";
}
@GetMapping("/categoria/crear")
public String crear( Model model) {
	
	model.addAttribute("categoria", new Categoria());
	return "categorias/formulario";
}
@PostMapping("/categoria/guardar")
public String guardar(Categoria categoria) {
	cateser.GuardarCategoria(categoria);
	return "redirect:/categorias";
}
}
