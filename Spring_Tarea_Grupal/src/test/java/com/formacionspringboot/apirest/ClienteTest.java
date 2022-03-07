package com.formacionspringboot.apirest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.formacionspringboot.apirest.modelo.Cliente;
import com.formacionspringboot.apirest.repository.ClienteRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteTest {

	@Autowired
	private ClienteRepository clienteDao;
	
	@Test
	public void testMostrar() {
		List<Cliente> clientes = (List<Cliente>) clienteDao.findAll();
		
		assertThat(clientes).size().isGreaterThan(0);
	}
	
	@Test
	public void testNuevoClientes() {
		Cliente cliente = new Cliente("Pepe", "Rodriguez", "Hombre", 12345678);
		Cliente clienteGuardado = clienteDao.save(cliente);
		
		assertNotNull(clienteGuardado);
	}
	
	@Test
	public void testGuardarCliente() {
		String nombre = "Pepe";
		Cliente cliente = new Cliente(nombre, "Rodriguez", "Hombre", 12345678);
		cliente.setNumCliente((long)1);
		clienteDao.save(cliente);
		
		Cliente clienteActualizado = clienteDao.findByNombre(nombre);
		
		assertThat(clienteActualizado.getNombre().equals(nombre));
	}
	
	@Test
	public void testEliminarCliente() {
		Long id=(long) 1;
		boolean existe = clienteDao.findById(id).isPresent();
		
		clienteDao.deleteById(id);
		
		boolean existe2 = clienteDao.findById(id).isPresent();
		
		assertTrue(existe);
		assertFalse(existe2);
	}

}