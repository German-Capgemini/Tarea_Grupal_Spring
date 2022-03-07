package com.formacionspringboot.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacionspringboot.apirest.modelo.Venta;
import com.formacionspringboot.apirest.service.VentaService;

@Controller
public class VentaController {
	
	@Autowired
	private VentaService ventaServicio;

	// Solicito todos los registros de la tabla
	@GetMapping("/ventas")
	public String mostrarTodos(Model modelo) {

		modelo.addAttribute("datos", ventaServicio.listarTodosLosVentas());
		return "ventas";
	}

	// Nueva venta
	@GetMapping("/nuevaVenta")
	public String nuevaVenta(Model modelo) {

		modelo.addAttribute("venta", new Venta());

		return "formVenta";
	}

	// Guardar Venta
	@PostMapping("/submitVenta")
	public String guardarVenta(@ModelAttribute("venta") Venta venta, Model modelo) {

		ventaServicio.guardarVenta(venta);
		modelo.addAttribute("venta", ventaServicio.listarTodosLosVentas());
		return "redirect:/ventas";
	}

	// Editar Venta
	@GetMapping("/venta/editar/{folio}")
	public String editarVenta(@PathVariable Long folio, Model modelo) {
		modelo.addAttribute("keyVenta", ventaServicio.findByFolio(folio));
		return "formEditarVenta";

	}

	// Actualizar venta
	@PostMapping("/venta/{folio}")
	public String actualizarProducto(@PathVariable Long folio, @ModelAttribute("keyVenta") Venta venta) {

		Venta ventaExiste = ventaServicio.findByFolio(folio);

		ventaExiste.setFolio(folio);
		ventaExiste.setCliente(venta.getCliente());
		ventaExiste.setProducto(venta.getProducto());
		ventaExiste.setCantidad(venta.getCantidad());
		ventaExiste.setSubtotal(venta.getSubtotal());
		ventaExiste.setIva(venta.getIva());
		ventaExiste.setTotal(venta.getTotal());

		ventaServicio.guardarVenta(ventaExiste);

		return "redirect:/ventas";
	}

	// Eliminar venta
	@GetMapping("/venta/eliminar/{folio}")
	public String eliminarVenta(@PathVariable Long folio) {
		ventaServicio.eliminarVenta(folio);
		return "redirect:/ventas";
	}

}