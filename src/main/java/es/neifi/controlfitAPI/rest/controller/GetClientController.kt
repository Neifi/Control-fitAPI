package es.neifi.controlfitAPI.rest.controller

import es.neifi.controlfitAPI.rest.exceptions.ApiError
import es.neifi.controlfitAPI.rest.model.cliente.Cliente
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GetClientController(
  private val listClientsUsecase: ListClientsUsecase,
  private val findClientByIdUsecase: FindClientByIdUsecase
) {

  @ApiOperation(value = "Obtener lista completa de clientes", notes = "Obtener todos los clientes")
  @ApiResponses(
    value = [ApiResponse(code = 200, message = "OK", response = Cliente::class), ApiResponse(
      code = 404,
      message = "Not Found",
      response = ApiError::class
    ), ApiResponse(code = 500, message = "Internal Server Error", response = ApiError::class)]
  )
  @GetMapping(value = ["/cliente"])
  fun obtenerTodos(@RequestParam pag: Int, @RequestParam rpag: Int) =
  listClientsUsecase.execute(ListClientRequest(pag, rpag))

  @ApiOperation(
    value = "Obtener un cliente por la id",
    notes = "permite obtener datos de un solo cliente pasando la id"
  )
  @ApiResponses(
    value = [ApiResponse(code = 200, message = "OK", response = Cliente::class), ApiResponse(
      code = 404,
      message = "Not Found",
      response = ApiError::class
    ), ApiResponse(code = 500, message = "Internal Server Error", response = ApiError::class)]
  )
  @GetMapping("/cliente/{id}")
  fun obtenerUno(
    @ApiParam(value = "ID del cliente", required = true, type = "String") @PathVariable id: String
  ) = findClientByIdUsecase.execute(FindClientByIdRequest(id))

}