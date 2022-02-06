package es.neifi.controlfitAPI.rest.config;

import es.neifi.controlfitAPI.rest.controller.LoginUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class Config {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


  @Bean
  public LoginUseCase signUpUseCase(
          AuthenticationManager authenticationManager
  ) {
    return new LoginUseCase(authenticationManager);
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
