package es.neifi.controlfitAPI.rest.model.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ClienteJPARepository extends PagingAndSortingRepository, ClientRepository{

  @Modifying
  @Query("UPDATE Cliente c SET c.email = :email WHERE c.id = :id")
  int updateDatosContacto(@Param("email") String email, @Param("id") int id);

  @Modifying
  @Query(value = "UPDATE Cliente c SET c.nombre = ?, c.apellidos = ?, c.fecha_nacimiento = ?, c.dni = ?," +
          " c.calle = ?, c.ciudad= ?, c.provincia = ?, c.codigo_postal = ? WHERE c.id = ?", nativeQuery = true)
  int updateDatosPersonales(
          @Param("nombre") String nombre,
          @Param("apellidos") String apellidos,
          @Param("fechana") String fecha_nacimiento,
          @Param("dni") String dni,
          @Param("calle") String calle,
          @Param("ciudad") String ciudad,
          @Param("provincia") String provincia,
          @Param("codigo_postal") String codigo_postal,
          @Param("id") int id);

  Page<Cliente> findAllByOrderByIdAsc(Pageable pageable);


  @Query(value = "SELECT id_gimnasio  AS id FROM Cliente INNER JOIN usuario ON cliente.id = usuario.id_usuario " +
          "WHERE id_usuario =:id_usuario", nativeQuery = true)
  int findIdGimnasioByIdUsuario(@Param("id_usuario") String id_usuario);


}
