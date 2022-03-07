package com.formacionspringboot.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacionspringboot.apirest.modelo.Producto;
import com.formacionspringboot.apirest.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoServicio;

	// Solicito todos los registros de la tabla
	@GetMapping("/productos")
	public String mostrarTodos(Model modelo) {

		modelo.addAttribute("datos", productoServicio.listarTodosLosProductos());
		return "productos";
	}

	// Nuevo producto
	@GetMapping("/nuevoProducto")
	public String nuevoProductos(Model modelo) {

		modelo.addAttribute("producto", new Producto());

		return "formProducto";
	}

	// Guardar producto
	@PostMapping("/submitProducto")
	public String guardarProducto(@ModelAttribute("producto") Producto producto, Model modelo) {

		productoServicio.guardarProducto(producto);
		modelo.addAttribute("producto", productoServicio.listarTodosLosProductos());
		return "redirect:/productos";
	}

	// Editar producto
	@GetMapping("/producto/editar/{clave}")
	public String editarProducto(@PathVariable Long clave, Model modelo) {
		modelo.addAttribute("keyProducto", productoServicio.findByClave(clave));
		return "formEditarProducto";

	}

	// Actualizar producto
	@PostMapping("/producto/{clave}")
	public String actualizarProducto(@PathVariable Long clave, @ModelAttribute("keyProducto") Producto producto) {

		Producto productoExiste = productoServicio.findByClave(clave);

		productoExiste.setClave(clave);
		productoExiste.setNombre(producto.getNombre());
		productoExiste.setDescripcion(producto.getDescripcion());
		productoExiste.setPrecioUnitario(producto.getPrecioUnitario());
		productoExiste.setExistencias(producto.getExistencias());

		productoServicio.guardarProducto(productoExiste);

		return "redirect:/productos";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}

	// Eliminar producto
	@GetMapping("/producto/eliminar/{clave}")
	public String eliminarProducto(@PathVariable Long clave) {
		try {
			productoServicio.eliminarProducto(clave);
		} catch (Exception e) {
			return "redirect:/error";
		}
		return "redirect:/productos";
	}

}
