package es.neifi.GestionGymAPI.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.neifi.GestionGymAPI.rest.DTO.CrearUsuarioDTO;
import es.neifi.GestionGymAPI.rest.model.Rol;
import es.neifi.GestionGymAPI.rest.model.Usuario;
import es.neifi.GestionGymAPI.rest.model.UsuarioRepository;
import es.neifi.GestionGymAPI.service.baseServices.BaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Integer,UsuarioRepository>{
	
	private final PasswordEncoder passwordEncoder;
	
	public Optional<Usuario> findByUsername(String username){
		return this.repositorio.findByUsername(username);
	}
	
	public Usuario nuevoUsuario (CrearUsuarioDTO usuario) {
		
		 
			Usuario newUsuario = Usuario.builder()
					.username(usuario.getUsername())
					.password(passwordEncoder.encode(usuario.getPassword()))
					.avatar(null)
					.rol(Stream.of(Rol.UNVERIFIED).collect(Collectors.toSet()))
					.build();
			try {
				Usuario toReturn = save(newUsuario);
				return toReturn;
			} catch (DataIntegrityViolationException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nombre de usuario existente");
			}
		
	}
}
