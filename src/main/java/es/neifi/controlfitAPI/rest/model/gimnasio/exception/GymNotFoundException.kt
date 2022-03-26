package es.neifi.controlfitAPI.rest.model.gimnasio.exception

import es.neifi.controlfitAPI.rest.model.employee.EmployeeId

class GymNotFoundException(val employeeId: EmployeeId):
  RuntimeException("Gym not found with employeeId: ${employeeId.id}")