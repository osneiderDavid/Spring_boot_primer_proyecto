package comspringtoolsappproyecto.model.sevices;

import java.util.List;import org.apache.catalina.Cluster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import comspringtoolsappproyecto.model.entity.Clientes;

public interface IClientesService {
public List<Clientes> ListarClientes();
public void GuardarCliente(Clientes cliente);
public Clientes BuscarCliente(Long id);
public int contarClientes();
public Page<Clientes> ListarClientesPaginados(Pageable pageable);
}
