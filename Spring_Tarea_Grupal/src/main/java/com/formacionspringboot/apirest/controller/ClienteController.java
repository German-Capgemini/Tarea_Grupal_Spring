package com.formacionspringboot.apirest.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspringboot.apirest.modelo.Cliente;
import com.formacionspringboot.apirest.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService servicio;
	
	@GetMapping({"/clientes","/todos"})
	public List<Cliente> index(){
		return servicio.listarTodosLosClientes();
	}
	
	@GetMapping("clientes/{id}")
	public ResponseEntity<?> findClienteById(@PathVariable Long id) {
		Cliente cliente=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.findByNumCliente(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		cliente=servicio.findByNumCliente(id);
		if (cliente==null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	/*@GetMapping("clientes/{id}")
	public Cliente findClienteById(@PathVariable Long id) {
		return servicio.findById(id);
	}*/
	
	
	@PutMapping("cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveCliente(@PathVariable Cliente cliente) {
		Cliente clienteNew=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			clienteNew=servicio.guardarCliente(cliente);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "El cliente no se ha creado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El cliente se ha creado con exito");
		response.put("cliente",clienteNew);
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	/*
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody Cliente cliente) {
		return servicio.save(cliente);
	}
	 */	
	
	
	@PutMapping("cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual=servicio.findByNumCliente(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (clienteActual==null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellidos(cliente.getApellidos());
			clienteActual.setSexo(cliente.getSexo());
			clienteActual.setTelefono(cliente.getTelefono());
			
			
		} catch(DataAccessException e) {
			response.put("mensaje", "El al actualizar el cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El cliente se ha actualizado con exito");
		response.put("cliente",clienteActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	/*
	@PutMapping("cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteUpdate=servicio.findById(id);
		
		clienteUpdate.setNombre(cliente.getNombre());
		clienteUpdate.setApellido(cliente.getApellido());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setCreatedAt(cliente.getCreatedAt());
		
		return servicio.save(clienteUpdate);
	}*/
	
	/*
	@DeleteMapping("/cliente/{id}")
	public void deleteCliente(@PathVariable Long id) {
		servicio.delete(id);
	}*/
	
	@DeleteMapping("/clientedelete/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		Cliente clienteActual= servicio.findByNumCliente(id);
		
		Map<String, Object> response=new HashMap<>();
		
		response.put("cliente",clienteActual);
		
		if(clienteActual==null) {
			response.put("mensaje", "Error: no se pudo eliminar");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		try {
			
			clienteActual=servicio.eliminarCliente(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El cliente se ha eliminado con exito");
		response.put("cliente",clienteActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
