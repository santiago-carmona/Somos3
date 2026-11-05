package com.somos3.service;
import com.somos3.domain.Usuario;
import com.somos3.repository.UsuarioRepository;
import java.util.Locale;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void registrar(String nombre, String correo,
                          String contrasena) {
        if (contrasena == null || contrasena.isBlank()) {
            throw new IllegalArgumentException(
                    "La contraseña es obligatoria");
        }
        String correoNormalizado =
                correo.trim().toLowerCase(Locale.ROOT);
        if (usuarioRepository.existsByCorreo(correoNormalizado)) {
            throw new IllegalArgumentException(
                    "Este correo ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre.trim());
        usuario.setCorreo(correoNormalizado);
        usuario.setContrasena(passwordEncoder.encode(contrasena));
        usuario.setRol("CLIENTE");
        usuario.setEstado(true);
        usuarioRepository.save(usuario);
    }
    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {
        String correoNormalizado =
               correo.trim().toLowerCase(Locale.ROOT);
        Usuario usuario = usuarioRepository
                .findByCorreo(correoNormalizado)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "El usuario no fue encontrado"));
        return User.withUsername(usuario.getCorreo())
                .password(usuario.getContrasena())
                .roles(usuario.getRol())
                .disabled(!Boolean.TRUE.equals(usuario.getEstado()))
                .build();
    }
}
