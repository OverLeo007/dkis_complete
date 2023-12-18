package com.lab1;

import java.util.Objects;

/**
 * Класс шкафа, наледуемый от класса мебели
 */
public class Wardrobe extends Furniture {

  /**
   * Кол-во полок
   */
  private int shelvesCount;
  /**
   * Тип шкафа
   */
  private String type;

  /**
   * Границы кол-ва полок
   */
  public static final int MIN_SHELVES = 1, MAX_SHELVES = 20;

  /**
   * Конструктор Wardrobe без параметров
   */
  public Wardrobe() {
    this.shelvesCount = -1;
    this.type = "NoneType";
  }

  /**
   * Конструктор Wardrobe с параметрами
   *
   * @param netWeight масса НЕТТО
   * @param title название
   * @param shelvesCount кол-во полок
   * @param type тип шкафа
   */
  public Wardrobe(int netWeight, String title, int shelvesCount, String type) {
    super(netWeight, title);
    this.shelvesCount = shelvesCount;
    this.type = type;
  }

  /**
   * @return Кол-во полок шкафа
   */
  public int getShelvesCount() {
    return shelvesCount;
  }

  /**
   * @return тип шкафа
   */
  public String getType() {
    return type;
  }

  /**
   * @return строковое предвставления Wardrobe
   */
  @Override
  public String toString() {
    return "Wardrobe{" +
        "netWeight=" + super.getNetWeight() +
        ", title='" + super.getTitle() + '\'' +
        ", shelvesCount=" + shelvesCount +
        ", type='" + type + '\'' +
        '}';
  }

  /**
   * Метод сравнения Wardrobe с Object
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
    Wardrobe wardrobe = (Wardrobe) o;
    return shelvesCount == wardrobe.shelvesCount && Objects.equals(type, wardrobe.type);
  }

  /**
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), shelvesCount, type);
  }
}
