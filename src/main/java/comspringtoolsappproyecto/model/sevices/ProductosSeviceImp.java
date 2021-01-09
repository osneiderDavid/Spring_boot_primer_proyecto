package comspringtoolsappproyecto.model.sevices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comspringtoolsappproyecto.model.dao.IProductos;
import comspringtoolsappproyecto.model.entity.Productos;
@Service
public class ProductosSeviceImp implements IProductosService{
	@Autowired
	private IProductos prodao;

	@Transactional(readOnly=true)
	@Override
	public List<Productos> listarProductos() {
		// TODO Auto-generated method stub
		return prodao.findAll();
	}
	@Transactional
    @Override
	public void GuardarProducto(Productos productos) {
		prodao.save(productos);
		
	}
	@Override
	public List<Productos> BuscarProductosTodos(String term) {
		
		return prodao.BuscarTodosProductos(term);
	}
	@Override
	public Productos BuscarProductos(Long id) {
		// TODO Auto-generated method stub
		return prodao.findById(id).orElse(null);
	}
	@Override
	public void ActualizarCantidad(Integer cantidad, Long id) {
		prodao.actualizarCantidad(cantidad, id);
		
	}
	@Transactional(readOnly=true)
	@Override
	public int contarProductos() {
		// TODO Auto-generated method stub
		return prodao.contarProductos();
	}
	@Transactional(readOnly=true)
	@Override
	public Page<Productos> ListarProductosPaginados(Pageable pageable) {
		// TODO Auto-generated method stub
		return prodao.findAll(pageable);
	}

}
