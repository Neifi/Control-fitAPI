package es.neifi.utils

import es.neifi.controlfitAPI.rest.model.IdGenerator
import es.neifi.controlfitAPI.rest.model.cliente.ClientId
import es.neifi.controlfitAPI.rest.model.cliente.Cliente
import es.neifi.controlfitAPI.rest.services.UUIDGenerator
import io.mockk.InternalPlatformDsl.toStr
import java.time.OffsetDateTime
import kotlin.random.Random

class ClientGenerator( ) {

  companion object {
    val idGenerator: IdGenerator = UUIDGenerator()
    fun generateClient() = Cliente(
      "1",
      randomDni(),
      "nombre",
      "apellido",
      OffsetDateTime.now().toStr(),
      "email@mail.com",
      "calle",
      "02380",
      "Madrid",
      "Madrid",
      null,
      ClientId(idGenerator.generate() as String)
    )

    private fun randomDni() = Random
      .nextInt(10000000, 99999999)
      .toStr() + "Y"
  }


}