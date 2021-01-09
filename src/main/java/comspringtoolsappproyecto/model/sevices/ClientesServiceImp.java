package comspringtoolsappproyecto.model.sevices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comspringtoolsappproyecto.model.dao.IClientes;
import comspringtoolsappproyecto.model.entity.Clientes;

@Service
public class ClientesServiceImp implements IClientesService{
	@Autowired
	private IClientes clientedao;

	@Transactional(readOnly=true)
	@Override
	public List<Clientes> ListarClientes() {
		return clientedao.findAll();
	}

	@Transactional
	@Override
	public void GuardarCliente(Clientes cliente) {
		clientedao.save(cliente);
		
	}

	@Transactional(readOnly=true)
	@Override
	public Clientes BuscarCliente(Long id) {
		// TODO Auto-generated method stub
		return clientedao.findById(id).orElse(null);
	}
	@Transactional(readOnly=true)
    @Override
	public int contarClientes() {
		// TODO Auto-generated method stub
		return clientedao.contarClientes();
	}

	@Override
	public Page<Clientes> ListarClientesPaginados(Pageable pageable) {
		// TODO Auto-generated method stub
		return clientedao.findAll(pageable);
	}
	
	

}
