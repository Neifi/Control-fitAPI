package es.neifi.GestionGymAPI.rest.DTO;

import es.neifi.GestionGymAPI.rest.model.Rol;
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
	
}
