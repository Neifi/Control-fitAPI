package es.neifi.controlfitAPI.rest.model

import es.neifi.controlfitAPI.rest.model.cliente.Id
import java.util.Objects

interface IdGenerator {
  public fun generate(): Any
}