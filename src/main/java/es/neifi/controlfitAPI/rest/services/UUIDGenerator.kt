package es.neifi.controlfitAPI.rest.services

import es.neifi.controlfitAPI.rest.model.IdGenerator

import java.util.UUID

class UUIDGenerator: IdGenerator {
  override fun generate() = UUID.randomUUID().toString()
}