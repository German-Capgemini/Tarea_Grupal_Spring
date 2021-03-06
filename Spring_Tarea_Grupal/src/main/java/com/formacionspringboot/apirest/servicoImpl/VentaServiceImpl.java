package com.formacionspringboot.apirest.servicoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.modelo.Cliente;
import com.formacionspringboot.apirest.modelo.Producto;
import com.formacionspringboot.apirest.modelo.Venta;
import com.formacionspringboot.apirest.repository.VentaRepository;
import com.formacionspringboot.apirest.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

	@Autowired
	private VentaRepository repositorio;

	@Override
	@Transactional(readOnly = true)
	public List<Venta> listarTodosLosVentas() {
		return (List<Venta>) repositorio.findAll();
	}

	@Override
	@Transactional
	public Venta findByFolio(Long folio) {
		return repositorio.findById(folio).orElse(null);
	}

	@Override
	@Transactional
	public Venta guardarVenta(Venta venta) {
		return repositorio.save(venta);
	}

	@Override
	@Transactional
	public void eliminarVenta(Long folio) {
		repositorio.deleteById(folio);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAllProductos() {
		return repositorio.findAllProductos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAllClientes() {
		return repositorio.findAllClientes();
	}

}
