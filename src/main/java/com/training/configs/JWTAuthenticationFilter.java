package com.training.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.entities.NewsUser;
import com.training.services_impl.ResponseDataServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) {
        try {
            NewsUser creds = new ObjectMapper()
                    .readValue(req.getInputStream(), NewsUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
        	return null;
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
    	ResponseDataServiceImpl responseService = new ResponseDataServiceImpl();
        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getSecret())
                .compact();
        res.addHeader(SecurityConstants.getHeaderString(), SecurityConstants.getTokenPrefix() + token);
        res.setContentType("application/json");
        String json = new ObjectMapper().writeValueAsString(responseService.responseSuccess(null).getBody());
        res.getWriter().write(json);
    }
}