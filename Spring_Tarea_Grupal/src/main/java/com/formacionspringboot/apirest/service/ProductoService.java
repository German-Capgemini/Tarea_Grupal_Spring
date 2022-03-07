package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.modelo.Producto;

public interface ProductoService {

	public List<Producto> listarTodosLosProductos();
	
	public List<Producto> findAll();

	public Producto findByClave(Long clave);

	public Producto guardarProducto(Producto producto);

	public Producto buscarProducto(Long clave);

	public void eliminarProducto(Long clave);

}
