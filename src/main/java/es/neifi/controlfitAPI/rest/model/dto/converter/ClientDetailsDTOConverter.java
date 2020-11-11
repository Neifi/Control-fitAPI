package es.neifi.controlfitAPI.rest.model.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.neifi.controlfitAPI.rest.model.cliente.Cliente;
import es.neifi.controlfitAPI.rest.model.dto.InfoClienteDTO;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor
public class ClientDetailsDTOConverter {
	private final ModelMapper modelMapper;
	public InfoClienteDTO convertToDTO(Cliente cliente) {
		return modelMapper.map(cliente, InfoClienteDTO.class);
	}
	
}
