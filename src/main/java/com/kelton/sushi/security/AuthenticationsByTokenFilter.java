package com.kelton.sushi.security;

import com.kelton.sushi.repositories.UserRepository;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationsByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository repo;

    public AuthenticationsByTokenFilter(TokenService tokenService, UserRepository repo) {
        this.tokenService = tokenService;
        this.repo = repo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
