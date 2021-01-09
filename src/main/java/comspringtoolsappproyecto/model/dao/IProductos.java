package comspringtoolsappproyecto.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import comspringtoolsappproyecto.model.entity.Productos;

public interface IProductos extends JpaRepository<Productos, Long>{
@Query("select p from Productos p where p.nombre like %?1%")
public List<Productos> BuscarTodosProductos(String term);
@Transactional
@Modifying
@Query("update Productos set cantidad = cantidad - ?1 where id=?2")
public void actualizarCantidad(Integer cantidad, Long id);
@Query("select count(*) from Productos")
public int contarProductos();


}
