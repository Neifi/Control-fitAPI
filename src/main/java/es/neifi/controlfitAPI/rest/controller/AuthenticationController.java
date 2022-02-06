package es.neifi.controlfitAPI.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.neifi.controlfitAPI.rest.model.dto.converter.UsuarioDTOConverter;
import es.neifi.controlfitAPI.rest.model.dto.usuario.GetUserDTO;
import es.neifi.controlfitAPI.rest.model.usuario.Usuario;
import es.neifi.controlfitAPI.rest.security.jwt.JwtProvider;
import es.neifi.controlfitAPI.rest.security.jwt.model.LoginRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final UsuarioDTOConverter converter;
  private final LoginUseCase loginUseCase;

  @PostMapping("auth/login")
  public ResponseEntity<LoginUsecaseResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

    LoginUsecaseResponse response = loginUseCase.execute(
            new LoginUseCaseRequest(
                    loginRequest.getUsername(),
                    loginRequest.getPassword())
    );
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
  }

  public GetUserDTO me(@AuthenticationPrincipal Usuario usuario) {
    return converter.convertUserToGetUserDTO(usuario);
  }


}
