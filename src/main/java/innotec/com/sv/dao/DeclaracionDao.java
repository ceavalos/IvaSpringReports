package innotec.com.sv.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotec.com.sv.entities.Declaracion;
import innotec.com.sv.entities.Empresa;



public interface DeclaracionDao extends PagingAndSortingRepository<Declaracion, Long>{
	
	public List<Declaracion> findByEmpresa(Empresa empresa);
	
}
