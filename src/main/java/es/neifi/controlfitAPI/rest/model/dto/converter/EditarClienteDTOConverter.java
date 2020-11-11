package es.neifi.controlfitAPI.rest.model.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.neifi.controlfitAPI.rest.model.cliente.Cliente;
import es.neifi.controlfitAPI.rest.model.dto.CrearClienteDTO;
import es.neifi.controlfitAPI.rest.model.dto.EditarClienteDTO;
import es.neifi.controlfitAPI.rest.model.dto.usuario.DatosAccesoDTO;
import es.neifi.controlfitAPI.rest.model.dto.usuario.DatosContactoDTO;
import es.neifi.controlfitAPI.rest.model.dto.usuario.DatosPersonalesDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EditarClienteDTOConverter {
	private final ModelMapper modelMapper;
	public Cliente convertToClient(EditarClienteDTO dto) {
		return  modelMapper.map(dto, Cliente.class); 
	}
	public Cliente convertToClient(DatosPersonalesDTO dto) {
		return  modelMapper.map(dto, Cliente.class); 
	}
	public Cliente convertToClient(DatosAccesoDTO dto) {
		return  modelMapper.map(dto, Cliente.class); 
	}
	public Cliente convertToClient(DatosContactoDTO dto) {
		return  modelMapper.map(dto, Cliente.class); 
	}
	
	
}	
