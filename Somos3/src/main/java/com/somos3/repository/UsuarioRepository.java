package com.somos3.repository;
import com.somos3.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
