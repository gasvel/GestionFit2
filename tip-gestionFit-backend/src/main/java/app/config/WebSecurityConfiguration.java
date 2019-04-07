package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.persistence.UserDAO;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
  @Autowired
  private UserDAO userDAO;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(authProvider());
  }

  @Bean
  public UserDetailsService userDetailsService() {
      return new UserDetailsService() {
          @Override
          public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
              String password = null;
              app.model.User_Priv user = userDAO.getByUsername(username);
              if(user == null){ throw new UsernameNotFoundException("No existe el usuario '"
                      + username + "'");};
              password = user.getPassword();
              String role = user.getRole();

            
              if(password != null) {
                  return  User.withUsername(username).password(password).roles(role).build();
              } else {
                  throw new UsernameNotFoundException("No existe el usuario '"
                          + username + "'");
              }

          }
      };
  }
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  } 
  
  @Bean
  public DaoAuthenticationProvider authProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
  }
}
