package es.neifi.controlfitAPI.rest.exceptions;

public class UserNotVerifiedException extends RuntimeException{
  public UserNotVerifiedException(String id_usuario) {
    super("User with id: "+id_usuario+" is not verified");
  }
}
