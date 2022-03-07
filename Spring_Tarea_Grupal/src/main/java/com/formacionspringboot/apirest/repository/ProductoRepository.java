package com.formacionspringboot.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.modelo.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{

}
