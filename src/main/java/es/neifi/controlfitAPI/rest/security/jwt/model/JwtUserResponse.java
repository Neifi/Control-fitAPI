package es.neifi.controlfitAPI.rest.security.jwt.model;

import java.util.Set;

import es.neifi.controlfitAPI.rest.model.dto.usuario.GetUserDTO;
import es.neifi.controlfitAPI.rest.model.rol.Rol;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDTO{
	private String token;
	
	@Builder(builderMethodName = "jwtUserResponseBuilder")
	public JwtUserResponse(String username, String password,String avatar, Set<String>roles, String token) {
		super(username,password,avatar,roles);
		this.token = token;
	}
}
