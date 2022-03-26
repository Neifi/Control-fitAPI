package es.neifi.controlfitAPI.rest.model.employee

import es.neifi.controlfitAPI.rest.model.cliente.DomainEntity

data class Employee(
  val companyId: String,
  val employeeId: EmployeeId,
  val nationalId: String,
  val name: String,
  val surnames: String,
  val birthDate: String,
  val email: String,
  val address: Address,
) : DomainEntity

data class Address(
  val country: String,
  val state: String,
  val city: String,
  val street: String,
  val zipCode: String
)