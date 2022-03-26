package es.neifi.controlfitAPI.rest.model.cliente

import java.util.Objects

class Cliente : DomainEntity {
  private var clientId: ClientId
  private var gymId: String
  private var dni: String
  private var nombre: String
  private var apellidos: String
  private var fechaNacimiento: String
  private var email: String
  private var calle: String
  private var codigoPostal: String
  private var ciudad: String
  private var provincia: String
  private var userId: String? = null

  constructor(
    gymId: String,
    dni: String,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    email: String,
    calle: String,
    codigoPostal: String,
    ciudad: String,
    provincia: String,
    userId: String?,
    clientId: ClientId
  ) {
    this.gymId = gymId
    this.dni = dni
    this.nombre = nombre
    this.apellidos = apellidos
    this.fechaNacimiento = fechaNacimiento
    this.email = email
    this.calle = calle
    this.codigoPostal = codigoPostal
    this.ciudad = ciudad
    this.provincia = provincia
    this.clientId = clientId
    this.userId = userId
  }

  constructor(
    clientId: ClientId,
    gymId: String,
    dni: String,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    email: String,
    calle: String,
    codigoPostal: String,
    ciudad: String,
    provincia: String
  ) {
    this.clientId = clientId
    this.gymId = gymId
    this.dni = dni
    this.nombre = nombre
    this.apellidos = apellidos
    this.fechaNacimiento = fechaNacimiento
    this.email = email
    this.calle = calle
    this.codigoPostal = codigoPostal
    this.ciudad = ciudad
    this.provincia = provincia
  }

  fun clientId(): ClientId {
    return clientId
  }

  fun gymId(): String {
    return gymId
  }

  fun dni(): String {
    return dni
  }

  fun nombre(): String {
    return nombre
  }

  fun apellidos(): String {
    return apellidos
  }

  fun fechaNacimiento(): String {
    return fechaNacimiento
  }

  fun email(): String {
    return email
  }

  fun calle(): String {
    return calle
  }

  fun codigoPostal(): String {
    return codigoPostal
  }

  fun ciudad(): String {
    return ciudad
  }

  fun provincia(): String {
    return provincia
  }

  fun userId(): String? {
    return userId
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Cliente) return false

    if (clientId != other.clientId) return false
    if (gymId != other.gymId) return false
    if (dni != other.dni) return false
    if (nombre != other.nombre) return false
    if (apellidos != other.apellidos) return false
    if (fechaNacimiento != other.fechaNacimiento) return false
    if (email != other.email) return false
    if (calle != other.calle) return false
    if (codigoPostal != other.codigoPostal) return false
    if (ciudad != other.ciudad) return false
    if (provincia != other.provincia) return false
    if (userId != other.userId) return false

    return true
  }

  override fun hashCode(): Int {
    var result = clientId.hashCode()
    result = 31 * result + gymId.hashCode()
    result = 31 * result + dni.hashCode()
    result = 31 * result + nombre.hashCode()
    result = 31 * result + apellidos.hashCode()
    result = 31 * result + fechaNacimiento.hashCode()
    result = 31 * result + email.hashCode()
    result = 31 * result + calle.hashCode()
    result = 31 * result + codigoPostal.hashCode()
    result = 31 * result + ciudad.hashCode()
    result = 31 * result + provincia.hashCode()
    result = 31 * result + (userId?.hashCode() ?: 0)
    return result
  }


}