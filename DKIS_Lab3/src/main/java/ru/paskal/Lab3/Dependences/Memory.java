package ru.paskal.Lab3.Dependences;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
/**
 * Класс чипа - памяти, обладающий размером
 */
public class Memory implements ChipComponent{

  @PostConstruct
  private void init() {
    System.out.println("MemoryChip initialized");
  }

  /**
   * Размер памяти в GB
   */
  private final int size;

  /**
   * Конструктор класса
   *
   * @param size размер памяти чипа в GB
   */
  public Memory(@Value("${memory.size}") int size) {
    this.size = size;
  }

  /**
   * Запуск работы чипа, говорящий нам о том, сколько GB данных он может хранить
   */
  @Override
  public void process() {
    System.out.println("Storing " + size + "GB of data by RAM.");
  }

  @PreDestroy
  private void destroy() {
    System.out.println("MemoryChip destroyed");
  }
}
