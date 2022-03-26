package es.neifi.controlfitAPI.rest.controller.tmpUsecases

import es.neifi.controlfitAPI.rest.model.IdGenerator
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.ClientRepository
import es.neifi.controlfitAPI.rest.model.cliente.Cliente
import es.neifi.controlfitAPI.rest.model.cliente.exception.ClientAlreadyExistException
import es.neifi.controlfitAPI.rest.model.employee.EmployeeId
import es.neifi.controlfitAPI.rest.model.gimnasio.Gym
import es.neifi.controlfitAPI.rest.model.gimnasio.GimnasioRepository
import es.neifi.controlfitAPI.rest.model.gimnasio.exception.GymNotFoundException
import es.neifi.utils.ClientGenerator
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.UUID
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreateClientUsecaseShould {

  @MockK
  private lateinit var gimnasioRepository: GimnasioRepository

  @MockK
  private lateinit var clientRepository: ClientRepository

  @MockK
  private lateinit var idGenerator: IdGenerator

  @InjectMockKs
  private lateinit var createClientUsecase: CreateClientUsecase

  @Test
  fun `register a client in current gym`() {
    val gymID = UUID.randomUUID().toString()
    val clientUuid = UUID.randomUUID().toString()
    val employeeId = UUID.randomUUID().toString()
    val request = aRegisterClientRequestWith(employeeId)
    val gimnasio = aGym(gymID)
    val expectedClient = Cliente(
      gymID,
      request.dni,
      request.nombre,
      request.apellidos,
      request.fechaNacimiento.toString(),
      request.email,
      request.calle,
      request.codigoPostal,
      request.ciudad,
      request.provincia,
      "1",
      ClientId(clientUuid)
    )

    every { idGenerator.generate() } returns clientUuid

    every {
      clientRepository.findByEmail(any())
    } returns null

    every {
      gimnasioRepository.findGymIdByEmployeeId(EmployeeId(employeeId))
    } returns gimnasio

    every {
      clientRepository.save(any())
    } just runs

    createClientUsecase.execute(request)

    verify {
      gimnasioRepository.findGymIdByEmployeeId(EmployeeId(employeeId))
      clientRepository.save(
        expectedClient
      )
    }
  }

  @Test
  fun `throw exception when gym does not exist`() {
    val employeeId = UUID.randomUUID().toString()

    every {
      clientRepository.findByEmail(any())
    } returns null

    every {
      gimnasioRepository.findGymIdByEmployeeId(EmployeeId(employeeId))
    } returns null

    assertThrows<GymNotFoundException> {
      createClientUsecase.execute(aRegisterClientRequestWith(employeeId))
    }
  }

  @Test
  fun `throw exception when client already exist`() {
    val gymID = 1
    val employeeId = UUID.randomUUID().toString()
    val clientEmail = "email@email.com"

    every {
      clientRepository.findByEmail(clientEmail)
    } returns ClientGenerator.generateClient()

    assertThrows<ClientAlreadyExistException> {
      createClientUsecase.execute(aRegisterClientRequestWith(employeeId))
    }
  }

  private fun aGym(gymID: String) = Gym(
    gymID,
    "gym name",
    "city",
    "street",
    23412,
    "province",
    "country"
  )

  private fun aRegisterClientRequestWith(employeeId: String) = CreateClientRequest(
    employeeId,
    "39172947Y",
    "name",
    "surname",
    OffsetDateTime.of(
      2022,
      1,
      1,
      15,
      0,
      0,
      0,
      ZoneOffset.UTC
    ),
    "email@email.com",
    "street",
    "83719",
    "city",
    "province"
  )
}