package innotec.com.sv.controladores;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login( @RequestParam(value= "error", required = false) String error,  @RequestParam(value= "logout", required = false) String logout, Model model, Principal principal, RedirectAttributes flash) {
		
		//si es diferente a null ya habia iniciado sesion, para evitar que haga otro inicio de sesion
		if (principal != null) {
			flash.addFlashAttribute("info","Ya ha iniciado sesion anteriomente");
			return "redirect:/";
		}
		
		
		if (error != null) {
			model.addAttribute("error"," Error en el Login usuario o contraseña incorrecto. Vuelva a intentarlo");		
		}
		
		if (logout != null) {
			model.addAttribute("info"," Sesion terminada con éxito.");		
		}
		
		return "login";
	}
}
