package comspringtoolsappproyecto.model.dao;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comspringtoolsappproyecto.model.entity.Ventas;

public interface IVentas extends JpaRepository<Ventas, Long>{
	@Query("select count(*) from Ventas")
	public int contarVentas();

	
	

}
