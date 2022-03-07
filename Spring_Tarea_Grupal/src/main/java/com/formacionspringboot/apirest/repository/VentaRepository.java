package com.formacionspringboot.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.modelo.Venta;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long>{

}
