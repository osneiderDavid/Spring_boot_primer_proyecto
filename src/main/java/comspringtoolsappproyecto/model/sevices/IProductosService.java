package comspringtoolsappproyecto.model.sevices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import comspringtoolsappproyecto.model.entity.Productos;

public interface IProductosService {
public List<Productos> listarProductos();
public void GuardarProducto(Productos productos);
public List<Productos> BuscarProductosTodos(String term);
public Productos BuscarProductos(Long id);
public void ActualizarCantidad(Integer cantidad, Long id);
public int contarProductos();
public Page<Productos> ListarProductosPaginados(Pageable pageable);
}
