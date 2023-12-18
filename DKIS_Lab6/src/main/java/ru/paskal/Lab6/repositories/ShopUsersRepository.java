package ru.paskal.Lab6.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.paskal.Lab6.models.ShopUser;

/**
 * Репозиторий для работы с пользователями магазина.
 */
@Repository
public interface ShopUsersRepository extends JpaRepository<ShopUser, Integer> {

  /**
   * Поиск пользователя по имени пользователя.
   *
   * @param username Имя пользователя для поиска.
   * @return Optional с найденным пользователем или пустой, если пользователь не найден.
   */
  Optional<ShopUser> findByUsername(String username);
}
