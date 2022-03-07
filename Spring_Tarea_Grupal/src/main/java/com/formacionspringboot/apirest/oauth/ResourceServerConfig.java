package com.formacionspringboot.apirest.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET,
		"/ventas","/nuevaVenta",
		"/submitVenta","/venta/editar/{folio}","/venta/{folio}","/venta/eliminar/{folio}",
		"/clientes","/nuevoCliente","/submitCliente","/cliente/editar/{numCliente}","/cliente/{numCliente}",
		"/cliente/eliminar/{numCliente}",
		"/productos","/nuevoProducto","/submitProducto","/producto/editar/{clave}","/producto/{clave}","/producto/eliminar/{clave}",
		"/api/ventasrest")
		.permitAll()
		.anyRequest()
		.authenticated();
	}
	
}