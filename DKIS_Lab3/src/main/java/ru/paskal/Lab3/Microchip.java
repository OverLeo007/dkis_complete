package ru.paskal.Lab3;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import ru.paskal.Lab3.Dependences.ChipComponent;

/**
 * Класс микросхемы определенного типа, модели и id
 */
public class Microchip {

  @PostConstruct
  private void init() {
    System.out.println("Microchip initialized");
  }

  /**
   * Основной компонент микросхемы, отвечающий за ее тип
   */
  private final ChipComponent chipComponent;
  /**
   * Модель микросхемы
   */
  private final String model;

  /**
   * Айди микросхемы
   */
  private int id;

  /**
   * Конструктор класса
   *
   * @param chipComponent основной компонент микросхемы
   * @param model модель микросхемы
   */
  public Microchip(ChipComponent chipComponent, String model) {
    this.chipComponent = chipComponent;
    this.model = model;
  }

  /**
   * @param id Сеттер айди микросхемы
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Метод запуска микросхемы
   */
  public void run() {
    System.out.println("This " + model + " " + id + " microchip can:");
    chipComponent.process();
  }

  @PreDestroy
  private void destroy() {
    System.out.println("Microchip destroyed");
  }
}
