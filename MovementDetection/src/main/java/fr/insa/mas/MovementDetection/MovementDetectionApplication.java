package fr.insa.mas.MovementDetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@RestController
@SpringBootApplication
@RequestMapping("/MovementDetection/")
public class MovementDetectionApplication {

	private boolean detection;
	private int nbDetection;
	private Logger logger = LoggerFactory.getLogger(MovementDetectionApplication.class);
	
	//Constructor
	public MovementDetectionApplication() {
		this.detection = false;
		this.nbDetection = 0;
	}
	
	//Methods

	@GetMapping("getDetection/")
	public boolean getDetection() {
		return this.detection;
	}
	@GetMapping("setDetection/")
	public ResponseEntity<String> setDetection(boolean detection) {
	    this.detection = detection;

	    LocalDateTime currentDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = currentDateTime.format(formatter);

	    String action = (detection ? "enabled" : "disabled");
	    String statusMessage = "Motion detection has been " + action;
	    String emoji = (detection ? "🔍" : "🚫");

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
	            "<h1>" + statusMessage + " " + emoji + "</h1>" +
	            "<p>Set at: " + formattedDateTime + "</p>" +
	            "</div>" +
	            "</body>" +
	            "</html>";

	    logger.info(statusMessage);
	    return new ResponseEntity<>(htmlResponse, HttpStatus.OK);
	}

	
	@GetMapping("getNbDetection/")
	public int getNbDetection() {
		return this.nbDetection;
	}
	
	@GetMapping("setNbDetection/")
	public void setNbDetection(int detection) {
		this.nbDetection = detection;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovementDetectionApplication.class, args);
	}

}
