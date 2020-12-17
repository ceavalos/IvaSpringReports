package innotec.com.sv.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotec.com.sv.entities.Periodo;


public interface PeriodoService {

	public List<Periodo> findAll();

	public Page<Periodo> findAll(Pageable pageable);

	public Periodo findById(Long id);

	public Periodo save(Periodo periodo);

	public void delete(Long id);
	
}
