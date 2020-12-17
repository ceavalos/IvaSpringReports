package innotec.com.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import innotec.com.sv.entities.Role;



 
 
public interface RoleRepository extends CrudRepository<Role, Integer> {
     
}