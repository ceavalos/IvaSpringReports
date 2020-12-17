package innotec.com.sv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import innotec.com.sv.entities.Compra;



public interface IComprasDao extends PagingAndSortingRepository<Compra, Long>{
	
	@Query(value ="SELECT * FROM Compras u WHERE u.declaracion_id = ?1", nativeQuery = true)
	public List<Compra> findByDeclaracion(Long declaracion) ;
}
