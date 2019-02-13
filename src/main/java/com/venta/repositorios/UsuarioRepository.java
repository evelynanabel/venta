package com.venta.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.venta.proy.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	public Iterable<Usuario> findByEstado(String estado);
}

