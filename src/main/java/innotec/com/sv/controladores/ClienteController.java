package innotec.com.sv.controladores;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import innotec.com.sv.entities.Cliente;
import innotec.com.sv.paginador.PageRender;
import innotec.com.sv.services.ClienteService;
import innotec.com.sv.services.IUploadFileService;



@Controller
@SessionAttributes("cliente")
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private IUploadFileService uploadFileService;
	
	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource> verfoto( @PathVariable String filename  ){
		Path pathfoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
		log.info("pathfoto: "+pathfoto);
		
		Resource recurso=null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		return ResponseEntity.ok().header(HttpHeaders.
				CONTENT_DISPOSITION, "attachment; filename= \""+ recurso.getFilename() + "\"").body(recurso);
	}
	
	
	@RequestMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(id);
		if (cliente==null) {
			flash.addAttribute("error", "El Tercero no existe en la Base de datos");
			return "redirect:/cliente/listar";
		}
		//
		model.put("cliente", cliente);
		model.put("titulo", "Detalle Cliente "+cliente.getNombre());
		//
		return "cliente/ver";
		
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model modelo) {
		//System.out.println("elementos= "+ this.elementos);
		int elemento = Integer.parseInt(this.elementos);  
		
		Pageable  pageRequest =  PageRequest.of(page, elemento);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/cliente/listar", clientes,elemento ) ;
		
		modelo.addAttribute("titulo", "Listado de terceros");
		modelo.addAttribute("cliente", clientes);
		modelo.addAttribute("page",pageRender);
		return "cliente/listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		
		return "/cliente/form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", " Tercero no existe en la Base de datos");
				return "redirect:/listar";
			}
			
			
		} else {
			flash.addFlashAttribute("error", " Tercero no existe");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		flash.addFlashAttribute("success", " Tercero guardado con éxito");
		return "cliente/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, 
			@RequestParam("file") MultipartFile foto, 
			RedirectAttributes flash,	SessionStatus status) {
		
		if(result.hasErrors()) {
			 System.out.println("con errores");
			model.addAttribute("titulo", "Formulario de Cliente");
			return "cliente/form";
		}
		
		if ( !foto.isEmpty()) {
			//Path direccionRecursos = Paths.get("src//main//resources//static///uploads");
			//String rootPath = direccionRecursos.toFile().getAbsolutePath();
			//String rootPath = "c://temp//uploads";

			if (cliente.getId()   != null &&
				cliente.getId()   > 0 &&
			    cliente.getFoto() != null &&
			    cliente.getFoto().length()>0
					) {
				
				uploadFileService.delete(cliente.getFoto());
				log.info("delete: "+cliente.getFoto());
			};
			
			String uniqueFilename  = null;
			
			try {
				uniqueFilename = uploadFileService.copy(foto);				
				log.info("Archivo cargado: "+uniqueFilename);
				cliente.setFoto(uniqueFilename);
				flash.addFlashAttribute("info", "Se ha subido correctamente "+uniqueFilename);
				
			} catch (IOException e1) {				
				e1.printStackTrace();
			}  
			
/*			String uniquefilename = UUID.randomUUID().toString()+"_"+foto.getOriginalFilename();
			Path rootPath = Paths.get("uploads").resolve(uniquefilename);
			Path rootAdsolutePath = rootPath.toAbsolutePath();
			
			log.info(" rootPath: "+rootPath );
			log.info(" rootAdsolutePath: "+rootAdsolutePath );
	*/		
			//byte[] bytes = foto.getBytes();
			//Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
			//Files.write(rutaCompleta, bytes);
          //	Files.copy(foto.getInputStream(), rootAdsolutePath);
			//flash.addFlashAttribute("info", "Se ha subido correctamente "+foto.getOriginalFilename());
			
			//
			//cliente.setFoto(foto.getOriginalFilename());
			
			//
			} 
			
			clienteService.save(cliente);
			status.setComplete();
			
			
			String mensajeFlash = (cliente.getId() != null)? "Tercero Editado con éxito" : " Tercero guardado con éxito "  ;
			flash.addFlashAttribute("success", mensajeFlash );
			
			return "redirect:/cliente/listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		 
		if(id > 0) {
			
			//System.out.println("Dentro de contralador clientes!!! id= " + id );
			
			Cliente cliente = clienteService.findOne(id);			
			
			if (!(cliente.getFoto() ==null)) {
				if (uploadFileService.delete(cliente.getFoto())) {
				  flash.addFlashAttribute("info", " Archivo eliminado con exito "+ cliente.getFoto());
			    };
			};
			
			clienteService.delete(id);
			flash.addFlashAttribute("success", " Tercero eliminado con éxito");						
		}
		
		return "redirect:/cliente/listar";
	}
}