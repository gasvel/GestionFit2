package app.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;



/**
 * Las peticiones que no sean /login pasarán por este filtro
 * el cuál se encarga de pasar el "request" a nuestra clase de utilidad JwtUtil
 * para que valide el token.
 */
public class JwtFilter extends GenericFilterBean {
	
	private List<String> whitelist;
	
	public JwtFilter(List<String> whitelist){
		this.whitelist = whitelist;
	}
	

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
    	boolean notInWhiteList = true;

    	for(String url :this.whitelist){
    		notInWhiteList =  notInWhiteList && !((HttpServletRequest)request).getRequestURL().toString().contains(url);
    	}
    	
    	if(notInWhiteList){        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request);
        

        SecurityContextHolder.getContext().setAuthentication(authentication);};
    	


        filterChain.doFilter(request,response);
    }
}
