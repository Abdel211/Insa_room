package fr.insa.mas.AlarmControl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@RestController
@RequestMapping("/AlarmControl/")
public class AlarmControlApplication {

  private boolean alarmTriggered = false;
  private Logger logger = LoggerFactory.getLogger(AlarmControlApplication.class);
  public static void main(String[] args) {
    SpringApplication.run(AlarmControlApplication.class, args);
  }

  @GetMapping("/status")
  public ResponseEntity<String> getAlarmStatus() {
      String statusMessage = "Alarm Status: " + (alarmTriggered ? "Triggered 🔔" : "Not Triggered 🔕");

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

  
  @GetMapping("/setStatus/")
  public void setAlarmStatus(boolean oN) {
	  alarmTriggered = oN;
  }
	
}
