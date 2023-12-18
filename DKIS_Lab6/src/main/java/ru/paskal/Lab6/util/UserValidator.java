package ru.paskal.Lab6.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.paskal.Lab6.models.ShopUser;
import ru.paskal.Lab6.services.ShopUserService;

/**
 * Валидатор для проверки уникальности имени пользователя.
 */
@Component
public class UserValidator implements Validator {

  private final ShopUserService shopUserService;

  /**
   * Конструктор для UserValidator.
   *
   * @param shopUserService Сервис пользователей магазина.
   */
  @Autowired
  public UserValidator(ShopUserService shopUserService) {
    this.shopUserService = shopUserService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return ShopUser.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ShopUser shopUser = (ShopUser) target;
    if (shopUserService.hasUsername(shopUser.getUsername())) {
      errors.rejectValue("username", "", "This username was already taken!");
    }

  }
}
