package ru.paskal.Lab2.Dependences;

/**
 * Класс чипа - памяти, обладающий размером
 */
public class Memory implements Component{

  /**
   * Размер памяти в GB
   */
  private final int size;

  /**
   * Конструктор класса
   *
   * @param size размер памяти чипа в GB
   */
  public Memory(int size) {
    this.size = size;
  }

  /**
   * Запуск работы чипа, говорящий нам о том, сколько GB данных он может хранить
   */
  @Override
  public void process() {
    System.out.println("Storing " + size + "GB of data by RAM.");
  }
}
