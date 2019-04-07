package app.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.exception.NoAuthorizationException;
import app.model.GymDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    // metodo para crear el JWT y enviarlo al cliente en el header de la respuesta
    static void addAuthentication(HttpServletResponse res,UserDetails userContext, String userJson,String gym) throws IOException {
    	
    	 if ((userContext.getUsername()) == "") 
             throw new IllegalArgumentException("Cannot create JWT Token without username");

         if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) 
             throw new IllegalArgumentException("User doesn't have any privileges");

         Claims claims = Jwts.claims().setSubject(userContext.getUsername());
         claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

         //Seteamos la expiracion del token dentro de 5 dias
         Date dt = new Date();
         Calendar c = Calendar.getInstance(); 
         c.setTime(dt); 
         c.add(Calendar.DATE, 5);
         
//		2 minutos para testeo
//      c.add(Calendar.MINUTE, 2);
         dt = c.getTime();
         
        String token = Jwts.builder()
            .setClaims(claims)
            .setExpiration(dt)
            .signWith(SignatureAlgorithm.HS512, "G@sT0n")
            .compact();

      //agregamos al encabezado y al cuerpo de la respuesta el token 
        res.setHeader("Authorization", token);
    	PrintWriter writer = res.getWriter();
		writer.write("[{\"token\":\""+ "Bearer " + token + "\""+ "}," + userJson + "," + gym + "]");
        writer.close();
    }

    // metodo para validar el token enviado por el cliente
    @SuppressWarnings("unchecked")
	static Authentication getAuthentication(HttpServletRequest request) throws NoAuthorizationException {

    	
        // obtenemos el token que viene en el encabezado de la peticion
    	String token;
    	if(request.getHeader("Authorization") != null){
    		token = request.getHeader("Authorization").replace("Bearer ", "");	
    	}
    	else{
    		throw new NoAuthorizationException("Empty header");
    	}
        


        String url=request.getRequestURL().toString();

        try{
        	// si hay un token presente entonces lo validamos
	        if (token != null) {
	            String user = Jwts.parser()
	                    .setSigningKey("G@sT0n")
	                    .parseClaimsJws(token) //este metodo es el que valida
	                    .getBody()
	                    .getSubject();
	            
	            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey("G@sT0n").parseClaimsJws(token);
	            List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
	            List<GrantedAuthority> authorities = scopes.stream()
	                    .map(authority -> new SimpleGrantedAuthority(authority))
	                    .collect(Collectors.toList());
	            if(url.contains("user/byUsername")) {
	            	if(!url.contains(user)) {
	            		throw new ExpiredJwtException(null, null, user);
	            	}
	            }
	
	            return user != null ?
	                    new UsernamePasswordAuthenticationToken(user, null, authorities) :
	                    null;
	        }
        } catch(ExpiredJwtException exception){
        	return null;
        }
        return null;
    }

}

