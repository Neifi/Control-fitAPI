package es.neifi.GestionGymAPI.rest.DTO;

import java.util.List;
import java.util.Set;

import es.neifi.GestionGymAPI.rest.model.Cliente;
import es.neifi.GestionGymAPI.rest.model.RegistroHorario;
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
public class GetUserDTO {
	private String username;
	private String password;
	private String avatar;
	private Set<String>roles;
	//private List<RegistroHorario> registroHorario;
}
