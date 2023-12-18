package com.lab1.UI;

import com.lab1.Chair;
import com.lab1.Furniture;
import com.lab1.Inputer;
import com.lab1.Table;
import com.lab1.Wardrobe;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;



/**
 * Основной класс интерфейса
 */

public class UI implements menuEnum {

  /**
   * Поток вывода, поддерживающий русские символы
   */
  private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

  /**
   * Экземпляр класса содержащего методы ввода разных типов
   */
  private final Inputer inp = new Inputer();

  /**
   * Основной список людей
   */
  private final ArrayList<Furniture> furniture = new ArrayList<>();

  public void menu() {
    furniture.add(new Furniture(25, "Навесная полка"));
    furniture.add(new Chair(5, "Стул обеденный", 75, "Стул деревянный"));
    furniture.add(new Chair(5, "Стул обеденный", 75, "Стул деревянный"));
    furniture.add(new Table(14, "Стол ProGamer3000", 15000, "Стол игровой"));
    furniture.add(new Wardrobe(35, "Шкаф Мирана", 4, "Шкаф-гардероб"));



    int choice;

    do {
      // Вывод основного меню
      out.println("""
          1. Добавить мебель
          2. Удалить мебель
          3. Вывести всю мебель
          4. Сравнить два экземпляра мебели
          5. Выход
          """);
      choice = inp.getInt();
      switch (choice) {
        case ADD_FURNITURE -> addFurn();
        case DELETE_FURNITURE -> delFurn();
        case PRINT_FURNITURE -> printFurn();
        case COMPARE_FURNITURE -> compareFurn();
        default -> {
          if (choice != EXIT) {
            out.println("Некорректный ввод");
          }
        }
      }
    } while (choice != EXIT);
  }

  /**
   * Метод меню добавления мебели
   */
  private void addFurn() {
    int choice;
    do {
      // Вывод меню добавления мебели
      out.println("""
          1. Добавить мебель
          2. Добавить стул
          3. Добавить стол
          4. Добавить шкаф
          5. Выйти в меню
          """);
      choice = inp.getInt();
      switch (choice) {
        case FURNITURE -> addHandler(FURNITURE);
        case CHAIR -> addHandler(CHAIR);
        case TABLE -> addHandler(TABLE);
        case WARDROBE -> addHandler(WARDROBE);
        default -> {
          if (choice != EXIT) {
            this.out.println("Некорректный ввод");
          }
        }
      }
    } while (choice != EXIT);

  }

  /**
   * Метод добавления мебели выбранного типа
   *
   * @param furnType выбранный тип мебели
   */
  private void addHandler(int furnType) {
    String data_answer;

    while (true) {
      out.println("Добавлять с входными данными (y/n)?");
      data_answer = inp.getString();
      if (data_answer == null) {
        continue;
      }
      if (data_answer.equals("y") || data_answer.equals("n")) {
        break;
      }
    }

    Furniture furn;
    if (data_answer.equals("y")) {
      int weight = -1;
      String title = "Unnamed";
      boolean isWeight = false, isTitle = false;
      while (!(isTitle & isWeight)) {

        if (!isTitle) {
          out.println("Введите название мебели:");
          title = inp.getString();
          if (title != null) {
            isTitle = true;
          } else {
            out.println("Некорректный ввод");
          }
        }

        if (!isWeight) {
          out.println("Введите вес мебели:");
          weight = inp.getInt();
          if ((Furniture.MIN_WEIGHT < weight & weight < Furniture.MAX_WEIGHT)) {
            isWeight = true;
          } else {
            out.println("Некорректный ввод");
          }
        }
      }
      furn = new Furniture(weight, title);
    } else {
      furn = new Furniture();
    }

    switch (furnType) {
      case FURNITURE -> addFurn(furn);
      case CHAIR -> addChair(furn);
      case TABLE -> addTable(furn);
      case WARDROBE -> addWardrobe(furn);
    }

  }

  /**
   * Метод добавления мебели
   *
   * @param newFurn добавляемая мебель
   */
  private void addFurn(Furniture newFurn) {
    furniture.add(newFurn);
  }

  /**
   * Метод добавления стула
   *
   * @param newFurn исходная мебель с заполненными полями
   */
  private void addChair(@NotNull Furniture newFurn) {
    if (newFurn.getNetWeight() == -1) {
      furniture.add(new Chair());
      return;
    }

    int height = -1;
    String type = "NoneType";
    boolean isHeight = false, isType = false;

    while (!(isHeight & isType)) {

      if (!isHeight) {
        out.println("Введите высоту стула");
        height = inp.getInt();
        if ((Chair.MIN_HEIGHT < height & height < Chair.MAX_HEIGHT)) {
          isHeight = true;
        } else {
          out.println("Некорректный ввод");
        }
      }

      if (!isType) {
        out.println("Введите тип стула");
        type = inp.getString();
        if (type != null) {
          isType = true;
        } else {
          out.println("Некорректный ввод");
        }
      }

    }
    furniture.add(new Chair(newFurn.getNetWeight(), newFurn.getTitle(), height, type));
  }

  /**
   * Метод добавления стола
   *
   * @param newFurn исходная мебель с заполненными полями
   */
  private void addTable(@NotNull Furniture newFurn) {
    if (newFurn.getNetWeight() == -1) {
      furniture.add(new Table());
      return;
    }

    int area = -1;
    String type = "NoneType";
    boolean isArea = false, isType = false;

    while (!(isArea & isType)) {
      if (!isArea) {
        out.println("Введите площадь стола (см²)");
        area = inp.getInt();
        if ((Table.MIN_AREA < area & area < Table.MAX_AREA)) {
          isArea = true;
        } else {
          out.println("Некорректный ввод");
        }
      }

      if (!isType) {
        out.println("Введите тип стола");
        type = inp.getString();
        if (type == null) {
          out.println("Некорректный ввод");
        } else {
          isType = true;
        }
      }
    }
    furniture.add(new Table(newFurn.getNetWeight(), newFurn.getTitle(), area, type));
  }

  /**
   * Метод добавления шкафа
   *
   * @param newFurn исходная мебель с заполненными полями
   */
  private void addWardrobe(@NotNull Furniture newFurn) {
    if (newFurn.getNetWeight() == -1) {
      furniture.add(new Wardrobe());
      return;
    }

    int shelvesCount = -1;
    String type = "NoneType";
    boolean isShelvesCount = false, isType = false;

    while (!(isShelvesCount & isType)) {
      if (!isShelvesCount) {
        out.println("Введите кол-во полок в шкафу");
        shelvesCount = inp.getInt();
        if ((Wardrobe.MIN_SHELVES < shelvesCount & shelvesCount < Wardrobe.MAX_SHELVES)) {
          isShelvesCount = true;
        } else {
          out.println("Некорректный ввод");
        }
      }

      if (!isType) {
        out.println("Введите тип шкафа");
        type = inp.getString();
        if (type == null) {
          out.println("Некорректный ввод");
        } else {
          isType = true;
        }
      }
    }
    furniture.add(new Wardrobe(newFurn.getNetWeight(), newFurn.getTitle(), shelvesCount, type));
  }

  /**
   * Метод удаления мебели по индексу
   */
  private void delFurn() {
    if (furniture.isEmpty()) {
      out.println("Ни одного экземпляра мебели не добавлено");
      return;
    }
    out.println("Введите индекс мебели в списке:");
    int index = inp.getInt();
    if (index < 1 || index > furniture.size()) {
      out.println("Некорректный индекс");
    } else {
      this.out.println(furniture.get(index - 1).getTitle() + " удален из списка");
      furniture.remove(index - 1);
    }
  }

  /**
   * Метод вывода всей мебели
   */
  private void printFurn() {
    if (furniture.isEmpty()) {
      out.println("Ни одного экземпляра мебели не добавлено");
      return;
    }

    int num = 1;
    for (Furniture furniture : furniture) {
      out.printf("№%d\n", num++);
      out.println(furniture);
      out.println("************************");
    }
  }

  /**
   * Метод сравнениня двух экземпляров мебели по индексам
   */
  private void compareFurn() {
    if (furniture.isEmpty()) {
      out.println("Ни одного экземпляра мебели не добавлено");
      return;
    }
    int index1 = 1, index2 = 1;
    boolean isIndex1 = false, isIndex2 = false;

    while (!(isIndex1 & isIndex2)) {
      if (!isIndex1) {
        this.out.println("Введите индекс первого экземпляра мебели");
        index1 = inp.getInt();
        if (index1 > 1 & index1 <= furniture.size()) {
          isIndex1 = true;
        } else {
          out.println("Некорректный индекс");
        }
      }

      if (!isIndex2) {
        this.out.println("Введите индекс второго экземпляра мебели");
        index2 = inp.getInt();
        if (index2 > 1 & index2 <= furniture.size()) {
          isIndex2 = true;
        } else {
          out.println("Некорректный индекс");
        }
      }
    }
    if (furniture.get(index1 - 1).equals(furniture.get(index2 - 1))) {
      out.printf("Мебель под номером %d и %d индентична друг другу\n", index1, index2);
    } else {
      out.printf("Мебель под номером %d и %d отличается друг от друга\n", index1,
          index2);
    }
  }
}