package comspringtoolsappproyecto.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comspringtoolsappproyecto.model.entity.Clientes;

public interface IClientes extends JpaRepository<Clientes, Long>{
	@Query("select count(*) from Clientes")
	public int contarClientes();

}
