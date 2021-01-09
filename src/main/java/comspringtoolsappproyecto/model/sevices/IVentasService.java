package comspringtoolsappproyecto.model.sevices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import comspringtoolsappproyecto.model.entity.Ventas;

public interface IVentasService {
public void GuardarVenta(Ventas ventas);
public List<Ventas> ListarVentas();
public Page<Ventas> ListarPaginado(Pageable pageable);
public int contarVentas();
public Ventas BuscarVentas(Long id);
}
