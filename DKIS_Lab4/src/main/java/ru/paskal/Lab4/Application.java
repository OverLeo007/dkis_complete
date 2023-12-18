package ru.paskal.Lab4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.paskal.Lab4.config.SpConfig;
import ru.paskal.Lab4.utils.UI;

/**
 * Точка входа
 */
public class Application {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpConfig.class);
    UI ui = context.getBean("UI", UI.class);
    ui.menu();
  }
}
