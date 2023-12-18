package ru.paskal.Lab4.models;


/**
 * Класс, представляющий собой модель инструмента
 */
public class Instrument {

  private int id;
  private String name;
  private String type;
  private String brand;
  private float price;
  private int quantity;

  public Instrument() {
    this.name = null;
    this.type = null;
    this.brand = null;
    this.price = 0;
    this.quantity = 0;
  }

  public Instrument(int id, String name, String type, String brand, float price, int quantity) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.brand = brand;
    this.price = price;
    this.quantity = quantity;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}