package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.modelo.Cliente;

public interface ClienteService {

	public List<Cliente> listarTodosLosClientes();
		
	public Cliente findByNumCliente(Long numCliente);

	public Cliente guardarCliente(Cliente cliente);

	public Cliente eliminarCliente(Long numCliente);

}
