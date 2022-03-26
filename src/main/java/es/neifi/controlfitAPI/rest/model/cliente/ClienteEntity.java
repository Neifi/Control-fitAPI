package es.neifi.controlfitAPI.rest.model.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.neifi.controlfitAPI.rest.model.usuario.Usuario;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@Component
public class ClienteEntity {

  public ClienteEntity() {

  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int id_gimnasio;
  private String dni;
  private String nombre;
  private String apellidos;
  private String fecha_nacimiento;
  private String fecha_inscripcion;
  private String email;
  private String calle;
  private String codigo_postal;
  private String ciudad;
  private String provincia;

  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id", referencedColumnName = "id_usuario")
  private Usuario usuario;

  private ClienteEntity(
          int id,
          int id_gimnasio,
          String dni,
          String nombre,
          String apellidos,
          String fecha_nacimiento,
          String email,
          String calle,
          String codigo_postal,
          String ciudad,
          String provincia
  ) {
    this.id = id;
    this.id_gimnasio = id_gimnasio;
    this.dni = dni;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.fecha_nacimiento = fecha_nacimiento;
    this.email = email;
    this.calle = calle;
    this.codigo_postal = codigo_postal;
    this.ciudad = ciudad;
    this.provincia = provincia;
  }

  public int id() {
    return id;
  }

  public int id_gimnasio() {
    return id_gimnasio;
  }

  public String dni() {
    return dni;
  }

  public String nombre() {
    return nombre;
  }

  public String apellidos() {
    return apellidos;
  }

  public String fecha_nacimiento() {
    return fecha_nacimiento;
  }

  public String fecha_inscripcion() {
    return fecha_inscripcion;
  }

  public String email() {
    return email;
  }

  public String calle() {
    return calle;
  }

  public String codigo_postal() {
    return codigo_postal;
  }

  public String ciudad() {
    return ciudad;
  }

  public String provincia() {
    return provincia;
  }

  public Usuario usuario() {
    return usuario;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ClienteEntity)) return false;
    ClienteEntity cliente = (ClienteEntity) o;
    return id == cliente.id && id_gimnasio == cliente.id_gimnasio && Objects.equals(dni, cliente.dni) && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellidos, cliente.apellidos) && Objects.equals(fecha_nacimiento, cliente.fecha_nacimiento) && Objects.equals(fecha_inscripcion, cliente.fecha_inscripcion) && Objects.equals(email, cliente.email) && Objects.equals(calle, cliente.calle) && Objects.equals(codigo_postal, cliente.codigo_postal) && Objects.equals(ciudad, cliente.ciudad) && Objects.equals(provincia, cliente.provincia) && Objects.equals(usuario, cliente.usuario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, id_gimnasio, dni, nombre, apellidos, fecha_nacimiento, fecha_inscripcion, email, calle, codigo_postal, ciudad, provincia, usuario);
  }
}
