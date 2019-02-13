package com.venta.servicios.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.venta.proy.Categoria;
import com.venta.proy.Cliente;
import com.venta.proy.Factura;
import com.venta.proy.Producto;
import com.venta.proy.Rol;
import com.venta.proy.Usuario;
import com.venta.repositorios.CategoriaRepository;
import com.venta.repositorios.ClienteRepository;
import com.venta.repositorios.FacturaRepository;
import com.venta.repositorios.ProductoRepository;
import com.venta.repositorios.RolRepository;
import com.venta.repositorios.UsuarioRepository;
import com.venta.servicios.ServicioVenta;

@Service
public class ServicioVentaJPA implements ServicioVenta {

	@Autowired
	private ProductoRepository repoproducto;
	@Autowired
	private CategoriaRepository repocategoria;
	@Autowired
	private ClienteRepository repocliente;
	@Autowired
	private UsuarioRepository repousuario;
	@Autowired
	private RolRepository reporol;
	@Autowired
	private FacturaRepository repofactura;

	// producto
	public ProductoRepository getRepoproducto() {
		return repoproducto;
	}

	public void setRepoproducto(ProductoRepository repoproducto) {
		this.repoproducto = repoproducto;
	}

	// categoria
	public CategoriaRepository getRepocategoria() {
		return repocategoria;
	}

	public void setRepocategoria(CategoriaRepository repocategoria) {
		this.repocategoria = repocategoria;
	}

	// Cliente
	public ClienteRepository getRepocliente() {
		return repocliente;
	}

	public void setRepocliente(ClienteRepository repocliente) {
		this.repocliente = repocliente;
	}

	// Usuario
	public UsuarioRepository getRepousuario() {
		return repousuario;
	}

	public void setRepousuario(UsuarioRepository repousuario) {
		this.repousuario = repousuario;
	}

	// Rol
	public RolRepository getReporol() {
		return reporol;
	}

	public void setReporol(RolRepository reporol) {
		this.reporol = reporol;
	}

	// factura
	public FacturaRepository getRepofactura() {
		return repofactura;
	}

	public void setRepofactura(FacturaRepository repofactura) {
		this.repofactura = repofactura;
	}

	// producto
	public Producto findOneProd(Integer id) {
		return repoproducto.findOne(id);
	}

	public Iterable<Producto> findAllProd() {
		return repoproducto.findAll();
	}

	@Transactional
	public void saveProd(Producto producto) {
		repoproducto.save(producto);
	}

	@Transactional
	public void deleteProd(Producto producto) {
		repoproducto.delete(producto);
	}

	// categoria

	public Categoria findOneCat(Integer id) {
		return repocategoria.findOne(id);
	}

	public Iterable<Categoria> findAllCat() {
		return repocategoria.findAll();
	}

	@Transactional
	public void  saveCat(Categoria categoria) {
		repocategoria.save(categoria);
	}

	@Transactional
	public void deleteCat(Categoria categoria) {
		repocategoria.delete(categoria);
	}

	// cliente
	public Cliente findOneCli(Integer id) {
		return repocliente.findOne(id);
	}

	public Iterable<Cliente> findAllCli() {
		return repocliente.findAll();
	}

	@Transactional
	public void saveCli(Cliente cliente) {
		repocliente.save(cliente);
	}

	@Transactional
	public void deleteCli(Cliente cliente) {
		repocliente.delete(cliente);
	}

	// usuario
	public Usuario findOneUsu(Integer id) {
		return repousuario.findOne(id);
	}

	public Iterable<Usuario> findAllUsu() {
		return repousuario.findAll();
	}

	@Transactional
	public void saveUsu(Usuario usuario) {
		repousuario.save(usuario);
	}

	@Transactional
	public void deleteUsu(Usuario usuario) {
		repousuario.delete(usuario);
	}

	// rol
	public Rol findOneRol(Integer id) {
		return reporol.findOne(id);
	}

	public Iterable<Rol> findAllRol() {
		return reporol.findAll();
	}

	@Transactional
	public void saveRol(Rol rol) {
		reporol.save(rol);
	}

	@Transactional
	public void deleteRol(Rol rol) {
		reporol.delete(rol);
	}

	// factura
	public Factura findOneFac(Integer id) {
		return repofactura.findOne(id);
	}

	public Iterable<Factura> findAllFac() {
		return repofactura.findAll();
	}

	@Transactional
	public void saveFac(Factura factura) {
		repofactura.save(factura);
	}

	@Transactional
	public void deleteFac(Factura factura) {
		repofactura.delete(factura);
	}

	@Override
	public Iterable<Producto> findByEstado(String estado) {
		return repoproducto.findByEstado(estado);
	}
	
	@Override
	public Iterable<Cliente> findByEstadoCli(String estado) {
		return repocliente.findByEstado(estado);
	}
	
	@Override
	public Iterable<Usuario> findByEstadoUsu(String estado) {
		return repousuario.findByEstado(estado);
	}

}
