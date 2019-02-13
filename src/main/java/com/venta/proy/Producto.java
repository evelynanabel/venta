package com.venta.proy;

import java.util.List;

import javax.persistence.*;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;

import com.venta.proy.Factura;



@Entity
@Table(name="producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	
	private String nombre;
	private int stock;
	private String estado;
	
	
	//Uno a uno
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	
	//bi-directional many-to-many association to Factura
		@ManyToMany(mappedBy="productos", fetch = FetchType.EAGER)
		private List<Factura> facturas;
	
	
	
	
	public Producto(int id, String nombre, int stock, String estado, Categoria categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.estado = estado;
		this.categoria = categoria;
	}




	public Producto(int id) {
		super();
		this.id = id;
	}


	public Producto() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

}
