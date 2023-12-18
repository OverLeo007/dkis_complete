package ru.paskal.Lab8;

import jakarta.jms.JMSException;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.paskal.Lab8.messaging.MessageReceiver;

@SpringBootApplication
public class Lab8Application {

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lab8Application.class, args);
		MessageReceiver messageReceiver = context.getBean(MessageReceiver.class);
		messageReceiver.startReceivingMessages();
	}

}
