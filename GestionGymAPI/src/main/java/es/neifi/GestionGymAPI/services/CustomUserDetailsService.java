package es.neifi.GestionGymAPI.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("UserDetailService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return usuarioService.findByUsername(username)
				.orElseThrow(
						() -> new UsernameNotFoundException("Usuario "+username+" no encontrado"));
	}
	
	public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {
		return usuarioService.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("Usuario con id"+id+" no encontrado"));
	}
	
	
}
