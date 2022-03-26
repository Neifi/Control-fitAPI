package es.neifi.controlfitAPI.rest.model.gimnasio;

import es.neifi.controlfitAPI.rest.model.cliente.DomainEntity;

public class Gym implements DomainEntity {

  private String gymId;
  private String nombre;
  private String ciudad;
  private String direccion;
  private int codigo_postal;
  private String provincia;
  private String pais;

  public String gymId() {
    return gymId;
  }

  public void setGymId(String gymId) {
    this.gymId = gymId;
  }

  public Gym(
          String gymId,
          String nombre,
          String ciudad,
          String direccion,
          int codigoPostal,
          String provincia,
          String pais
  ) {
    this.gymId = gymId;
    this.nombre = nombre;
    this.ciudad = ciudad;
    this.direccion = direccion;
    this.codigo_postal = codigoPostal;
    this.provincia = provincia;
    this.pais = pais;
  }

  public String nombre() {
    return nombre;
  }

  public String ciudad() {
    return ciudad;
  }

  public String direccion() {
    return direccion;
  }

  public int codigo_postal() {
    return codigo_postal;
  }

  public String provincia() {
    return provincia;
  }

  public String pais() {
    return pais;
  }

}
