package com.lab1;

import java.util.Objects;

/**
 * Класс мебели
 */
public class Furniture {

  /**
   * Масса НЕТТО мебели
   */
  private int netWeight;

  /**
   * Граница массы
   */
  public static final int MIN_WEIGHT = 1, MAX_WEIGHT = 1000;

  /**
   * Название мебели
   */
  private String title;

  /**
   * Конструктор Furniture без параметров
   */
  public Furniture() {
    this.netWeight = -1;
    this.title = "Unnamed";
  }

  /**
   * Конструктор Furniture с параметрами
   *
   * @param netWeight масса НЕТТО
   * @param title название
   */
  public Furniture(int netWeight, String title) {
    this.netWeight = netWeight;
    this.title = title;
  }

  /**
   * Метод получения веса мебели
   *
   * @return Вес мебели
   */
  public int getNetWeight() {
    return netWeight;
  }

  /**
   * Метод получения навзания мебели
   *
   * @return Название мебели
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return строковое предвставления Furniture
   */
  @Override
  public String toString() {
    return "Furniture{" +
        "netWeight=" + netWeight +
        ", title='" + title + '\'' +
        '}';
  }

  /**
   * Метод сравнения Furniture с Object
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
    Furniture furniture = (Furniture) o;
    return netWeight == furniture.netWeight && Objects.equals(title, furniture.title);
  }

  /**
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(netWeight, title);
  }
}
