package es.neifi.controlfitAPI.rest.model.dto;

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
@Builder
public class CrearUsuarioDTO {
	private String username;
	private String avatar;
	private String password;
	//private Rol rol;
	
}
