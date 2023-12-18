package com.lab1;

import java.util.Objects;

/**
 * Класс стола, наледуемый от класса мебели
 */
public class Table extends Furniture {

  /**
   * Площадь стола
   */
  private int area;

  /**
   * Тип стола
   */
  private String type;

  /**
   * Границы площади в см^2
   */
  public static final int MIN_AREA = 1500, MAX_AREA = 50000;

  /**
   * Конструктор Table без параметров
   */
  public Table() {
    this.area = -1;
    this.type = "NoneType";
  }

  /**
   * Конструктор Table с параметрами
   *
   * @param netWeight масса НЕТТО
   * @param title название
   * @param area площадь стола
   * @param type тип стола
   */
  public Table(int netWeight, String title, int area, String type) {
    super(netWeight, title);
    this.area = area;
    this.type = type;
  }

  /**
   * @return площадь стола
   */
  public int getArea() {
    return area;
  }

  /**
   * @return тип стола
   */
  public String getType() {
    return type;
  }

  /**
   * @return строковое предвставления Table
   */
  @Override
  public String toString() {
    return "Table{" +
        "netWeight=" + super.getNetWeight() +
        ", title='" + super.getTitle() + '\'' +
        ", area=" + area +
        ", type='" + type + '\'' +
        '}';
  }

  /**
   * Метод сравнения Table с Object
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
    Table table = (Table) o;
    return area == table.area && Objects.equals(type, table.type);
  }

  /**
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), area, type);
  }
}
