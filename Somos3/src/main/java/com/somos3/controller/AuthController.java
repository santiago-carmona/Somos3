package com.somos3.controller;
import com.somos3.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class AuthController {
    private final UsuarioService usuarioService;
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    public static class RegistroForm {
        @NotBlank(message = "Ingrese su nombre")
        @Size(max = 100)
        private String nombre;
        @NotBlank(message = "Ingrese su correo")
        @Email(message = "Correo no válido")
        @Size(max = 150)
        private String correo;
        @NotBlank(message = "Ingrese una contraseña")
        @Size(min = 8, max = 72,
              message = "Use entre 8 y 72 caracteres")
        private String contrasena;
        @NotBlank(message = "Confirme su contraseña")
        private String confirmarContrasena;
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public String getCorreo() {
            return correo;
        }
        public void setCorreo(String correo) {
            this.correo = correo;
        }
        public String getContrasena() {
            return contrasena;
        }
        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }
        public String getConfirmarContrasena() {
            return confirmarContrasena;
        }
        public void setConfirmarContrasena(String confirmarContrasena) {
            this.confirmarContrasena = confirmarContrasena;
        }
    }
    @GetMapping("/login")
    public String mostrarLogin() {
        return "auth/login";
    }
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("registroForm", new RegistroForm());
        return "auth/registro";
    }
    @PostMapping("/registro")
    public String registrar(
            @Valid @ModelAttribute("registroForm") RegistroForm form,
            BindingResult result,
            Model model,
            RedirectAttributes redirect) {
        if (form.getContrasena() != null
                && !form.getContrasena().equals(
                        form.getConfirmarContrasena())) {
            result.rejectValue(
                    "confirmarContrasena",
                    "error.confirmacion",
                    "Las contraseñas no coinciden");
        }
        if (result.hasErrors()) {
            return "auth/registro";
        }

        try {
            usuarioService.registrar(
                    form.getNombre(),
                    form.getCorreo(),
                    form.getContrasena());
            redirect.addFlashAttribute(
                    "mensaje",
                    "Cuenta creada. Inicie sesión.");
            return "redirect:/login";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "auth/registro";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error",
                    "Error al crear el usuario.");
            return "auth/registro";
        }
    }
}
