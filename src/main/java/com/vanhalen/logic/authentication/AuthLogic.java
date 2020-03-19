package com.vanhalen.logic.authentication;

import com.vanhalen.interfaces.AuthLogicInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthLogic implements AuthLogicInterface {
    private AuthenticationManager _authAuthenticationManager;
    private JwtService _jwtService;
    private DefaultUserDetailsService _userDetailsService;

    @Autowired
    public AuthLogic(AuthenticationManager authenticationManager, DefaultUserDetailsService defaultUserDetailsService, JwtService jwtService) {
        _authAuthenticationManager = authenticationManager;
        _userDetailsService = defaultUserDetailsService;
        _jwtService = jwtService;
    }

    @Override
    public String authenticate(String username, String password) throws AuthenticationException {
        _authAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final var user = _userDetailsService.loadUserByUsername(username);

        return _jwtService.generateJwtToken(user);
    }
}
