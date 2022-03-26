package es.neifi.controlfitAPI.rest.model.cliente

interface ClienteBuilder {

  fun withEmail(email: String): ClienteBuilder
  fun withDni(dni: String): ClienteBuilder
  fun withIdGimnasio(idGimnasio: Int): ClienteBuilder
  fun withNombre(nombre: String): ClienteBuilder
  fun withApellidos(apellidos: String): ClienteBuilder
  fun withFechanacimiento(fechaNacimiento: String): ClienteBuilder
  fun withCalle(calle: String): ClienteBuilder
  fun withCodigoPostal(codigoPostal: String): ClienteBuilder
  fun withCiudad(ciudad: String): ClienteBuilder
  fun withProvincia(provincia: String): ClienteBuilder
  fun create(): Cliente

}