package es.neifi.controlfitAPI.rest.infrastructure.controller.cliente

import es.neifi.controlfitAPI.rest.controller.tmpUsecases.DeleteClientUseCase
import es.neifi.controlfitAPI.rest.controller.tmpUsecases.UpdateClientRequest
import es.neifi.controlfitAPI.rest.controller.tmpUsecases.UpdateClienteUseCase
import java.time.OffsetDateTime
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ModifyClientController(
  private val modifyClientUseCase: UpdateClienteUseCase
) {

  @PutMapping("client/{clientId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  fun modifyClient(request: UpdateClientHttpRequest, @PathVariable clientId: String) {
    modifyClientUseCase.execute(
      UpdateClientRequest(
        clientId,
        request.dni,
        request.nombre,
        request.apellidos,
        request.fechaNacimiento,
        request.email,
        request.calle,
        request.codigoPostal,
        request.ciudad,
        request.provincia
      )
    )
  }

}

data class UpdateClientHttpRequest(
  val dni: String,
  val nombre: String,
  val apellidos: String,
  val fechaNacimiento: OffsetDateTime,
  val email: String,
  val calle: String,
  val codigoPostal: String,
  val ciudad: String,
  val provincia: String
)
