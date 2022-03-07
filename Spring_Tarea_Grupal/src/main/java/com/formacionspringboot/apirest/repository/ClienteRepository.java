package com.formacionspringboot.apirest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.modelo.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query(value="SELECT * FROM clientes WHERE num_cliente = ?1", nativeQuery=true)
	public Cliente findByNumCliente(Long id);
	
	@Query(value="SELECT * FROM clientes WHERE nombre = ?1", nativeQuery=true)
	public Cliente findByNombre(String nombre);

}
