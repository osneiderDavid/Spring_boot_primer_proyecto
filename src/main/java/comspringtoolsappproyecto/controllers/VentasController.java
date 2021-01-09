package comspringtoolsappproyecto.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import comspringtoolsappproyecto.model.dao.IClientes;
import comspringtoolsappproyecto.model.dao.IVentas;
import comspringtoolsappproyecto.model.entity.Clientes;
import comspringtoolsappproyecto.model.entity.Detalle_Venta;
import comspringtoolsappproyecto.model.entity.Productos;
import comspringtoolsappproyecto.model.entity.Ventas;
import comspringtoolsappproyecto.model.sevices.IClientesService;
import comspringtoolsappproyecto.model.sevices.IProductosService;
import comspringtoolsappproyecto.model.sevices.IVentasService;
import comspringtoolsappproyecto.utils.PageRender;

@Controller

public class VentasController {
	@Autowired
	private IClientesService cliense;
	@Autowired
	private IProductosService prose;
	@Autowired
	private IVentasService venser;
	private final Logger log = LoggerFactory.getLogger(getClass());
	@GetMapping("/ventas/detalle/{id}")
	public String verdetalle(@PathVariable(value="id") Long id, Model model) {
		Ventas ventas = null;
		if(id>0) {
			ventas = venser.BuscarVentas(id);	
		}else {
			return "redirect:/ventas";
		}
		 model.addAttribute("venta", ventas);
		
		return "ventas/detalle";
	}

	@GetMapping("/ventas")
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 4);
		Page<Ventas> ventas = venser.ListarPaginado(pageable);
		PageRender<Ventas> pageRender = new PageRender<>("/ventas", ventas);
		model.addAttribute("ventas", ventas);
		model.addAttribute("page", pageRender);
		
		return "ventas/listar";
	}

	@GetMapping("/ventas/{clienteId}")
	public String Crear(Model model, @PathVariable(value = "clienteId") Long clienteId) {
		Clientes cliente = cliense.BuscarCliente(clienteId);
		if (cliente == null) {
			return "redirect:/clientes";
		}
		Ventas ventas = new Ventas();
		ventas.setCliente(cliente);
		model.addAttribute("venta", ventas);

		return "ventas/formulario";
	}

	@GetMapping(value = "/buscar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Productos> buscarproductos(@PathVariable String term) {
		return prose.BuscarProductosTodos(term);

	}

	@PostMapping("/venta/save")
	public String guardar(Ventas ventas, @RequestParam(name = "item_id[]", required = false) Long[] itemid,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,RedirectAttributes flash) {
		
		
		for (int i = 0; i < itemid.length; i++) {
			Productos productos = new Productos();
			Detalle_Venta detalle_Venta = new Detalle_Venta();
			productos = prose.BuscarProductos(itemid[i]);

			detalle_Venta.setCantidad(cantidad[i]);
			detalle_Venta.setProducto(productos);
			ventas.AñadirDetalle(detalle_Venta);
			log.info("ID: " + itemid[i].toString() + ", cantidad: " + cantidad[i].toString());
			if(detalle_Venta.getCantidad() <= detalle_Venta.getProducto().getCantidad()) {
			prose.ActualizarCantidad(cantidad[i], itemid[i]);
			}else {
				return "redirect:/ventas/"+ventas.getCliente().getId();
			}

		}
		venser.GuardarVenta(ventas);
		
		return "redirect:/ventas";
	}

}
