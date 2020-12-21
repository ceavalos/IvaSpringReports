package innotec.com.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import innotec.com.sv.entities.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
}