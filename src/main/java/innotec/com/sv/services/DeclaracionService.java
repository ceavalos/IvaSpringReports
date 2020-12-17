package innotec.com.sv.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotec.com.sv.entities.Declaracion;
import innotec.com.sv.entities.Empresa;


public interface DeclaracionService {

    public List<Declaracion> findAll();
	
	public Page<Declaracion> findAll(Pageable pageable);

	public void save(Declaracion declaracion);
	
	public Declaracion findOne(Long id);
	
	public void delete(Long id);
	
	public List<Declaracion> findEmpresa(Empresa empresa);
	
	public double totalCompras (Declaracion declaracion);
	
	public double totalVentas (Declaracion declaracion);
	
}
