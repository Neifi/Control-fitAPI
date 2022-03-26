package es.neifi.controlfitAPI.rest.infrastructure.controller

import es.neifi.controlfitAPI.rest.controller.tmpUsecases.CreateClientRequest
import es.neifi.controlfitAPI.rest.controller.tmpUsecases.CreateClientUsecase
import es.neifi.controlfitAPI.rest.exceptions.ApiError
import es.neifi.controlfitAPI.rest.model.cliente.Cliente
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import java.time.OffsetDateTime
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateClientController(private val createClienteUsecase: CreateClientUsecase) {

  @ApiOperation(
    value = "Añadir cliente", notes = "Permite añadir un cliente, al ser añadido"
      + " se crea automaticamente un usuario con el nombre del cliente como credencias, tanto de usuario como"
      + " de contraseña, la id del gimnasio se obtiene a partir del usuario que le dio de alta que solo podrá "
      + " ser un admin"
  )
  @ApiResponses(
    value = [ApiResponse(code = 200, message = "OK", response = Cliente::class), ApiResponse(
      code = 404,
      message = "Not Found",
      response = ApiError::class
    ), ApiResponse(code = 500, message = "Internal Server Error", response = ApiError::class)]
  )
  @PostMapping("/cliente")
  @ResponseStatus(HttpStatus.CREATED)
  fun crearCliente(
    @ApiParam(value = "Datos del cliente", required = true, type = "JSON") @RequestBody client: ClienteJsonRequest,
    @AuthenticationPrincipal employee: Authentication
  ) {

    createClienteUsecase.execute(
      CreateClientRequest(
        employee.details.toString(),
        client.dni,
        client.nombre,
        client.apellidos,
        OffsetDateTime.parse(client.fechaNacimiento),
        client.email,
        client.calle,
        client.codigoPostal,
        client.ciudad,
        client.provincia
      )
    )
  }

}

data class ClienteJsonRequest(
  val registratorId: String,
  val dni: String,
  val nombre: String,
  val apellidos: String,
  val fechaNacimiento: String,
  val email: String,
  val calle: String,
  val codigoPostal: String,
  val ciudad: String,
  val provincia: String
)
