package es.neifi.controlfitAPI.rest.controller

import es.neifi.controlfitAPI.rest.model.cliente.ClienteJPARepository
import org.springframework.data.domain.PageRequest

class ListClientsUsecase(private val clienteJPARepository: ClienteJPARepository) {
  fun execute(request: ListClientRequest): ListClienteResponse {
    return clienteJPARepository.findAllByOrderByIdAsc(
      PageRequest.of(request.pag, request.rpag)
    ).map {
        ClienteResponse(
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
    }.let { ListClienteResponse(it.toList()) }
  }
}

data class ListClientRequest(val pag: Int, val rpag: Int)
data class ListClienteResponse(val clients: List<ClienteResponse>)

data class ClienteResponse(
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
