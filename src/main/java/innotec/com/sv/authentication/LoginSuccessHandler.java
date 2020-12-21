package innotec.com.sv.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;
 

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		SessionFlashMapManager flashmanager = new SessionFlashMapManager();
		FlashMap flashmap = new FlashMap();
		
		logger.info(" hola '"+ authentication.getName() +"' iniciado sesión con éxito");
		flashmap.put ("success", "Hola '" + authentication.getName() +"' iniciado sesión con éxito") ;
		
		flashmanager.saveOutputFlashMap(flashmap, request, response);
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
