package com.example.api.authenticate

import com.example.api.models.BearerToken
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class JWTSupport() {
    private val key = Keys.hmacShaKeyFor("EM1j4UjuEcwq5FJ8tkaxAR5zZc5zB4YL17X+CQ6lBjY=".toByteArray());
    private val parser = Jwts.parserBuilder().setSigningKey(key).build();
    fun generate(username: String)  : BearerToken
    {
        val builder = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
            .signWith(key)

        return BearerToken(builder.compact());
    }

    fun getUsername(token: BearerToken): String {
    return parser.parseClaimsJws(token.value).body.subject;
    }

    fun isValid(token: BearerToken, user: UserDetails?): Boolean{
        val claims = parser.parseClaimsJws(token.value).body
        val unexpired = claims.expiration.after(Date.from(Instant.now()))

        return unexpired && (claims.subject == user?.username)
    }


}