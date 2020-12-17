package innotec.com.sv.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	
@PropertySource("classpath:configuraciones.properties")
public class PropertiesSource {

	/*private final Logger log = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		String resourcesPath = Paths.get("uploads").toAbsolutePath().toUri().toString();
		
		registry.addResourceHandler("/uploads/**").addResourceLocations(resourcesPath);
		
		log.info("resourcesPath: "+resourcesPath);

	}*/

	
	
}
