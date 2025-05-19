package com.fleetguard.api.controller;

import com.fleetguard.api.DTO.DriverDTO;
import com.fleetguard.api.model.Driver;
import com.fleetguard.api.model.DriverSignUp;
import com.fleetguard.api.securityOAuthConfig.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth-driver")
public class AuthDriverController {
    @Autowired
    @Qualifier("driverUserManager")
    UserDetailsManager userDetailsManager;
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;
    @Autowired
    @Qualifier("jwtRefreshTokenAuthProvider")
    JwtAuthenticationProvider refreshTokenAuthProvider;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody DriverSignUp driverDTO) {
        Driver driver = new Driver(driverDTO.getName(), driverDTO.getUsername(), driverDTO.getDocumentType(), driverDTO.getDocumentNumber(), driverDTO.getRol(), driverDTO.getAddress(), driverDTO.getBirthDate(), driverDTO.getPhoneNumber(), driverDTO.getEmail(), driverDTO.getPassword());
        userDetailsManager.createUser(driver);
        
    }

}
