package es.neifi.controlfitAPI.rest.model.cliente

interface ClientRepository : BaseRepository<Cliente, Id> {

  fun findById(id: Id): Cliente?
  fun save(cliente: Cliente)
  fun findByEmail(clientEmail: String): Cliente?
  fun deleteClient(clientId: ClientId)
  fun update(client: Cliente)
}