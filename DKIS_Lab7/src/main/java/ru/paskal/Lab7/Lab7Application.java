package ru.paskal.Lab7;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.paskal.Lab7.services.ClientService;


@SpringBootApplication
public class Lab7Application {
	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}

}
