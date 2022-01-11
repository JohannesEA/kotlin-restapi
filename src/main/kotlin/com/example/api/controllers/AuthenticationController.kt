package com.example.api.controllers

import com.example.api.authenticate.JWTSupport
import com.example.api.models.JWT
import com.example.api.models.Login
import com.example.api.models.Profile
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.security.Principal


@RestController
@RequestMapping("api/auth")
class AuthenticationController(
    private val jwtSupport: JWTSupport,
    private val encoder: PasswordEncoder,
    private val users: ReactiveUserDetailsService
) {

    @GetMapping("/profile")
    suspend fun getTest(@AuthenticationPrincipal principal: Principal): Profile {
        return Profile(principal.name);
    }

    @PostMapping("/login")
    suspend fun login(@RequestBody login: Login): JWT{
        val user = users.findByUsername(login.username).awaitSingleOrNull();

        user?.let{
            if (encoder.matches(login.password, it.password)){
                return JWT(jwtSupport.generate(it.username).value);
            }
        }

        throw ResponseStatusException(HttpStatus.UNAUTHORIZED);

    }

}



