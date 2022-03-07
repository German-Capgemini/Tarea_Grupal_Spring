package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.modelo.Cliente;
import com.formacionspringboot.apirest.modelo.Producto;
import com.formacionspringboot.apirest.modelo.Venta;

public interface VentaService {

	public List<Venta> listarTodosLosVentas();

	public List<Producto> findAllProductos();

	public List<Cliente> findAllClientes();

	public Venta findByFolio(Long folio);

	public Venta guardarVenta(Venta venta);

	public void eliminarVenta(Long folio);

}
