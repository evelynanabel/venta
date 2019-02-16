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
import com.venta.proy.Detalle;
import com.venta.proy.Factura;

import com.venta.repositorios.FacturaRepository;
import com.venta.servicios.ServicioVenta;


	@RestController
	@RequestMapping("/apifactura")
	public class FacturaRestController {
		@Autowired
		ServicioVenta servicio;

		@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
		public Iterable<Factura> findAllFac() {

			return servicio.findAllFac(); //YA NO LO USAMOS - SOLO MOSTRAMOS LOS ACTIVOS
			//return servicio.findByEstado("A");
		}

		@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Factura findOne(@PathVariable Integer id) {

			return servicio.findOneFac(id);
		}
		@GetMapping(value = "/finddetalle/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Iterable<Detalle> findOneDet(@PathVariable Integer id) {
			Factura f = servicio.findOneFac(id);
			return f.getDetalles();
		}

		@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void save(@RequestBody Factura Facturas) {
				//Al crear nuevo factura lo colocamos con estado activo
			   /* Facturas.setEstado("A");
				servicio.saveFac(Facturas);*/

		}

		@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void update(@RequestBody Factura Facturas, @PathVariable Integer id) {

			Factura c = servicio.findOneFac(id);
			// informacion que viene de la peticion put para modificar
			/*c.setNombre(Factura.getNombre());
			c.setStock(Factura.getStock());
			c.setCategoria(Factura.getCategoria());
			servicio.saveFac(c);*/
		}

		@PutMapping(value = "/erase/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void erase(@RequestBody Factura Factura, @PathVariable Integer id) {

			Factura c = servicio.findOneFac(id);
			// seteamos el atributo estado para dar de baja
			//c.setEstado("B");
			//servicio.saveFac(c);
		}
		
		
	}

