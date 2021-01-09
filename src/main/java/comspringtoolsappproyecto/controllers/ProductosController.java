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
import comspringtoolsappproyecto.model.entity.Productos;
import comspringtoolsappproyecto.model.sevices.ICategoriasService;
import comspringtoolsappproyecto.model.sevices.IProductosService;
import comspringtoolsappproyecto.utils.PageRender;

@Controller
public class ProductosController {
	@Autowired
	private IProductosService proserv;
	@Autowired
	private ICategoriasService cateser;
@GetMapping("/productos")
public String listar(@RequestParam(name="page", defaultValue="0" )int page,Model model) {
	Pageable pageable = PageRequest.of(page, 4);
	Page<Productos>   productos = proserv.ListarProductosPaginados(pageable);
	PageRender<Productos> pageRender = new PageRender<>("/productos", productos);
	model.addAttribute("productos", productos);
	model.addAttribute("page", pageRender);
	return "productos/listar";
}
@GetMapping("/productos/crear")
public String crear(Model model) {
	model.addAttribute("producto", new Productos());
	model.addAttribute("categorias", cateser.ListarCategoria());
	return "productos/formulario";
}
@PostMapping("/productos/guardar")
public String guardar(Productos productos) {
	proserv.GuardarProducto(productos);
	return "redirect:/productos";
}
@GetMapping("/productos/detalle/{id}")
public String detalle(@PathVariable(value="id")Long id, Model model) {
	Productos productos = null;
	if(id>0) {
		productos=proserv.BuscarProductos(id);
	}else {
		return "redirect:/productos";
	}
	model.addAttribute("producto", productos);
	return "productos/detalle";
}
@GetMapping("/productos/editar/{id}")
public String editar(@PathVariable(value="id") Long id,  Model model) {
	Productos productos = null;
	if(id>0) {
		productos=proserv.BuscarProductos(id);
	}else {
		return "redirect:/productos";
	}
	model.addAttribute("producto", productos);
	model.addAttribute("categorias", cateser.ListarCategoria());
	return "productos/formulario";
}
}
