package com.formacionspringboot.apirest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.formacionspringboot.apirest.modelo.Producto;
import com.formacionspringboot.apirest.repository.ProductoRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

class ProductoTest_Jupiter {
	@Autowired
	private ProductoRepository productoDAO;

	@Test
	@Rollback(false)
	public void testGuardarProducto() {

		Producto productoNuevo = new Producto("Freidora Plus-23589", "Freidora sin aceite de aire caliente", 245.00, 5);

		Producto productoGuardado = productoDAO.save(productoNuevo);

		assertThat(productoGuardado.getNombre()).isEqualTo("Freidora Plus-23589");
	}

	@Test
	public void testBuscarProducto() {

		Producto productoBusco = productoDAO.findById((long) 3).orElse(null);

		assertThat(productoBusco.getNombre()).isEqualTo("Batidora 3000 Max");
	}

	@Test
	@Rollback(false)
	public void testactualizarProducto() {

		String nombre = "TV Lenovo";
		Producto productoNuevo = new Producto(nombre, "Televisor 3D", 2345.00, 15);
		productoNuevo.setClave((long) 6);
		productoDAO.save(productoNuevo);
		Producto productoActualizado = productoDAO.findById((long) 6).orElse(null);

		assertThat(productoActualizado.getNombre()).isEqualTo(nombre);
	}

	@Test
	public void testListarProductos() {

		List<Producto> productos = (List<Producto>) productoDAO.findAll();
		assertThat(productos.size()).isGreaterThan(0);
	}

	@Test
	@Rollback(false)
	public void testborrarProducto() {

		Producto productoBusco = productoDAO.findById((long) 6).orElse(null);

		productoDAO.deleteById(productoBusco.getClave());

		List<Producto> productos = (List<Producto>) productoDAO.findAll();

		assertThat(productos.size()).isEqualTo(5);
	}

}
