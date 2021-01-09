package comspringtoolsappproyecto.model.sevices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import comspringtoolsappproyecto.model.entity.Categoria;

public interface ICategoriasService {
public List<Categoria> ListarCategoria();
public void GuardarCategoria(Categoria categoria);
public int contarCategorias();
public Page<Categoria> ListarPaginado(Pageable pageable);
}
