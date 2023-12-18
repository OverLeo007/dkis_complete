package ru.paskal.Lab2.Dependences;

import ru.paskal.Lab2.Dependences.Component;

/**
 * Класс чипа - процессора, обладающиего моделью
 */
public class Processor implements Component {

  /**
   * Модель процессора
   */
  private final String model;


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
}
