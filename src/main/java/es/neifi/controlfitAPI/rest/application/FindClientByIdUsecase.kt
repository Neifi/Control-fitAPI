package es.neifi.controlfitAPI.rest.controller

import es.neifi.controlfitAPI.rest.exceptions.ClienteNotFoundException
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.ClientRepository

class FindClientByIdUsecase(private val clientRepository: ClientRepository) {
  fun execute(request: FindClientByIdRequest): FindClientByIdResponse {

     return clientRepository.findById(ClientId(request.clientId))?.let {
       FindClientByIdResponse(
         it.clientId().id,
         it.dni(),
         it.nombre(),
         it.apellidos(),
         it.fechaNacimiento(),
         it.email(),
         it.calle(),
         it.codigoPostal(),
         it.ciudad(),
         it.provincia()
       )
     } ?: throw ClienteNotFoundException(ClientId(request.clientId))

  }
}


data class FindClientByIdRequest(val clientId: String)
data class FindClientByIdResponse(
  val id: String,
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
