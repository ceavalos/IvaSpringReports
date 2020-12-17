package innotec.com.sv.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import innotec.com.sv.dao.IempresaDao;
import innotec.com.sv.entities.Empresa;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Controller
@RequestMapping(value = "/api")
public class ReportService   {
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
	final ModelAndView model = new ModelAndView();
	    
	@Autowired
	IempresaDao empresaServ;
	
	 // Method to display the index page of the application.
    @GetMapping(value= "/welcome")
    public ModelAndView index() {
        log.info("Showing the welcome page.");
        model.setViewName("welcome");
        return model;
    } 
    
	 @GetMapping(value = "/view")
	    public ModelAndView viewReport() {
	        log.info("Preparing the pdf report via jasper.");
	        
	        try {
	        	createPdfReport ( (List<Empresa>) empresaServ.findAll());
	            log.info("File successfully saved at the given path.");
	        } catch (final Exception e) {
	            log.error("Some error has occurred while preparing the empresa pdf report.");
	            e.printStackTrace();
	        }
	        // Returning the view name as the index page for ease.
	        model.setViewName("welcome");
	        return model;
	    }
	 
	 
	public String  createPdfReport(List<Empresa> empresa)throws JRException, IOException, InterruptedException {
		
		//List<Empresa> empresa =  (List<Empresa>) empresaServ.findAll();

		 // Fetching the .jrxml file from the resources folder.
       final InputStream stream = this.getClass().getResourceAsStream("/empresas2.jrxml");

        // Compile the Jasper report from .jrxml to .japser
          final JasperReport report = JasperCompileManager.compileReport(stream);

        // Fetching the employees from the data source.
        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(empresa);
 
         // Adding the additional parameters to the pdf.
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "javacodegeek.com");

        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
 
        // Users can change as per their project requrirements or can take it as request input requirement.
        // For simplicity, this tutorial will automatically place the file under the "c:" drive.
        // If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
        final String filePath = "c:\\Angular\\";
        // Export the report to a PDF file.
        JasperExportManager.exportReportToPdfFile(print, filePath + "Empresa_report.pdf");
		 /**/
        
      /*  if ((new File(filePath + "Empresa_report.pdf")).exists()) {
        	Process p = Runtime.getRuntime().exec(filePath + "Empresa_report.pdf");
        	p.waitFor();
        }	
        */
        System.out.println("Done");
        

		return "Report successfully generated @path= " + filePath;
		
	}

	
	

	private static final String logo_path = "/jasper/images/stackextend-logo.png";
	private final String invoice_template_path = "/Empresa_report.jrxml";
	

    public void generateInvoiceFor(Empresa order, Locale locale) throws IOException {

        File pdfFile = File.createTempFile("my-invoice", ".pdf");

        try(FileOutputStream pos = new FileOutputStream(pdfFile))
        {
        	
        	final InputStream stream = this.getClass().getResourceAsStream("/empresas2.jrxml");

             // Compile the Jasper report from .jrxml to .japser
            final JasperReport report = JasperCompileManager.compileReport(stream);

               
			// Load the invoice jrxml template.
            // final JasperReport report = loadTemplate();

              // Create parameters map.
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "javacodegeek.com");
            
            // Create an empty datasource.
         //   final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Empresa"));

            // Render the PDF file
            //JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

        }
        catch (final Exception e)
        {
            log.error(String.format("An error occured during PDF creation: %s", e));
        }
    }
}
