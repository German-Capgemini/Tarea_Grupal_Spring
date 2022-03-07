package com.formacionspringboot.apirest.servicoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.modelo.Producto;
import com.formacionspringboot.apirest.repository.ProductoRepository;
import com.formacionspringboot.apirest.service.ProductoService;

@Service
public class ProductoServicioImpl implements ProductoService {

	@Autowired
	ProductoRepository productoDao;

	@Override
	public List<Producto> listarTodosLosProductos() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	public Producto findByClave(Long clave) {
		return productoDao.findById(clave).orElse(null);
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	public void eliminarProducto(Long clave) {
		productoDao.deleteById(clave);
	}


}
