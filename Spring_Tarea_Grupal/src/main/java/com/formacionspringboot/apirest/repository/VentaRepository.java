package com.formacionspringboot.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.modelo.Cliente;
import com.formacionspringboot.apirest.modelo.Producto;
import com.formacionspringboot.apirest.modelo.Venta;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long>{
	//Para poder usar este método aquí (ya que es propio de otro repositorio):
	@Query("from Producto")
	public List<Producto> findAllProductos();
	@Query("from Cliente")
	public List<Cliente> findAllClientes();


	
	
}
