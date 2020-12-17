package innotec.com.sv.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import innotec.com.sv.entities.Periodo;
import innotec.com.sv.paginador.PageRender;
import innotec.com.sv.services.PeriodoServiceImpl;


@Controller
@SessionAttributes("periodo")
@RequestMapping("/periodo")
public class PeriodoController {

	@Autowired
	PeriodoServiceImpl periodoServiceimp;
	
	
	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String inicial (@RequestParam(name="page", defaultValue="0") int page,   Model modelo) {
		
		
		
		int elemento = Integer.parseInt(this.elementos);  
		
		Pageable  pageRequest =  PageRequest.of(page, elemento);
		Page<Periodo> periodo = periodoServiceimp.findAll(pageRequest);
		
		PageRender<Periodo> pageRender = new PageRender<>("/periodo/listar", periodo, elemento) ;
				
		modelo.addAttribute("mensaje","hola desde thymeleaf");		
		modelo.addAttribute("titulo","Mantenimiento de Periodos");
		//modelo.addAttribute("datos",periodoServiceimp.findAll());		
		modelo.addAttribute("datos",periodo);
		modelo.addAttribute("page",pageRender);
		return "periodo/listar";
		
		
	};
	
	@RequestMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Periodo periodo = periodoServiceimp.findById(id);
		if (periodo==null) {
			flash.addAttribute("error", "El periodo no existe en la Base de datos");
			return "redirect:/empresa/listar";
		}
		//
		model.put("datos", periodo);
		model.put("titulo", "Detalle Periodos Configurados " );
		//model.put("datos",periodo);
		//
		return "periodo/ver";
	}
	
	@RequestMapping(value="/form") 
	public String form (Model modelo) throws ParseException {	
		Periodo periodo = new Periodo();
		//empresa.setNombre("Carlitos Avalos");
		SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
		//periodo.setDescripcion("descripcion");
		//periodo.setFechaInicio( objSDF.parse("2020-01-01"));
		//periodo.setFechaFinal(objSDF.parse("2020-01-01"));
		//---
		modelo.addAttribute("titulo","Creación de Periodo");	
		modelo.addAttribute("periodo",periodo);
		
		return "periodo/form";
	};
	
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String salvar (@Valid @ModelAttribute(value="periodo") Periodo periodo, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Creación de Periodo");		
			System.out.println("Estamos en error periodo " + result.getFieldError(null)+" "+  periodo.getDescripcion()+" " + periodo.getFechaFinal()+" "+periodo.getFechaInicio());			
			return "/periodo/form";
		} else {
		
			String mensajeFlash = (periodo.getId() != null)? "Periodo Editado con éxito" : " Periodo guardado con éxito "  ;
			periodoServiceimp.save(periodo);
			//System.out.println(periodo.getDescripcion() + " ----");			
			model.addAttribute("titulo","Creación de Periodos");
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash );
		
		return "redirect:/periodo/listar";
		}

	};
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Periodo periodo = null;
		
		if(id > 0) {
			periodo = periodoServiceimp.findById(id);
			if (periodo == null) {
				flash.addFlashAttribute("error", " El periodo no existe en la Base de datos");
				return "redirect:/periodo/listar";
			}
		} else {
			flash.addFlashAttribute("error", " Periodo no existe");
			return "redirect:/periodo/listar";
		}
		model.put("periodo", periodo);
		model.put("titulo", "Editar Periodo");
		flash.addFlashAttribute("success", " Periodo guardado con éxito");
		return "periodo/form";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			periodoServiceimp.delete(id);
		}
		flash.addFlashAttribute("success", " Periodo eliminado con éxito");
		return "redirect:/periodo/listar";
	}
	
	
}
