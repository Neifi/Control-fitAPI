package es.neifi.controlfitAPI.rest.controller.tmpUsecases

import es.neifi.controlfitAPI.rest.controller.FindClientByIdRequest
import es.neifi.controlfitAPI.rest.controller.FindClientByIdResponse
import es.neifi.controlfitAPI.rest.controller.FindClientByIdUsecase
import es.neifi.controlfitAPI.rest.exceptions.ClienteNotFoundException
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.ClientRepository
import es.neifi.utils.ClientGenerator
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class FindClientByIdUsecaseShould {

  @MockK
  private lateinit var clientRepository: ClientRepository

  @InjectMockKs
  private lateinit var findClientByIdUsecase: FindClientByIdUsecase

  @Test
  fun `return existent client`() {
    val cliente = ClientGenerator.generateClient()
    val clienteExpectedResponse = FindClientByIdResponse(
      cliente.clientId().id,
      cliente.dni(),
      cliente.nombre(),
      cliente.apellidos(),
      cliente.fechaNacimiento(),
      cliente.email(),
      cliente.calle(),
      cliente.codigoPostal(),
      cliente.ciudad(),
      cliente.provincia()
    )
    every {
      clientRepository.findById(cliente.clientId())
    } returns cliente

    val response = findClientByIdUsecase
      .execute(FindClientByIdRequest(cliente.clientId().id))

    assertEquals(clienteExpectedResponse,response)
  }

  @Test
   fun `throw exception when client does not exist`() {
    every {
      clientRepository.findById(ClientId("id"))
    } returns null

    assertThrows<ClienteNotFoundException> {
      findClientByIdUsecase.execute(FindClientByIdRequest("id"))
    }
  }
}

