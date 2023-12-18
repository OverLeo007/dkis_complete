package ru.paskal.Lab3.Dependences;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
/**
 * Класс чипа - процессора, обладающиего моделью
 */
public class Processor implements ChipComponent {


  @PostConstruct
  private void init() {
    System.out.println("ProcessorChip initialized");
  }

  /**
   * Модель процессора
   */
  private final String model;

  public static Processor processorFactory(String model) {
    System.out.println(" ----> Cpu created via factory");
    return new Processor(model);
  }

  /**
   * Конструктор класса
   *
   * @param model модель процессора
   */
  public Processor(String model) {
    this.model = model;
  }

  /**
   * Запуск работы чипа, говорящий нам о том, какой модели процессор установлен
   */
  @Override
  public void process() {
    System.out.println("Processing data by " + model);
  }

  @PreDestroy
  private void destroy() {
    System.out.println("ProcessorChip destroyed");
  }
}
