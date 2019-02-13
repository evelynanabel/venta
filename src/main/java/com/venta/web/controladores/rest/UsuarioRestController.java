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



import com.venta.proy.Usuario;
import com.venta.repositorios.ProductoRepository;
import com.venta.servicios.ServicioVenta;


	@RestController
	@RequestMapping("/apiusuario")
	public class UsuarioRestController {
		@Autowired
		ServicioVenta servicio;

		@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
		public Iterable<Usuario> findAllUsuarios() {

			//return servicio.findAllProd(); YA NO LO USAMOS - SOLO MOSTRAMOS LOS ACTIVOS
			//return servicio.findAllCli();
			return servicio.findByEstadoUsu("A");
		}

		@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Usuario findOne(@PathVariable Integer id) {

			return servicio.findOneUsu(id);
		}

		@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void save(@RequestBody Usuario usuario) {
				//Al crear nuevo producto lo colocamos con estado activo
			    usuario.setEstado("A");
				servicio.saveUsu(usuario);

		}

		@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void update(@RequestBody Usuario usuario, @PathVariable Integer id) {

			Usuario c = servicio.findOneUsu(id);
			// informacion que viene de la peticion put para modificar
			c.setUsername(usuario.getUsername());
			c.setPassword(usuario.getPassword());
			c.setNombre(usuario.getNombre());
			c.setApellidos(usuario.getApellidos());
			
			c.setDni(usuario.getDni());
			//c.set(Producto.getStock());
			//c.setCategoria(Producto.getCategoria());
			servicio.saveUsu(c);
		}

		@PutMapping(value = "/erase/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void erase(@RequestBody Usuario usuario, @PathVariable Integer id) {

			Usuario c = servicio.findOneUsu(id);
			// seteamos el atributo estado para dar de baja
			c.setEstado("B");
			servicio.saveUsu(c);
		}
		
		
	}

