package com.formacionspringboot.apirest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.modelo.Venta;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long>{
	
	@Query("from Venta")
	public List<Venta> findAllVentas();


	
	
}
