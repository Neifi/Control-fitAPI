package es.neifi.controlfitAPI.rest.controller.tmpUsecases

import es.neifi.controlfitAPI.rest.model.rol.Rol
import es.neifi.controlfitAPI.rest.model.usuario.Usuario
import es.neifi.controlfitAPI.rest.security.jwt.JwtProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class LoginUseCase(private val authenticationManager: AuthenticationManager) {

    private val jwtProvider = JwtProvider()

    fun execute(request: LoginUseCaseRequest): LoginUsecaseResponse {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication
        val user = authentication.principal as Usuario
        val token = jwtProvider.generateToken(authentication)

        return LoginUsecaseResponse(token, user.roles())
    }
}

data class LoginUseCaseRequest(
    val username: String,
    val password: String
)

data class LoginUsecaseResponse(val token: String, val roles: Set<Rol>)