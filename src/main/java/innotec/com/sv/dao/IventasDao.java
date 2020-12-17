package innotec.com.sv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import innotec.com.sv.entities.Venta;


public interface IventasDao extends PagingAndSortingRepository <Venta, Long> {
	
	@Query(value ="SELECT * FROM Ventas u WHERE u.declaracion_id = ?1", nativeQuery = true)
	public List<Venta> findByDeclaracion(Long declaracion) ;
	

}
