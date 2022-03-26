package es.neifi.controlfitAPI.rest.controller.tmpUsecases

import es.neifi.controlfitAPI.rest.model.IdGenerator
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.ClientRepository
import es.neifi.controlfitAPI.rest.model.cliente.Cliente
import es.neifi.controlfitAPI.rest.model.cliente.exception.ClientAlreadyExistException
import es.neifi.controlfitAPI.rest.model.employee.EmployeeId
import es.neifi.controlfitAPI.rest.model.gimnasio.GimnasioRepository
import es.neifi.controlfitAPI.rest.model.gimnasio.exception.GymNotFoundException
import java.time.OffsetDateTime


class CreateClientUsecase(
  private val clientRepository: ClientRepository,
  private val gimnasioRepository: GimnasioRepository,
  private val idGenerator: IdGenerator
) {
  fun execute(request: CreateClientRequest) {

    guardAgainstExistentClient(request)

    val gym = gimnasioRepository.let {
      it.findGymIdByEmployeeId(EmployeeId(request.registratorId))
    } ?: throw GymNotFoundException(EmployeeId(request.registratorId))

    val gymId = gym.gymId()
    val cliente = Cliente(
      gymId,
      request.dni,
      request.nombre,
      request.apellidos,
      request.fechaNacimiento.toString(),
      request.email,
      request.calle,
      request.codigoPostal,
      request.ciudad,
      request.provincia,
      "1",//TODO
      ClientId(idGenerator.generate() as String)
    )
    clientRepository.save(cliente)

  }

  private fun guardAgainstExistentClient(request: CreateClientRequest) {
    if (clientRepository.findByEmail(request.email) != null) {
      throw ClientAlreadyExistException(request.email)
    }
  }

}

data class CreateClientRequest(
  val registratorId: String,
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
