package com.formacionspringboot.apirest.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.modelo.Cliente;
import com.formacionspringboot.apirest.repository.ClienteRepository;
import com.formacionspringboot.apirest.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	ClienteRepository AccesoDb;
	
	@Override
	public List<Cliente> listarTodosLosClientes() {

		return (List<Cliente>) AccesoDb.findAll();
	}

	@Override
	public Cliente findByNumCliente(Long numCliente) {
		
		return AccesoDb.findById(numCliente).orElse(null);
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		
		return AccesoDb.save(cliente);
	}

	@Override
	public Cliente eliminarCliente(Long numCliente) {
		AccesoDb.deleteById(numCliente);
		return null;
		
	}
	
}
