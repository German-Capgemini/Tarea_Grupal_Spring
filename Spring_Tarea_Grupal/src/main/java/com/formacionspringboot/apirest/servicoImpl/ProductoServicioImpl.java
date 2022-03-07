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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto buscarProducto(Long clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarProducto(Long clave) {
		// TODO Auto-generated method stub

	}

}
