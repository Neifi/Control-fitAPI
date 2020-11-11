package es.neifi.controlfitAPI.rest.model.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.neifi.controlfitAPI.rest.model.cliente.Cliente;
import es.neifi.controlfitAPI.rest.model.dto.CrearClienteDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateClienteDTOConverter {
	private final ModelMapper modelMapper;
	public Cliente convertToClient(CrearClienteDTO dto) {
		return  modelMapper.map(dto, Cliente.class);
		 
	}
	
	public CrearClienteDTO convertToDTO(Cliente cliente) {
		return modelMapper.map(cliente, CrearClienteDTO.class);
	}
}
