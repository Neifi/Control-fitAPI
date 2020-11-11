package es.neifi.controlfitAPI.rest.model.dto;

import java.util.List;

import es.neifi.controlfitAPI.rest.model.registrohorario.RegistroHorario;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PutClienteDTO {
	
	private int id;
	private String dni;
	private String calle;
	private String ciudad;
	private String provincia;
	private String codigoPostal;
	private String email;
	
}
