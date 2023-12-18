package ru.paskal.Lab6.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.paskal.Lab6.models.ShopUser;

/**
 * Реализация интерфейса UserDetails для пользователя магазина.
 */
public class ShopUserDetails implements UserDetails {

  private final ShopUser shopUser;

  /**
   * Конструктор для ShopUserDetails.
   *
   * @param shopUser Пользователь магазина.
   */
  public ShopUserDetails(ShopUser shopUser) {
    this.shopUser = shopUser;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(shopUser.getRole()));
  }

  @Override
  public String getPassword() {
    return shopUser.getPassword();
  }

  @Override
  public String getUsername() {
    return shopUser.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * Получает объект пользователя магазина.
   *
   * @return Объект пользователя магазина.
   */
  public ShopUser getUser() {
    return shopUser;
  }
}
