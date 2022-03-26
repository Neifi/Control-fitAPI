package es.neifi.controlfitAPI.rest.controller

import es.neifi.controlfitAPI.rest.model.cliente.Cliente
import es.neifi.controlfitAPI.rest.model.cliente.ClienteJPARepository
import es.neifi.utils.ClientGenerator
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

@ExtendWith(MockKExtension::class)
class GetClientListUseCaseShould {

  @InjectMockKs
  private lateinit var listClientsUsecase: ListClientsUsecase

  @MockK
  private lateinit var clienteJPARepository: ClienteJPARepository

  @Test
  fun `return client list with pagination`() {
    val cliente1 = ClientGenerator.generateClient()
    val cliente2 = ClientGenerator.generateClient()

    val clienteResponse1 = ClienteResponse(
      dni = cliente1.dni(),
      nombre = "nombre",
      apellidos = "apellido",
      fechaNacimiento = "01/02/1996",
      email = "email@mail.com",
      calle = "calle",
      codigoPostal = "02380",
      ciudad = "Madrid",
      provincia = "Madrid"
    )
    val clienteResponse2 = ClienteResponse(
      dni = cliente2.dni(),
      nombre = "nombre",
      apellidos = "apellido",
      fechaNacimiento = "01/02/1996",
      email = "email@mail.com",
      calle = "calle",
      codigoPostal = "02380",
      ciudad = "Madrid",
      provincia = "Madrid"
    )
    val expectedClientsResponse = ListClienteResponse(listOf(clienteResponse1, clienteResponse2))
    val clients: List<Cliente> = listOf(cliente1,cliente2)
    every {
      clienteJPARepository.findAllByOrderByIdAsc(PageRequest.of(1,1))
    } returns PageImpl(clients)

    val currentClientResponse = listClientsUsecase.execute(ListClientRequest(1, 1))

    assertEquals(expectedClientsResponse, currentClientResponse)
  }

}