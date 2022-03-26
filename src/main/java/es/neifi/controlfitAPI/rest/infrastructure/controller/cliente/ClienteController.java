package es.neifi.controlfitAPI.rest.infrastructure.controller.cliente;

import es.neifi.controlfitAPI.rest.exceptions.ApiError;
import es.neifi.controlfitAPI.rest.infrastructure.controller.UsuarioController;
import es.neifi.controlfitAPI.rest.model.cliente.Cliente;
import es.neifi.controlfitAPI.rest.model.cliente.ClienteJPARepository;
import es.neifi.controlfitAPI.rest.model.gimnasio.GimnasioRepository;
import es.neifi.controlfitAPI.rest.model.registrohorario.RegistroHorarioRepository;
import es.neifi.controlfitAPI.rest.model.usuario.Usuario;
import es.neifi.controlfitAPI.rest.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteJPARepository clienteJPARepository;
  private final UsuarioController usuarioController;
  private final GimnasioRepository gimnasioRepository;
  private final RegistroHorarioRepository registroHorarioRepository;
  private final UsuarioService usuarioService;

  @ApiOperation(value = "Edita un cliente", notes = "Permite editar un cliente por la autenticacion")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Cliente.class),
          @ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
          @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class)})
  @PutMapping("/cliente")
  public void modificarCliente(
          @ApiParam(value = "Datos del cliente", required = true, type = "JSON") @RequestBody Cliente cliente,
          @AuthenticationPrincipal Usuario usuario) {
/*
    cliente.setId(usuario.getId_usuario());
    System.out.println(cliente.getCalle());*/
    // return clienteJPARepository.save(cliente);

  }

  @ApiOperation(value = "Edita un cliente", notes = "Permite editar un cliente por la id")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Cliente.class),
          @ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
          @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class)})

  @PutMapping("/cliente/{id}")
  public void modificarClienteporId(
          @ApiParam(value = "Datos del cliente", required = true, type = "JSON") @RequestBody Cliente cliente,
          @PathVariable int id) {

    // cliente.setId(id);
    clienteJPARepository.save(cliente);

  }

  @ApiOperation(value = "Borrar cliente", notes = "Permite borrar un cliente por la id")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Cliente.class),
          @ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
          @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class)})
  @DeleteMapping("/cliente/{id}")
  public ResponseEntity<?> borrarProducto(
          @ApiParam(value = "ID del cliente", required = true, type = "String") @PathVariable String id) {
    // Cliente borrar = clienteJPARepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
    registroHorarioRepository.deleteByIdUsuario(id);
    //clienteJPARepository.delete(borrar);
    usuarioService.deleteById(id);
    return ResponseEntity.noContent().build();
  }


}
