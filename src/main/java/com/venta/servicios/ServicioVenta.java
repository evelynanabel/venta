package com.venta.servicios;

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

public interface ServicioVenta {
	
	// producto
	
	ProductoRepository getRepoproducto();

	void setRepoproducto(ProductoRepository repoproducto);
	
//categoria
	CategoriaRepository getRepocategoria();

	void setRepocategoria(CategoriaRepository repocategoria);
	
	// cliente
	ClienteRepository getRepocliente();

	void setRepocliente(ClienteRepository repocliente);
	
	//usuario
	UsuarioRepository getRepousuario();

	void setRepousuario(UsuarioRepository repousuario);
	
	//rol
	RolRepository getReporol();

	void setReporol(RolRepository reporol);
	
	//factura
	FacturaRepository getRepofactura();

	void setRepofactura(FacturaRepository repofactura);
	
	
	
	
// producto
	Producto findOneProd(Integer id);

	Iterable<Producto> findAllProd();

	void saveProd(Producto producto);

	void deleteProd(Producto producto);
	Iterable<Producto> findByEstado(String estado);
	
	
	//categoria
	Categoria findOneCat(Integer id);

	Iterable<Categoria> findAllCat();

	void saveCat(Categoria categoria);

	void deleteCat(Categoria categoria);
	
	
	
	// Cliente
	Cliente findOneCli(Integer id);

	Iterable<Cliente> findAllCli();

	void saveCli(Cliente cliente);

	void deleteCli(Cliente cliente);
	
	Iterable<Cliente> findByEstadoCli(String estado);
	
	
	//Usuario
	Usuario findOneUsu(Integer id);

	Iterable<Usuario> findAllUsu();

	void saveUsu(Usuario usuario);

	void deleteUsu(Usuario usuario);
	
	Iterable<Usuario> findByEstadoUsu(String estado);
	
	//Rol
	Rol findOneRol(Integer id);

	Iterable<Rol> findAllRol();

	void saveRol(Rol rol);

	void deleteRol(Rol rol);
	
	
	
	//Factura
	Factura findOneFac(Integer id);

	Iterable<Factura> findAllFac();

	void saveFac(Factura factura);

	void deleteFac(Factura factura);
	
	//Iterable<Factura> findByEstadoFac(String estado);

}