package innotec.com.sv.dao;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotec.com.sv.entities.Cliente;



public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

	public List<Cliente> findByNrc(String nrc);
	
}
