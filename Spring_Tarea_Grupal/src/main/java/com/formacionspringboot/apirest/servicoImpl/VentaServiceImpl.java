package com.formacionspringboot.apirest.servicoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.modelo.Venta;
import com.formacionspringboot.apirest.repository.VentaRepository;
import com.formacionspringboot.apirest.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

	@Autowired
	private VentaRepository repositorio;

	@Override
	@Transactional(readOnly=true)
	public List<Venta> listarTodosLosVentas() {
		// TODO Auto-generated method stub
		return repositorio.findAllVentas();
	}

	@Override
	public Venta findByFolio(Long folio) {
		// TODO Auto-generated method stub
		return repositorio.findById(folio).orElse(null);
	}

	@Override
	public Venta guardarVenta(Venta venta) {
		// TODO Auto-generated method stub
		return repositorio.save(venta);
	}



	@Override
	public void eliminarVenta(Long folio) {
		repositorio.deleteById(folio);
		
	}
	
	
	
	
	
}
