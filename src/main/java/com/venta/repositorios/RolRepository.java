package com.venta.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.venta.proy.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {
}

