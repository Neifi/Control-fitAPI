package es.neifi.controlfitAPI.rest.controller

import es.neifi.controlfitAPI.rest.controller.tmpUsecases.LoginUseCase
import es.neifi.controlfitAPI.rest.controller.tmpUsecases.LoginUseCaseRequest
import es.neifi.controlfitAPI.rest.exceptions.UserNotVerifiedException
import es.neifi.controlfitAPI.rest.model.cliente.Cliente
import es.neifi.controlfitAPI.rest.model.rol.Rol
import es.neifi.controlfitAPI.rest.model.usuario.Usuario
import es.neifi.utils.ClientGenerator
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

@ExtendWith(MockKExtension::class)
class LoginUseCaseShould {

    @MockK
    private lateinit var authenticationManager: AuthenticationManager

    @InjectMockKs
    private lateinit var loginUseCase: LoginUseCase

    @Test
    fun `return jwt token when credentials are valid and user is verified`() {
        val registeredUser = Usuario()
        val roles: HashSet<Rol> = HashSet()
        roles.add(Rol.ADMIN)
        registeredUser.setAvatar("http://avatarTest.com")
        registeredUser.setUsername("username")
        registeredUser.setPassword("password")
        registeredUser.setVerificado(true)
        registeredUser.setRol(roles)
        registeredUser.setCliente(
            ClientGenerator.generateClient()
        )
        var usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(registeredUser, null)
        every {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    "admin",
                    "admin"
                )
            )
        } returns usernamePasswordAuthenticationToken

        val sigUpUsecaseResponse = loginUseCase.execute(LoginUseCaseRequest("admin", "admin"))

        assertTrue(sigUpUsecaseResponse.token.isNotEmpty())
        assertTrue(sigUpUsecaseResponse.roles.isNotEmpty())
        assertTrue(sigUpUsecaseResponse.roles.contains(Rol.ADMIN))
    }

    @Test
    fun `throw exception when user is not verified`() {
        val registeredUser = Usuario()
        val roles: HashSet<Rol> = HashSet()
        roles.add(Rol.ADMIN)
        registeredUser.setAvatar("http://avatarTest.com")
        registeredUser.setUsername("username")
        registeredUser.setPassword("password")
        registeredUser.setVerificado(false)
        registeredUser.setRol(roles)
        registeredUser.setCliente(
            ClientGenerator.generateClient()
        )
        var usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(registeredUser, null)
        every {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    "admin",
                    "admin"
                )
            )
        } returns usernamePasswordAuthenticationToken


        org.junit.jupiter.api.assertThrows<UserNotVerifiedException> {
            loginUseCase.execute(LoginUseCaseRequest("admin", "admin"))
        }
    }
}