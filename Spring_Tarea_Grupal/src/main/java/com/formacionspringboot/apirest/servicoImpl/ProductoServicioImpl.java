package com.formacionspringboot.apirest.servicoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.modelo.Producto;
import com.formacionspringboot.apirest.service.ProductoService;

@Service
public class ProductoServicioImpl implements ProductoService {

	@Autowired
	ProductoServicioImpl productoDao;

	@Override
	public List<Producto> listarTodosLosProductos() {		
		return productoDao.findAll();
	}

	@Override
	public Producto findByClave(Long clave) {		
		return productoDao.findByClave(clave);
	}

	@Override
	public Producto guardarProducto(Producto producto) {		
		return productoDao.guardarProducto(producto);
	}

	@Override
	public Producto buscarProducto(Long clave) {		
		return productoDao.buscarProducto(clave);
	}

	@Override
	public void eliminarProducto(Long clave) {
		productoDao.eliminarProducto(clave);
	}

	@Override
	public List<Producto> findAll() {		
		return productoDao.findAll();
	}

}
