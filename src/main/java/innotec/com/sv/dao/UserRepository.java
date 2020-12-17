package innotec.com.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import innotec.com.sv.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
     
}
 
 