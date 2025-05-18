package com.fleetguard.api.securityOAuthConfig;

import com.fleetguard.api.model.Administrator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JWTtoUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source){
        Administrator admin = new Administrator();
        admin.setId(Long.valueOf(source.getSubject()));
        return new UsernamePasswordAuthenticationToken(admin, "", admin.getAuthorities());
    }
}
