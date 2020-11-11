package es.neifi.controlfitAPI.rest.model.dto.usuario;

import org.springframework.context.annotation.Bean;

import es.neifi.controlfitAPI.rest.model.rol.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SetAvatarUsuarioDTO {
	private String url;
	
}
