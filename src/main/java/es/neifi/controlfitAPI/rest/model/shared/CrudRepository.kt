package es.neifi.controlfitAPI.rest.model.shared

import es.neifi.controlfitAPI.rest.model.cliente.DomainEntity
import es.neifi.controlfitAPI.rest.model.cliente.Id

interface CrudRepository<entity : DomainEntity> {
  fun create(entity: entity)
  fun findBy(id: Id): entity
  fun update(entity: entity)
  fun delete(id: Id)
}
