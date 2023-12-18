package ru.paskal.Lab4.utils;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.paskal.Lab4.dao.InstrumentDAO;
import ru.paskal.Lab4.models.Instrument;

@Component
public class UI implements menuEnum {

  private final Inputer inputer;
  private final InstrumentDAO instrumentDAO;
  private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);


  @Autowired
  public UI(Inputer inputer, InstrumentDAO instrumentDAO) {
    this.inputer = inputer;
    this.instrumentDAO = instrumentDAO;
  }

  public void menu() {

    int choice;

    do {
      this.out.println("""
          1. Добавить инструмент
          2. Вывести все инструменты
          3. Редактировать инструмент
          4. Удалить инструмент
          5. Найти инструменты ниже определенной цены
          6. Очистить таблицу
          7. Добавить случайные инструменты в таблицу
          8. Выход
          """);

      choice = inputer.getInt();
      switch (choice) {
        case ADD_INSTRUMENT -> adder();
        case PRINT_INSTRUMENTS -> printer();
        case EDIT_INSTRUMENT -> editor();
        case DELETE_INSTRUMENT -> deleter();
        case FIND_BY_MAX_PRICE -> priceFilter();
        case WIPE_TABLE -> wiper();
        case ADD_RANDOM_INSTRUMENTS -> randomAdder();
        default -> {
          if (choice != EXIT) {
            this.out.println("Некорректный ввод");
          }
        }
      }

    } while (choice != EXIT);

  }


  private void adder() {
    Instrument instrument = new Instrument();
    boolean isInputValid = false;

    while (!isInputValid) {
      try {
        out.println("Введите название:");
        instrument.setName(inputer.getString());

        out.println("Введите тип:");
        instrument.setType(inputer.getString());

        out.println("Введите бренд:");
        instrument.setBrand(inputer.getString());

        out.println("Введите цену:");
        instrument.setPrice(inputer.getFloat());

        out.println("Введите количество:");
        instrument.setQuantity(inputer.getInt());

        instrumentDAO.insert(instrument);
        isInputValid = true;
        out.println("Объект успешно добавлен в базу данных.");
      } catch (InputMismatchException e) {
        out.println("Произошла ошибка: " + e.getMessage());
        inputer.getString();
      }
    }
  }


  private void printer() {
    printInstrumentList(instrumentDAO.selectAll());
  }

  public void editor() {
    int id;
    Instrument instrument;

    try {
      out.println("Введите id инструмента");
      id = inputer.getInt();
      instrument = instrumentDAO.select(id);
      if (instrument == null) {
        throw new InputMismatchException("Инструмента с данным id не существует");
      }
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
      return;
    }

    int choice;
    do {
      out.println("""
          1. Изменить имя
          2. Изменить тип
          3. Изменить бренд
          4. Изменить цену
          5. Изменить количество
          6. Выйти в меню
          """);
      choice = inputer.getInt();
      switch (choice) {
        case EDIT_NAME -> changeNameHandler(instrument);
        case EDIT_TYPE -> changeTypeHandler(instrument);
        case EDIT_BRAND -> changeBrandHandler(instrument);
        case EDIT_PRICE -> changePriceHandler(instrument);
        case EDIT_QUANTITY -> changeQuantityHandler(instrument);
        case EDIT_EXIT -> out.println("Редактирование завершено");
        default -> out.println("Некорректный ввод!");
      }
    } while (choice != EDIT_EXIT);
    instrumentDAO.update(id, instrument);
  }

  private void changeNameHandler(Instrument instrument) {
    out.println("Введите новое имя:");
    try {
      String newName = inputer.getString();
      // Дополнительная обработка, если необходимо
      instrument.setName(newName);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void changeTypeHandler(Instrument instrument) {
    out.println("Введите новый тип:");
    try {
      String newType = inputer.getString();
      instrument.setType(newType);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void changeBrandHandler(Instrument instrument) {
    out.println("Введите новый бренд:");
    try {
      String newBrand = inputer.getString();
      instrument.setBrand(newBrand);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void changePriceHandler(Instrument instrument) {
    out.println("Введите новую цену:");
    try {
      float newPrice = inputer.getFloat();
      instrument.setPrice(newPrice);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void changeQuantityHandler(Instrument instrument) {
    out.println("Введите новое количество:");
    try {
      int newQuantity = inputer.getInt();
      instrument.setQuantity(newQuantity);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void deleter() {
    int id;
    Instrument instrument;

    try {
      out.println("Введите id инструмента");
      id = inputer.getInt();
      instrument = instrumentDAO.select(id);
      if (instrument == null) {
        throw new InputMismatchException("Инструмента с данным id не существует");
      }
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
      return;
    }
    instrumentDAO.delete(id);
  }

  private void priceFilter() {
    float price;
    try {
      out.println("Введите максимальную цену");
      price = inputer.getFloat();
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
      return;
    }
    List<Instrument> instruments = instrumentDAO.filterPrice(price);
    printInstrumentList(instruments);
  }

  private void wiper() {
    instrumentDAO.wipeDb();
    out.println("База данных очищена");
  }

  private void randomAdder() {
    instrumentDAO.generateRandomInstruments();
    out.println("База данных наполена случайными инструментами");
  }

  private void printInstrumentList(List<Instrument> instruments) {
    for (Instrument instrument : instruments) {
      out.println("ID: " + instrument.getId());
      out.println("Name: " + instrument.getName());
      out.println("Type: " + instrument.getType());
      out.println("Brand: " + instrument.getBrand());
      out.println("Price: " + instrument.getPrice());
      out.println("Quantity: " + instrument.getQuantity());
      out.println("---------------------------");
    }
  }

}
