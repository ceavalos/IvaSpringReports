package innotec.com.sv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import innotec.com.sv.services.IUploadFileService;



@SpringBootApplication
public class SrpingExcelApplication implements CommandLineRunner {

	@Autowired
	IUploadFileService uploadFileService;
	
	public static void main(String[] args) {
		SpringApplication.run(SrpingExcelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteall();
		uploadFileService.init();
		
		
	}

}
