package com.formacionspringboot.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.formacionspringboot.apirest.modelo.Cliente;
import com.formacionspringboot.apirest.modelo.Producto;
import com.formacionspringboot.apirest.modelo.Venta;
import com.formacionspringboot.apirest.repository.ClienteRepository;
import com.formacionspringboot.apirest.repository.ProductoRepository;
import com.formacionspringboot.apirest.repository.VentaRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class VentasTest {

	@Autowired
	private VentaRepository ventasDao;
	
	@Autowired
	private ProductoRepository productoDao;
	
	@Autowired
	private ClienteRepository clienteDao;

	@Test
	@Rollback(false)
	public void testGuardarVenta() {

		Producto producto=productoDao.findById((long) 1).orElse(null);
				
		Cliente cliente=clienteDao.findById((long) 1).orElse(null);
		
		Venta venta= new Venta(cliente,producto, 300, 230.00d, 21.00d,300.00d);
		
		Venta ventaGuardada=ventasDao.save(venta);
		
		assertNotNull(ventaGuardada);
		
	}

	@Test
	public void testBuscarVentas() {

		Venta ventaBuscada=ventasDao.findById((long) 1).orElse(null);

		assertThat(ventaBuscada.getCantidad()==300);
	}

	@Test
	@Rollback(false)
	public void testactualizarVenta() {

		Producto producto=productoDao.findById((long) 1).orElse(null);
		Cliente cliente=clienteDao.findById((long) 1).orElse(null);
		Venta venta= new Venta(cliente,producto, 300, 230.00, 21.00,5000.00);
		
		venta.setFolio((long)1);
		
		ventasDao.save(venta);
		
		Venta VentaNueva=ventasDao.findById((long)1).orElse(null);
		
		assertThat(VentaNueva.getCantidad()==300);
	}

	@Test
	public void testListarProductos() {

		List<Venta> productos = (List<Venta>) ventasDao.findAll();
		assertThat(productos.size()).isGreaterThan(0);
	}

	@Test
	@Rollback(false)
	public void TestBorrarVenta() {
		
		
		Long id=(long) 1;
		
		boolean existe=ventasDao.findById(id).isPresent();
		
		ventasDao.deleteById(id);
		
		boolean existe2=ventasDao.findById(id).isPresent();
		
		assertTrue(existe);
		assertFalse(existe2);
	}

}
