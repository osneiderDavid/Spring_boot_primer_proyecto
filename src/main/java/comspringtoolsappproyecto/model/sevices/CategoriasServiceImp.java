package comspringtoolsappproyecto.model.sevices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comspringtoolsappproyecto.model.dao.ICategorias;
import comspringtoolsappproyecto.model.entity.Categoria;
@Service
public class CategoriasServiceImp implements ICategoriasService{
	@Autowired
	private ICategorias catedao;

	@Transactional(readOnly=true)
	@Override
	public List<Categoria> ListarCategoria() {
		return catedao.findAll();
	}

	@Transactional
	@Override
	public void GuardarCategoria(Categoria categoria) {
		catedao.save(categoria);
		
	}

	@Transactional(readOnly=true)
	@Override
	public int contarCategorias() {
		// TODO Auto-generated method stub
		return catedao.contarCategorias();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Categoria> ListarPaginado(Pageable pageable) {
		// TODO Auto-generated method stub
		return catedao.findAll(pageable);
	}

	
}
