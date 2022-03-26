package es.neifi.controlfitAPI.rest.infrastructure.controller.cliente

import es.neifi.controlfitAPI.rest.controller.tmpUsecases.DeleteClientRequest
import es.neifi.controlfitAPI.rest.controller.tmpUsecases.DeleteClientUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus

class DeleteClientController(private val deleteClientUseCase: DeleteClientUseCase) {

  @DeleteMapping("client/{clientId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  fun deleteClient(@PathVariable clientId: String) {
    deleteClientUseCase.execute(DeleteClientRequest(clientId))
  }
}

