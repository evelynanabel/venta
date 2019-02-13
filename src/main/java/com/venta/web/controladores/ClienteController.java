package com.venta.web.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import com.venta.servicios.ServicioVenta;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ServicioVenta servicio;

	@RequestMapping("/index")
	public String lista(Model modelo) {

		// Envia a la vista es decir a la plantilla todos los productos
		modelo.addAttribute("clientes", servicio.findAllCli());

		// prod-index.html en la carpeta categoria
		return "cliente/cli-index";
	}

	
}
