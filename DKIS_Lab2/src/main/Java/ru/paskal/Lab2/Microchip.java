package ru.paskal.Lab2;

import java.beans.ConstructorProperties;
import ru.paskal.Lab2.Dependences.Component;

/**
 * Класс микросхемы определенного типа, модели и id
 */
public class Microchip {

  /**
   * Основной компонент микросхемы, отвечающий за ее тип
   */
  private final Component component;
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
   * @param component основной компонент микросхемы
   * @param model модель микросхемы
   */
  @ConstructorProperties({"component", "model"})
  public Microchip(Component component, String model) {
    this.component = component;
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
    component.process();
  }

}
