package es.neifi.GestionGymAPI.rest.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonMappingException;

import es.neifi.GestionGymAPI.rest.model.Cliente;
import es.neifi.GestionGymAPI.rest.DTO.InfoClienteDTO;
import es.neifi.GestionGymAPI.rest.DTO.CrearClienteDTO;
import es.neifi.GestionGymAPI.rest.DTO.CrearUsuarioDTO;
import es.neifi.GestionGymAPI.rest.DTO.EditarClienteDTO;
import es.neifi.GestionGymAPI.rest.DTO.converter.ClientDetailsDTOConverter;
import es.neifi.GestionGymAPI.rest.DTO.converter.CreateClienteDTOConverter;
import es.neifi.GestionGymAPI.rest.DTO.converter.EditClientByAdminDTOConverter;
import es.neifi.GestionGymAPI.rest.exceptions.ApiError;
import es.neifi.GestionGymAPI.rest.exceptions.ClienteNotFoundException;
import es.neifi.GestionGymAPI.rest.model.ClienteRepository;
import es.neifi.GestionGymAPI.rest.model.Rol;
import es.neifi.GestionGymAPI.rest.model.Usuario;
import es.neifi.GestionGymAPI.services.ServerTime;
import es.neifi.GestionGymAPI.services.UsuarioService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({ "/api" })
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteRepository clienteRepository;
	private final ClientDetailsDTOConverter clienteDetailsDTOConverter;
	private final CreateClienteDTOConverter createClienteDTOConverter;
	private final EditClientByAdminDTOConverter editClientByAdminDTOConverter;
	//private final AltaUsuarioService altaUsuarioService;

	/**
	 * Obtener todos los clientes
	 * 
	 * @return 404 si no hay ningun cliente 200 si ha encontrado algún cliente
	 */
	@ApiOperation(value = "Obtener lista completa de clientes", notes = "Obtener todos los clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Cliente.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })

	@GetMapping("/cliente")
	public ResponseEntity<?> obtenerTodos() {

		List<Cliente> clientes = clienteRepository.findAll();
		if (clientes.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay clientes registrados");
		} else {
			List<InfoClienteDTO> dtoList = clientes.stream().map(clienteDetailsDTOConverter::convertToDTO)
					.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}

	}

	@ApiOperation(value = "Obtener un cliente por la id", notes = "permite obtener datos de un solo cliente pasando la id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Cliente.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@GetMapping("/cliente/{id}")
	public Cliente obtenerUno(
			@ApiParam(value = "ID del cliente", required = true, type = "Integer") @PathVariable int id) {
		try {

			return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		} catch (ClienteNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@ApiOperation(value = "Añadir cliente", notes = "Permite añadir un cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Cliente.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@PostMapping("/cliente")
	public ResponseEntity<CrearClienteDTO> crearCliente(
			@ApiParam(value = "Datos del cliente", required = true, type = "JSON") @RequestBody CrearClienteDTO nuevo) {
		Cliente saved = createClienteDTOConverter.convertToClient(nuevo);
		clienteRepository.save(saved);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
	}

	@ApiOperation(value = "Edita un cliente", notes = "Permite editar un cliente por la id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Cliente.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@PutMapping("/cliente/{id}")
	public Cliente modificarCliente(
			@ApiParam(value = "Datos del cliente", required = true, type = "JSON") @RequestBody EditarClienteDTO editDTO,
			@ApiParam(value = "ID del cliente", required = true, type = "Integer") @PathVariable Integer id) {

		return clienteRepository.findById(id).map(p -> {
			p = editClientByAdminDTOConverter.convertToClient(editDTO);
			return clienteRepository.save(p);
		}).orElseThrow(() -> new ClienteNotFoundException(id));

	}

	@ApiOperation(value = "Borrar cliente", notes = "Permite borrar un cliente por la id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Cliente.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> borrarProducto(
			@ApiParam(value = "ID del cliente", required = true, type = "Integer") @PathVariable int id) {
		Cliente borrar = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		clienteRepository.delete(borrar);
		return ResponseEntity.noContent().build();
	}

	private String generarPassword(Cliente c) {
		String apellido = c.getApellidos();
		String nombre = c.getNombre();
		String password = nombre.substring(0).toUpperCase().concat(apellido.toLowerCase()).concat(".123");
		return password;

	};

	private String generarUsername(Cliente c) {
		String username = c.getDni().substring(6, 9).toLowerCase();

		return username;
	}

	private Usuario buildUsuario(Cliente c) {

		return Usuario.builder().verificado(false).cliente(c).avatar(null).username(generarUsername(c))
				.password(generarPassword(c)).build();
	}

	public void altaUsuario() {
		
	}

}
