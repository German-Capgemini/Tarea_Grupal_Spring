package com.formacionspringboot.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacionspringboot.apirest.modelo.Cliente;
import com.formacionspringboot.apirest.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteServicio;

	// Solicito todos los registros de la tabla
	@GetMapping("/clientes")
	public String mostrarTodos(Model modelo) {

		modelo.addAttribute("datos", clienteServicio.listarTodosLosClientes());
		return "clientes";
	}

	// Nuevo cliente
	@GetMapping("/nuevoCliente")
	public String nuevoClientes(Model modelo) {

		modelo.addAttribute("cliente", new Cliente());

		return "formCliente";
	}

	// Guardar cliente
	@PostMapping("/submitCliente")
	public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, Model modelo) {

		clienteServicio.guardarCliente(cliente);
		modelo.addAttribute("cliente", clienteServicio.listarTodosLosClientes());
		return "redirect:/clientes";
	}

	// Editar cliente
	@GetMapping("/cliente/editar/{numCliente}")
	public String editarCliente(@PathVariable Long numCliente, Model modelo) {
		modelo.addAttribute("keyCliente", clienteServicio.findByNumCliente(numCliente));
		return "formEditarCliente";

	}

	// Actualizar cliente
	@PostMapping("/cliente/{numCliente}")
	public String actualizarCliente(@PathVariable Long numCliente, @ModelAttribute("keyCliente") Cliente cliente) {

		Cliente clienteExiste = clienteServicio.findByNumCliente(numCliente);

		clienteExiste.setNumCliente(numCliente);
		clienteExiste.setNombre(cliente.getNombre());
		clienteExiste.setApellidos(cliente.getApellidos());
		clienteExiste.setSexo(cliente.getSexo());
		clienteExiste.setTelefono(cliente.getTelefono());

		clienteServicio.guardarCliente(clienteExiste);

		return "redirect:/clientes";
	}

	// Eliminar cliente
	@GetMapping("/cliente/eliminar/{numCliente}")
	public String eliminarCliente(@PathVariable Long numCliente) {
		clienteServicio.eliminarCliente(numCliente);
		return "redirect:/clientes";
	}
}

