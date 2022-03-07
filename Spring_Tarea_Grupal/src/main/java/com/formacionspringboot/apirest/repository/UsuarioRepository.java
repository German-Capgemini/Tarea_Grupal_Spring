package com.formacionspringboot.apirest.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.modelo.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long>{

	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	
	public Usuario findByUsername2(String username);
	
}