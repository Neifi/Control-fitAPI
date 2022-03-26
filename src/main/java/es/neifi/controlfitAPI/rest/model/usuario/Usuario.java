package es.neifi.controlfitAPI.rest.model.usuario;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.neifi.controlfitAPI.rest.model.cliente.ClienteEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.neifi.controlfitAPI.rest.model.cliente.Cliente;
import es.neifi.controlfitAPI.rest.model.rol.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "usuario", schema = "public")
public class Usuario implements UserDetails {

  static final long serialVersionUID = 7615817677864376347L;

  @Id
  private String id_usuario;

  private String username;
  private String password;
  private String avatar;


  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_usuario", referencedColumnName = "id")
  private Cliente cliente;


  @CreatedDate
  private LocalDateTime fecha_creacion;
  @Builder.Default
  private LocalDateTime ultima_mod_password = LocalDateTime.now();
  private boolean verificado;
  @ElementCollection(fetch = FetchType.EAGER) // TODO eager?
  @Enumerated(EnumType.STRING)
  @Column(name = "rol")
  private Set<Rol> rol;


  public Usuario() {
    super();
    this.verificado = false;
  }

  public boolean isNotVerified() {
    return verificado == false;
  }

  public Usuario(@NotNull String username, @NotNull String password, @NotNull String avatar) {

  }

  public Set<Rol> roles(){
    return this.rol;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub

    return rol.stream().map(ur -> new SimpleGrantedAuthority("ROLE" + ur.name())).collect(Collectors.toSet());
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }


  public Usuario setId_usuario(String id_usuario) {
    this.id_usuario = id_usuario;
    return this;
  }

  public Usuario setUsername(String username) {
    this.username = username;
    return this;
  }

  public Usuario setPassword(String password) {
    this.password = password;
    return this;
  }

  public Usuario setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public Usuario setCliente(Cliente cliente) {
    this.cliente = cliente;
    return this;
  }

  public Usuario setFecha_creacion(LocalDateTime fecha_creacion) {
    this.fecha_creacion = fecha_creacion;
    return this;
  }

  public Usuario setUltima_mod_password(LocalDateTime ultima_mod_password) {
    this.ultima_mod_password = ultima_mod_password;
    return this;
  }

  public Usuario setVerificado(boolean verificado) {
    this.verificado = verificado;
    return this;
  }

  public Usuario setRol(Set<Rol> rol) {
    this.rol = rol;
    return this;
  }
}
