package innotec.com.sv.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import innotec.com.sv.entities.Compra;
import innotec.com.sv.entities.Declaracion;
import innotec.com.sv.paginador.PageRender;
import innotec.com.sv.services.CompraServiceImp;
import innotec.com.sv.services.DeclaracionServiceImp;



@Controller
@SessionAttributes("datosNew")
@RequestMapping("/compra")
public class ComprasController {
	
	@Autowired
	CompraServiceImp compraServiceImp;
	
	@Autowired
	DeclaracionServiceImp declaracionService;
	
	
	
	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	@RequestMapping(value="/listar/{id}", method = RequestMethod.GET)
	public String inicial (@PathVariable(value="id") Long id, @RequestParam(name="page", defaultValue="0") int page,   Model modelo) {
		
		int elemento = Integer.parseInt(this.elementos);  
		
		
		
		Declaracion declaracion = declaracionService.findOne(id);
       // List<Empresa> empresa =  empresaServiceImp.findById(declaracion.getEmpresa());
		// List<Periodo> periodo = periodoServiceImp.findAll(declaracion.);
		
		Pageable  pageRequest =  PageRequest.of(page, elemento);
		Page<Compra> compra = compraServiceImp.findAll(pageRequest) ;
		
		
		
		List<Compra> compras2 = (List<Compra>) compraServiceImp.findByDeclaracion(declaracion.getId() );
		
		List<Compra> compras3 = new  ArrayList();
		for(Compra com : compras2) {
			//compras3 = (List<Compra>) com;
			System.out.println(com.getId());
		}
		
		PageRender<Compra> pageRender = new PageRender<>("/compra/listar", compra, elemento) ;
		
		//System.out.println(compras2.get(1).getNombre_proveedor());
		System.out.println(compras2);
		modelo.addAttribute("mensaje","hola desde thymeleaf");		
		modelo.addAttribute("titulo","Mantenimiento Libro de Compras ");
		//
		modelo.addAttribute("datos",declaracion);
		modelo.addAttribute("page",pageRender);
		modelo.addAttribute("compras",compras2);
		
		
		return "compras/listar";		
		
	};
	
	@RequestMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Compra compra = compraServiceImp.findOne(id);
		if (compra==null) {
			flash.addAttribute("error", "La Declaracion no existe en la Base de datos");
			return "redirect:/compra/listar";
		}
		//
		model.put("datos", compra);
		model.put("titulo", "Detalle Compras Asignadas " );
		//
		return "/compras/ver";
	}
	
	@RequestMapping(value="/nuevo") 
	public Compra nuevo(Map<String, Object> model, RedirectAttributes flash) {
		Compra newcompra  = new Compra();
		
		model.put("newdatos", newcompra);
		
		return newcompra;
	}
	
	@RequestMapping(value="/forms/{id}") 
	public String forms (@PathVariable(value="id") Long id, Model modelo, RedirectAttributes flash) throws ParseException {	
		
		Declaracion declaracion = declaracionService.findOne(id);
		Compra       compra     = new Compra();
		compra.setDeclaracion(declaracion);
		//empresa.setNombre("Carlitos Avalos");
		//SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
		//periodo.setDescripcion("descripcion");
		//periodo.setFechaInicio( objSDF.parse("2020-01-01"));
		//periodo.setFechaFinal(objSDF.parse("2020-01-01"));
		//---
		modelo.addAttribute("datos",declaracion);
		modelo.addAttribute("titulo","Creación de Compras");	
		modelo.addAttribute("datosNew",compra);
		System.out.println("Salida desde el forms de compras");
		
		return "compras/form";
	};
	
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String salvar (@Valid @ModelAttribute(value="datosNew") Compra compra, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Creación de Compras");				
			System.out.println("Cantidad de Errores --> " + result.getFieldError());			
			return "compras/form";
			
		} else {
		
			String mensajeFlash = (compra.getId() != null)? "Compra Editada con éxito" : " Compra guardada con éxito "  ;
			compraServiceImp.save(compra);
			//System.out.println(compra.getDescripcion()+ " ----");			
			model.addAttribute("titulo","Creación de Compras");
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash );
		
		return "redirect:/compra/listar/"+compra.getDeclaracion().getId();
		}

		
	};
	
	/*
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Compra compra = null;
		
		if(id > 0) {
			compra = compraServiceImp.findOne(id);
			if (compra == null) {
				flash.addFlashAttribute("error", " La compra no existe en la Base de datos");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", " Compra no existe");
			return "redirect:/listar";
		}
		
	
		//
		model.put("titulo","Edición de Declaraciones");
		model.put("datos", compra);
		//
		flash.addFlashAttribute("success", " Periodo guardado con éxito");
		return "/compras/form";
	}
	*/
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		Compra compra = compraServiceImp.findOne(id);
		if(id > 0) {
			
			compraServiceImp.delete(id);
		}
		flash.addFlashAttribute("success", " Compra eliminada con éxito");
		return "redirect:/compra/listar/"+compra.getDeclaracion().getId();
	}
	

}
