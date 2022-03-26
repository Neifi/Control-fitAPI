package es.neifi.controlfitAPI.rest.model.cliente.exception

class ClientAlreadyExistException(email:String):
  RuntimeException("Client with email $email al ready exist") {
}