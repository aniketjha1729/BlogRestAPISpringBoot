package com.rest.springRestApi.security;

import com.rest.springRestApi.exception.SpringBlogException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {
    private Key key;

    @PostConstruct
    public void init() {
        key= Keys.secretKeyFor(SignatureAlgorithm.HS512);

    }

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

//    private PublicKey getPublickey() {
//        try {
//            return keyStore.getCertificate("springblog").getPublicKey();
//        } catch (KeyStoreException e) {
//            throw new SpringBlogException("Exception occured while retrieving public key from keystore");
//        }
//    }

//    private PrivateKey getPrivateKey() {
//        try {
//            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
//        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
//            throw new SpringBlogException("Exception occured while retrieving public key from keystore");
//        }
//    }


}
