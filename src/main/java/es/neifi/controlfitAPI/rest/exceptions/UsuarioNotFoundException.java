package es.neifi.controlfitAPI.rest.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
	
	
	public UsuarioNotFoundException(String id) {
		super("No se ha encontrado al usuario con la id "+id);
	}
}
