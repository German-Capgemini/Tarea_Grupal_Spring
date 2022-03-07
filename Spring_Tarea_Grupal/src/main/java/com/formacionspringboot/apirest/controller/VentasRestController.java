package com.formacionspringboot.apirest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspringboot.apirest.modelo.Venta;
import com.formacionspringboot.apirest.service.VentaService;

@RestController
@RequestMapping("/api")
public class VentasRestController {

	
	@Autowired
	private VentaService servicio;
	
	@GetMapping("/ventasrest")
	public List<Venta> findAllCompras() {
		return servicio.listarTodosLosVentas();
	}
	
	@PostMapping("/ventarest")
	public ResponseEntity<?> saveVenta(@RequestBody Venta venta) {
		Venta ventaNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			ventaNew = servicio.guardarVenta(venta);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Ha sido creada con éxito.");
		response.put("compra", ventaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/venta/{id}")
	public ResponseEntity<?> updateVenta(@RequestBody Venta venta, @PathVariable Long id) {
		Venta ventaActual = servicio.findByFolio(id);
		Map<String, Object> response = new HashMap<>();

		if (ventaActual == null) {
			response.put("mensaje", "La venta ID: " + id.toString() + " no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			ventaActual.setCliente(venta.getCliente());
			ventaActual.setProducto(venta.getProducto());
			ventaActual.setCantidad(venta.getCantidad());
			ventaActual.setSubtotal(venta.getSubtotal());
			ventaActual.setIva(venta.getIva());
			ventaActual.setTotal(venta.getTotal());
			
			servicio.guardarVenta(ventaActual);
		

		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Ha sido actualizada con éxito.");
		response.put("venta", ventaActual);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	@GetMapping("/ventas/{id}")
	public ResponseEntity<?> findComprasById(@PathVariable Long id) {
		Venta venta = null;
		Map<String, Object> response = new HashMap<>();

		try {
			
			venta = servicio.findByFolio(id);
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (venta == null) {
			response.put("mensaje", "La venta ID: " + id.toString() + " no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}
	

	@DeleteMapping("/venta/{id}")
	public ResponseEntity<?> deleteClienteExcepciones(@PathVariable Long id) {
		Map<String,Object> 	response = new HashMap<>();
		Venta ventaActual=servicio.findByFolio(id);

		if(ventaActual==null) {

			response.put("mensaje", "Error: no se pudo editar, la venta con ID: "+id.toString()+"no existe en la BBDD");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}

		try {	
		servicio.eliminarVenta(id);

		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			response.put("mensaje", "Error al realizar un delete a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("venta", ventaActual);
		response.put("mensaje", "Se ha borrado con exito el cliente");

		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	
	
}
