package es.neifi.controlfitAPI.rest.model.gimnasio;

import es.neifi.controlfitAPI.rest.model.cliente.ClientId;
import es.neifi.controlfitAPI.rest.model.employee.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GimnasioRepository extends JpaRepository<Gym, Long> {

  @Query(value = "SELECT id_gimnasio from gimnasio where employee_id = ?", nativeQuery = true)
  Gym findGymIdByEmployeeId(EmployeeId clientId);

  @Query(value = "UPDATE gym SET clients = WHERE id_gimnasio = ?", nativeQuery = true)
  void registerClient(List<ClientId> clientIds, int gymId);
}
