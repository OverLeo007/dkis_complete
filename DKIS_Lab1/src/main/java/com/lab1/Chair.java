package com.lab1;

import java.util.Objects;

/**
 * Класс стула, наледуемый от класса мебели
 */
public class Chair extends Furniture {

  /**
   * Высота стула
   */
  private int height;

  /**
   * Тип стула
   */
  private String type;

  /**
   * Граница высоты стула в см
   */
  public static final int MIN_HEIGHT = 50, MAX_HEIGHT = 130;

  /**
   * Конструктор Chair без параметров
   */
  public Chair() {
    this.height = -1;
    this.type = "NoneType";
  }

  /**
   * Конструктор Chair с параметрами
   *
   * @param netWeight масса НЕТТО
   * @param title название
   * @param height высота стула
   * @param type тип стула
   */
  public Chair(int netWeight, String title, int height, String type) {
    super(netWeight, title);
    this.height = height;
    this.type = type;
  }

  /**
   * @return Высота стула
   */
  public int getHeight() {
    return height;
  }

  /**
   * @return Тип стула
   */
  public String getType() {
    return type;
  }

  /**
   * @return строковое предвставления Chair
   */
  @Override
  public String toString() {
    return "Chair{" +
        "netWeight=" + super.getNetWeight() +
        ", title='" + super.getTitle() + '\'' +
        ", height=" + height +
        ", type='" + type + '\'' +
        '}';
  }


  /**
   * Метод сравнения Chair с Object
   *
   * @param o сравниваемый Object
   * @return true при эквивалентности объектов false при их различности
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Chair chair = (Chair) o;
    return height == chair.height && Objects.equals(type, chair.type);
  }


  /**
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), height, type);
  }
}
