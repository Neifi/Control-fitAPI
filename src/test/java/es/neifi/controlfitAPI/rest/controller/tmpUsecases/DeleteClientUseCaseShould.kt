package es.neifi.controlfitAPI.rest.controller.tmpUsecases

import es.neifi.controlfitAPI.rest.exceptions.ClienteNotFoundException
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.ClientRepository
import es.neifi.utils.ClientGenerator.Companion.generateClient
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DeleteClientUseCaseShould {

  @MockK
  lateinit var clientRepository: ClientRepository

  @InjectMockKs
  private lateinit var deleteClientUseCase: DeleteClientUseCase

  @Test
  fun `delete existent client correctly`() {
    val deleteRequest = DeleteClientRequest("1")
    val clientId = ClientId("1")
    val mockKAdditionalAnswerScope = every {
      clientRepository.findById(clientId)
    } returns generateClient()
    every {
      clientRepository.deleteClient(clientId)
    } just runs

    deleteClientUseCase.execute(deleteRequest)

    verify {
      clientRepository.deleteClient(clientId)
    }
  }

  @Test
  fun `throw exception when client does not exist`() {
    val deleteRequest = DeleteClientRequest("1")

    every {
      clientRepository.findById(ClientId("1"))
    } returns null

    assertThrows<ClienteNotFoundException> {
      deleteClientUseCase.execute(deleteRequest)
    }
  }
}