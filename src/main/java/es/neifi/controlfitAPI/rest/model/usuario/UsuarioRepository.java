package es.neifi.controlfitAPI.rest.model.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UsuarioRepository extends JpaRepository<Usuario,String>{
	
	Optional<Usuario> findByUsername(String username);
	

	
}
