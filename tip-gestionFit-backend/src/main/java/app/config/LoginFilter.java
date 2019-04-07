package app.config;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import app.model.GymDTO;
import app.model.User;
import app.persistence.GymDAO;
import app.persistence.UserDAO;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	  @Autowired
	  private UserDAO userDAO = new UserDAO();
	  
	  @Autowired
	  private GymDAO gymDAO = new GymDAO();

    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        // obtenemos el body de la peticion que viene en formato JSON
    	InputStream body = req.getInputStream();   	

        // mapeamos con la clase Credentials para tener ahi los datos
        Credentials user = new ObjectMapper().readValue(body, Credentials.class);

        System.out.println("USEEEEEEEEEEER " + user.getEmail());


        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
    	// si la autenticacion fue exitosa  agregamos el token a la respuesta
    	
    	UserDetails user = (UserDetails) auth.getPrincipal();
    	User userDB = this.userDAO.getByUsername(user.getUsername());
    	long id = userDB.getId();
    	long gymId = userDB.getGym();
    	GymDTO gym = this.gymDAO.getGymDTO(gymId);
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	String json = ow.writeValueAsString(gym);
    	String userJson = ow.writeValueAsString(userDB);
        JwtUtil.addAuthentication(res,user,userJson,json);
    }
}
