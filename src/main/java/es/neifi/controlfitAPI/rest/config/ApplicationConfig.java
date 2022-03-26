package es.neifi.controlfitAPI.rest.config;

import es.neifi.controlfitAPI.rest.controller.FindClientByIdUsecase;
import es.neifi.controlfitAPI.rest.controller.ListClientsUsecase;
import es.neifi.controlfitAPI.rest.controller.tmpUsecases.CreateClientUsecase;
import es.neifi.controlfitAPI.rest.controller.tmpUsecases.LoginUseCase;
import es.neifi.controlfitAPI.rest.model.IdGenerator;
import es.neifi.controlfitAPI.rest.model.cliente.ClienteJPARepository;
import es.neifi.controlfitAPI.rest.model.gimnasio.GimnasioRepository;
import es.neifi.controlfitAPI.rest.services.UUIDGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class ApplicationConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public IdGenerator idGenerator() {
    return new UUIDGenerator();
  }

  @Bean
  public CreateClientUsecase createClientUsecase(
          ClienteJPARepository clienteJPARepository,
          GimnasioRepository gimnasioRepository,
          IdGenerator idGenerator
  ) {
    return new CreateClientUsecase(
            clienteJPARepository,
            gimnasioRepository,
            idGenerator
    );

  }

  @Bean
  public LoginUseCase signUpUseCase(
          AuthenticationManager authenticationManager
  ) {
    return new LoginUseCase(authenticationManager);
  }

  @Bean
  public ListClientsUsecase signUpUseCase(
          ClienteJPARepository clienteJPARepository) {
    return new ListClientsUsecase(clienteJPARepository);
  }

  @Bean
  public FindClientByIdUsecase findClientByIdUsecase(
          ClienteJPARepository clienteJPARepository) {
    return new FindClientByIdUsecase(clienteJPARepository);
  }
//	BASIC AUTH CORS	
//	public WebMvcConfigurer corsConfig() {
//		
//		return new WebMvcConfigurer() {
//
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**"); // TODO Cambiar en produccion
//				/*
//				 * registry.addMapping(/usuario)
//				 * .allowedOrigins("http://localhost:4200)
//				 * .allowedMethods("GET","POST","PUT","DELETE")
//				 * .maxAge(3600);
//				 */
//				
//			}
//			
//		};

//	}
}
