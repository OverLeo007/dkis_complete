package ru.paskal.Lab3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * mainClass программы
 */
public class Application {

  /**
   * Точка входа в программу
   */
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        AppConfig.class
    );

    Microchip cpuMicrochip = context.getBean("cpuMicrochip", Microchip.class);
    Microchip ramMicrochip = context.getBean("ramMicrochip", Microchip.class);
    System.out.println("------------------------");
    cpuMicrochip.run();
    ramMicrochip.run();
    System.out.println("------------------------");

    context.close();
  }
}
