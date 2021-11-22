package com.salesianos.triana.realestate.v2.usuario.repository;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
