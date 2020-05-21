package es.neifi.GestionGymAPI.rest.DTO.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


import es.neifi.GestionGymAPI.rest.DTO.GetUserDTO;
import es.neifi.GestionGymAPI.rest.model.RegistroHorario;
import es.neifi.GestionGymAPI.rest.model.Rol;
import es.neifi.GestionGymAPI.rest.model.Usuario;
import es.neifi.GestionGymAPI.rest.security.jwt.model.JwtUserResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioDTOConverter {


	public GetUserDTO convertUserToGetUserDTO(Usuario usuario) {
		return GetUserDTO.builder()
				.username(usuario.getUsername())
				.avatar(usuario.getAvatar())
				//.cliente(usuario.getCliente())
				//.registroHorario(usuario.getRegistrohorario())
				.build();
			
				
	}
	
	public Object convertUserAndTokenToJwtUserResponse(Usuario usuario, String token) {
		// TODO Auto-generated method stub
		return JwtUserResponse.jwtUserResponseBuilder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.avatar(usuario.getAvatar())
				.roles(usuario.getRol().stream().map(Rol::name).collect(Collectors.toSet()))
				.token(token)
				.build();
	}
}
