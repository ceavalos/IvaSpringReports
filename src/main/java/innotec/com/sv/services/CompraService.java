package innotec.com.sv.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotec.com.sv.entities.Compra;



public interface  CompraService {

	public List<Compra> findAll();

	public Page<Compra> findAll(Pageable pageable);

	public void save(Compra compra);

	public Compra findOne(Long id);

	public void delete(Long id);
	
	public List<Compra> findByDeclaracion(Long declaracion);

}
