package ru.paskal.Lab6.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.paskal.Lab6.models.ShopUser;
import ru.paskal.Lab6.services.ShopUserService;
import ru.paskal.Lab6.util.UserValidator;

/**
 * Контроллер аутентификации и регистрации пользователей.
 */
@RequestMapping("/auth")
@Controller
public class AuthController {

  private final UserValidator userValidator;

  private final ShopUserService userService;

  /**
   * Конструктор для AuthController.
   *
   * @param userValidator Валидатор пользователя.
   * @param userService   Сервис пользователя магазина.
   */
  @Autowired
  public AuthController(UserValidator userValidator, ShopUserService userService) {
    this.userValidator = userValidator;
    this.userService = userService;
  }

  /**
   * Обработчик GET-запроса для страницы входа.
   *
   * @return Имя представления страницы входа.
   */
  @GetMapping("/login")
  public String loginPage() {
    return "auth/login";
  }

  /**
   * Обработчик GET-запроса для страницы регистрации.
   *
   * @param shopUser Модель пользователя магазина.
   * @return Имя представления страницы регистрации.
   */
  @GetMapping("/register")
  public String regPage(@ModelAttribute("user") ShopUser shopUser) {
    return "auth/registration";
  }

  /**
   * Обработчик POST-запроса для обработки регистрации пользователя.
   *
   * @param shopUser       Модель пользователя магазина.
   * @param bindingResult  Результаты связывания данных и валидации.
   * @return Имя представления страницы регистрации, если есть ошибки, в противном случае перенаправление на страницу входа.
   */
  @PostMapping("/process_reg")
  public String doReg(@ModelAttribute("user") @Valid ShopUser shopUser, BindingResult bindingResult) {
    userValidator.validate(shopUser, bindingResult);
    if (bindingResult.hasErrors()) {
      return "auth/registration";
    } else {
      userService.register(shopUser);
      return "redirect:/auth/login";
    }
  }

}
