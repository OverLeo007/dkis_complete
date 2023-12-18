package ru.paskal.Lab6.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.paskal.Lab6.models.ShopUser;
import ru.paskal.Lab6.repositories.ShopUsersRepository;
import ru.paskal.Lab6.security.ShopUserDetails;

/**
 * Сервис для загрузки пользовательских данных из репозитория пользователей магазина.
 */
@Service
public class ShopUserDetailsService implements UserDetailsService {

  private final ShopUsersRepository shopUsersRepository;

  /**
   * Конструктор для ShopUserDetailsService.
   *
   * @param shopUsersRepository Репозиторий пользователей магазина.
   */
  @Autowired
  public ShopUserDetailsService(ShopUsersRepository shopUsersRepository) {
    this.shopUsersRepository = shopUsersRepository;
  }

  /**
   * Получает пользователя по его имени
   *
   * @param username имя пользователя
   * @return ShopUserDetails для пользователя
   * @throws UsernameNotFoundException если пользователь не был найден
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<ShopUser> user = shopUsersRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return new ShopUserDetails(user.get());
  }
}
