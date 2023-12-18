package ru.paskal.Lab6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.paskal.Lab6.models.ShopUser;
import ru.paskal.Lab6.repositories.ShopUsersRepository;

/**
 * Сервис для работы с пользователями магазина.
 */
@Service
@Transactional(readOnly = true)
public class ShopUserService {

  private final ShopUsersRepository shopUsersRepository;

  private final PasswordEncoder passwordEncoder;

  /**
   * Конструктор для ShopUserService.
   *
   * @param shopUsersRepository Репозиторий пользователей магазина.
   * @param passwordEncoder    Кодировщик паролей.
   */
  @Autowired
  public ShopUserService(ShopUsersRepository shopUsersRepository, PasswordEncoder passwordEncoder) {
    this.shopUsersRepository = shopUsersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Проверяет наличие пользователя с указанным именем.
   *
   * @param username Имя пользователя для проверки.
   * @return true, если пользователь существует, в противном случае false.
   */
  public boolean hasUsername(String username) {
    return shopUsersRepository.findByUsername(username).isPresent();
  }

  /**
   * Регистрирует нового пользователя.
   *
   * @param shopUser Модель пользователя магазина.
   */
  @Transactional
  public void register(ShopUser shopUser) {
    shopUser.setPassword(passwordEncoder.encode(shopUser.getPassword()));
    if (shopUser.getUsername().equals("admin")) {
      shopUser.setRole("ROLE_ADMIN");
    } else {
      shopUser.setRole("ROLE_USER");
    }
    shopUsersRepository.save(shopUser);
  }


}
