package fr.insa.mas.LightControl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@SpringBootApplication
@RequestMapping("/LightControl/")
public class LightControlApplication {

	private boolean ON;
	private Logger logger = LoggerFactory.getLogger(LightControlApplication.class);
	public LightControlApplication() {
		this.ON = false;
	}

	
	
	@GetMapping("isON/")
	public ResponseEntity<String> isON() {
	    String emoji = (ON ? "💡" : "🚫"); 
	    String statusMessage = "Light is " + (ON ? "ON" : "OFF") + " " + emoji;
	    
	    String htmlResponse = "<html>" +
	                            "<head>" +
	                                "<style>" +
	                                    "body { font-family: 'Arial', sans-serif; background-color: #3498db; text-align: center; }" +
	                                    ".container { padding: 20px; background-color: #f2f2f2; border-radius: 10px; margin: 50px auto; max-width: 400px; }" +
	                                    "h1 { color: #ffffff; background-color: #3498db; border-radius: 5px; padding: 10px; }" +
	                                    "p { font-size: 18px; color: #333333; }" +
	                                "</style>" +
	                            "</head>" +
	                            "<body>" +
	                                "<div class='container'>" +
	                                    "<h1>" + statusMessage + "</h1>" +
	                                "</div>" +
	                            "</body>" +
	                         "</html>";

	    logger.info(statusMessage);
	    return new ResponseEntity<>(htmlResponse, HttpStatus.OK);
	}


	@GetMapping("setON/")
	public void setON(boolean oN) {
		ON = oN;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LightControlApplication.class, args);
	}

}
