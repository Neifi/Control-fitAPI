package es.neifi.controlfitAPI.rest.controller.tmpUsecases

import es.neifi.controlfitAPI.rest.exceptions.ClienteNotFoundException
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.ClientRepository

class DeleteClientUseCase(private val clientRepository: ClientRepository) {

  fun execute(request: DeleteClientRequest) {
    val clientId = ClientId(request.clientId)
    clientRepository.findById(clientId)?.let {
      clientRepository.deleteClient(clientId)
    } ?: throw ClienteNotFoundException(clientId)
  }
}

data class DeleteClientRequest(val clientId: String)
