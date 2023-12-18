package ru.paskal.Lab5.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "musical_instrument")
public class MusicalInstrument {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "instrument_name")
  @Size(min = 3, max = 30, message = "len of name is not between 3 and 30")
  private String name;

  @Size(min = 3, max = 30, message = "len of type is not between 3 and 30")
  @Column(name = "instrument_type")
  private String type;

  @Size(min = 3, max = 30, message = "len of brand is not between 3 and 30")
  @Column(name = "brand")
  private String brand;

  @Min(value = 1, message = "we cant sell it for free!")
  @Column(name = "price")
  private float price;

  @Min(value = 0, message = "What's on your mind?")
  @Column(name = "quantity")
  private int quantity;

  public MusicalInstrument() {
  }

  public MusicalInstrument(
      String name,
      String type,
      String brand,
      float price,
      int quantity
  ) {
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

  @Override
  public String toString() {
    return "MusicalInstrument{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", brand='" + brand + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MusicalInstrument that = (MusicalInstrument) o;
    return Float.compare(that.getPrice(), getPrice()) == 0
        && getQuantity() == that.getQuantity() && Objects.equals(getName(), that.getName())
        && Objects.equals(getType(), that.getType()) && Objects.equals(getBrand(),
        that.getBrand());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getType(), getBrand(), getPrice(), getQuantity());
  }
}
