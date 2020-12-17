package innotec.com.sv.controladores;

import java.text.ParseException;
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
import innotec.com.sv.entities.Venta;
import innotec.com.sv.paginador.PageRender;
import innotec.com.sv.services.DeclaracionServiceImp;
import innotec.com.sv.services.VentaServiceImp;



@Controller
@SessionAttributes("datosNew")
@RequestMapping("/ventas")
public class VentasController {

	@Autowired
	VentaServiceImp ventaServiceImp;
	
	@Autowired
	DeclaracionServiceImp declaracionService;
	
	
	
	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	
	@RequestMapping(value="/listar/{id}", method = RequestMethod.GET)
	public String inicial (@PathVariable(value="id") Long id, @RequestParam(name="page", defaultValue="0") int page,   Model modelo) {
		
		int elemento = Integer.parseInt(this.elementos);  

		Declaracion declaracion = declaracionService.findOne(id);
		
		Pageable  pageRequest =  PageRequest.of(page, elemento);
		Page<Venta> venta = ventaServiceImp.findAll(pageRequest) ;
		
		List<Venta> venta2 = (List<Venta>) ventaServiceImp.findByDeclaracion(declaracion.getId() );
		
		List<Compra> venta3 = new  ArrayList();
		for(Venta com : venta2) {
			//compras3 = (List<Compra>) com;
			System.out.println(com.getId());
		}
		
		PageRender<Venta> pageRender = new PageRender<>("/venta/listar", venta, elemento) ;
		//System.out.println(compras2.get(1).getNombre_proveedor());
		System.out.println(venta2);
		modelo.addAttribute("mensaje","hola desde thymeleaf");		
		modelo.addAttribute("titulo","Mantenimiento Libro de Ventas ");
		//
		modelo.addAttribute("datos",declaracion);
		modelo.addAttribute("page",pageRender);
		modelo.addAttribute("ventas",venta2);
		
		return "ventas/listar";		
		
	};
	
	@RequestMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Venta venta = ventaServiceImp.findOne(id);
		if (venta==null) {
			flash.addAttribute("error", "La Declaracion no existe en la Base de datos");
			return "redirect:/ventas/listar";
		}
		//
		model.put("datos", venta);
		model.put("titulo", "Detalle Ventas Asignadas " );
		//
		return "/ventas/ver";
	}
	
	@RequestMapping(value="/nuevo") 
	public Venta nuevo(Map<String, Object> model, RedirectAttributes flash) {
		Venta newventa  = new Venta();
		
		model.put("newdatos", newventa);
		
		return newventa;
	}
	
	
	@RequestMapping(value="/forms/{id}") 
	public String forms (@PathVariable(value="id") Long id, Model modelo, RedirectAttributes flash) throws ParseException {	
		
		Declaracion declaracion = declaracionService.findOne(id);
		Venta       venta     = new Venta();
		venta.setDeclaracion(declaracion);		
		//---
		modelo.addAttribute("datos",declaracion);
		modelo.addAttribute("titulo","Creación de Ventas");	
		modelo.addAttribute("datosNew",venta);
		System.out.println("Salida desde el forms de compras");		
		return "ventas/form";
	};
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String salvar (@Valid @ModelAttribute(value="datosNew") Venta venta, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Creación de Ventas");				
			System.out.println("Cantidad de Errores --> " + result.getFieldError());			
			return "/ventas/form";
			
		} else {
		
			String mensajeFlash = (venta.getId() != null)? "Venta Editada con éxito" : " Venta guardada con éxito "  ;
			ventaServiceImp.save(venta);
			//System.out.println(compra.getDescripcion()+ " ----");			
			model.addAttribute("titulo","Creación de Ventas");
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash );
		
		return "redirect:/ventas/listar/"+venta.getDeclaracion().getId();
		}
	};
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		Venta venta = ventaServiceImp.findOne(id);
		if(id > 0) {
			
			ventaServiceImp.delete(id);
		}
		flash.addFlashAttribute("success", "Venta eliminada con éxito");
		return "redirect:/ventas/listar/"+venta.getDeclaracion().getId();
	}
	
	
}
