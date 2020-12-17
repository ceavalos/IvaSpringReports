package innotec.com.sv.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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

import innotec.com.sv.entities.Declaracion;
import innotec.com.sv.entities.Empresa;
import innotec.com.sv.entities.Periodo;
import innotec.com.sv.paginador.PageRender;
import innotec.com.sv.services.DeclaracionServiceImp;
import innotec.com.sv.services.EmpresaServiceImp;
import innotec.com.sv.services.PeriodoServiceImpl;



@Controller
@SessionAttributes("declaracion")
@RequestMapping("/declaracion")
public class DeclaracionController {
	
	@Autowired
	DeclaracionServiceImp declaracionServiceImp;
	
	@Autowired
	EmpresaServiceImp empresaServiceImp;	

	@Autowired
	PeriodoServiceImpl periodoServiceImp;
	
	
	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String inicial (@RequestParam(name="page", defaultValue="0") int page,   Model modelo) {
		
	   
		
		int elemento = Integer.parseInt(this.elementos);  
		
		Pageable  pageRequest =  PageRequest.of(page, elemento);
		Page<Declaracion> declaracion = declaracionServiceImp.findAll(pageRequest);
		
		PageRender<Declaracion> pageRender = new PageRender<>("declaracion/listar", declaracion, elemento) ;
				
		modelo.addAttribute("mensaje","hola desde thymeleaf");		
		modelo.addAttribute("titulo","Mantenimiento de Declaraciones");
		//modelo.addAttribute("datos",periodoServiceimp.findAll());		
		modelo.addAttribute("datos",declaracion);
		modelo.addAttribute("page",pageRender);
		return "declaracion/listar";		
		
	};
	
	@RequestMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Declaracion declaracion = declaracionServiceImp.findOne(id);
		if (declaracion==null) {
			flash.addAttribute("error", "La Declaracion no existe en la Base de datos");
			return "redirect:/declaracion/listar";
		}
		//
		model.put("datos", declaracion);
		model.put("titulo", "Detalle Declaraciones Configuradas " );
		//
		return "declaracion/ver";
	}
	
	@RequestMapping(value="/form") 
	public String form (Model modelo) throws ParseException {	
		Declaracion declaracion  = new Declaracion();
		
		List<Empresa> empresa =  empresaServiceImp.findAll();
		
		List<Periodo> periodo = periodoServiceImp.findAll();
		
		//empresa.setNombre("Carlitos Avalos");
		//SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
		//periodo.setDescripcion("descripcion");
		//periodo.setFechaInicio( objSDF.parse("2020-01-01"));
		//periodo.setFechaFinal(objSDF.parse("2020-01-01"));
		//---
		modelo.addAttribute("titulo","Creación de Declaraciones");	
		modelo.addAttribute("datos",declaracion);
		modelo.addAttribute("empresa",empresa);
		modelo.addAttribute("periodo", periodo);
		
		return "declaracion/form";
	};
	
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String salvar (@Valid @ModelAttribute(value="datos") Declaracion declaracion, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Creación de Declaraciones");	
			List<Empresa> empresa =  empresaServiceImp.findAll();			
			List<Periodo> periodo = periodoServiceImp.findAll();
			model.addAttribute("empresa",empresa);
			model.addAttribute("periodo", periodo);
			
			System.out.println("Cantidad de Errores --> " + result.getFieldError());			
			return "/declaracion/form";
			//return "/declaracion/listar";
		} else {
		
			String mensajeFlash = (declaracion.getId() != null)? "Declaración Editada con éxito" : " Declaración guardada con éxito "  ;
			declaracionServiceImp.save(declaracion);
			System.out.println(declaracion.getDescripcion()+ " ----");			
			model.addAttribute("titulo","Creación de Declaraciones");
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash );
		
		return "redirect:/declaracion/listar";
		}

		
	};
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Declaracion declaracion = null;
		
		if(id > 0) {
			declaracion = declaracionServiceImp.findOne(id);
			if (declaracion == null) {
				flash.addFlashAttribute("error", " El periodo no existe en la Base de datos");
				return "redirect:declaracion/listar";
			}
		} else {
			flash.addFlashAttribute("error", " Periodo no existe");
			return "redirect:declaracion/listar";
		}
		
		 Empresa empresa =  empresaServiceImp.findById(declaracion.getEmpresa().getId());		
		 Periodo periodo =  periodoServiceImp.findById(declaracion.getPeriodo().getId());
		//
		model.put("titulo","Edición de Declaraciones");
		model.put("datos", declaracion);
		model.put("empresa", empresa);
		model.put("periodo", periodo);
		//
		flash.addFlashAttribute("success", " Periodo guardado con éxito");
		return "declaracion/form";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			declaracionServiceImp.delete(id);
		}
		flash.addFlashAttribute("success", " Declaracion eliminada con éxito");
		return "redirect:/declaracion/listar";
	}
	

	
}
