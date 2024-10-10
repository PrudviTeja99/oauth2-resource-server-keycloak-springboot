package com.example.ResourceServer.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt,AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        Collection<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(),fetchCustomAuthorities(jwt).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt,authorities,getPrincipleClaimName());
    }

    private String getPrincipleClaimName(){
        return JwtClaimNames.SUB;
    }

    private Collection<GrantedAuthority> fetchCustomAuthorities(Jwt jwt){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        try{
            Map<String,Object> resourceAccess = jwt.getClaim("resource_access");
            Map<String,Object> client = (Map<String, Object>) resourceAccess.get("test-client");
            List<String> roles = (List<String>) client.get("roles");
            authorities = roles.stream().map(role->new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toList());
        }
        catch (Exception exception){
            //Unable to fetch custom authorities
        }
        return authorities;
    }
}
