package es.neifi.controlfitAPI.rest.controller.tmpUsecases

import es.neifi.controlfitAPI.rest.exceptions.ClienteNotFoundException
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.ClientRepository
import es.neifi.controlfitAPI.rest.model.cliente.Cliente
import java.time.OffsetDateTime

class UpdateClienteUseCase(private val clientRepository: ClientRepository) {

  fun execute(request: UpdateClientRequest) {
    clientRepository.findById(ClientId(request.clientId))?.let {
      val client = Cliente(
        ClientId(request.clientId),
        it.gymId(),
        request.dni,
        request.nombre,
        request.apellidos,
        request.fechaNacimiento.toString(),
        request.email,
        request.calle,
        request.codigoPostal,
        request.ciudad,
        request.provincia
      )
      clientRepository.update(
        client
      )
    } ?: throw ClienteNotFoundException(ClientId(request.clientId))
  }
}

data class UpdateClientRequest(
  val clientId: String,
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
