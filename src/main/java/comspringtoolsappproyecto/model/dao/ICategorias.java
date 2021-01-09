package comspringtoolsappproyecto.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comspringtoolsappproyecto.model.entity.Categoria;

public interface ICategorias extends JpaRepository<Categoria, Long>{
@Query("select count(*) from Categoria")
public int contarCategorias();

}
