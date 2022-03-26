package es.neifi.controlfitAPI.rest.controller.tmpUsecases

import es.neifi.controlfitAPI.rest.model.IdGenerator
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.ClientRepository
import es.neifi.utils.ClientGenerator
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import java.time.OffsetDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UpdateClienteUseCaseShould {

  @MockK
  lateinit var idGenerator: IdGenerator

  @MockK
  lateinit var clientRepository: ClientRepository

  @InjectMockKs
  lateinit var updateClienteUseCase: UpdateClienteUseCase

  @Test
  fun `update existent client`() {
    val client = ClientGenerator.generateClient()
    val request = UpdateClientRequest(
      client.clientId().id,
      client.dni(),
      client.nombre(),
      client.apellidos(),
      OffsetDateTime.parse(client.fechaNacimiento()),
      client.email(),
      client.calle(),
      client.codigoPostal(),
      client.ciudad(),
      client.provincia()
    )

    every {
      idGenerator.generate()
    } returns client.clientId()

    every {
      clientRepository.findById(client.clientId())
    } returns client
    every {
      clientRepository.update(any())
    } just runs

    updateClienteUseCase.execute(request)

    verify {
      clientRepository.update(client)
    }
  }

  @Test
  fun `throw exception when client does not exist`() {

  }
}