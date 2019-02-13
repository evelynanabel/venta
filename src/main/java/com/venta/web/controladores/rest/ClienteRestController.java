package com.venta.web.controladores.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.venta.proy.Categoria;
import com.venta.proy.Cliente;
import com.venta.repositorios.ProductoRepository;
import com.venta.servicios.ServicioVenta;


	@RestController
	@RequestMapping("/apicliente")
	public class ClienteRestController {
		@Autowired
		ServicioVenta servicio;

		@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
		public Iterable<Cliente> findAllClientes() {

			//return servicio.findAllProd(); YA NO LO USAMOS - SOLO MOSTRAMOS LOS ACTIVOS
			//return servicio.findAllCli();
			return servicio.findByEstadoCli("A");
		}

		@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Cliente findOne(@PathVariable Integer id) {

			return servicio.findOneCli(id);
		}

		@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void save(@RequestBody Cliente cliente) {
				//Al crear nuevo producto lo colocamos con estado activo
			    cliente.setEstado("A");
				servicio.saveCli(cliente);

		}

		@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void update(@RequestBody Cliente cliente, @PathVariable Integer id) {

			Cliente c = servicio.findOneCli(id);
			// informacion que viene de la peticion put para modificar
			c.setNombre(cliente.getNombre());
			c.setApellido(cliente.getApellido());
			c.setDireccion(cliente.getDireccion());
			c.setDni(cliente.getDni());
			//c.set(Producto.getStock());
			//c.setCategoria(Producto.getCategoria());
			servicio.saveCli(c);
		}

		@PutMapping(value = "/erase/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void erase(@RequestBody Cliente cliente, @PathVariable Integer id) {

			Cliente c = servicio.findOneCli(id);
			// seteamos el atributo estado para dar de baja
			c.setEstado("B");
			servicio.saveCli(c);
		}
		
		
	}

