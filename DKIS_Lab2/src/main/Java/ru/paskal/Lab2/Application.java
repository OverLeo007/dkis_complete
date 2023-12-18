package ru.paskal.Lab2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * mainClass программы
 */
public class Application {

  /**
   * Точка входа в программу
   */
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext.xml"
    );

    Microchip cpuMicrochip = context.getBean("cpuMicrochip", Microchip.class);
    Microchip ramMicrochip = context.getBean("ramMicrochip", Microchip.class);

    cpuMicrochip.run();
    ramMicrochip.run();

  }
}
