package ru.paskal.Lab4.config;


import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.paskal.Lab4.utils.Inputer;


/**
 * Этот класс представляет собой конфигурацию для приложения Spring.
 */
@Configuration
@ComponentScan("ru.paskal.Lab4")
@PropertySource("classpath:database.properties")
public class SpConfig {

  private final Environment env;

  /**
   * Конструирует новый объект SpConfig с заданным окружением.
   * @param env окружение, которое будет использоваться для конфигурации
   */
  public SpConfig(Environment env) {
    this.env = env;
  }

  /**
   * Создает и настраивает источник данных (DataSource) для приложения на основе предоставленных свойств окружения.
   * @return настроенный источник данных (DataSource) для приложения
   */
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
    dataSource.setUrl("jdbc:postgresql://" + env.getProperty("db.host") + ":" +
        env.getProperty("db.port") + "/" + env.getProperty("db.name"));
    dataSource.setUsername(env.getProperty("db.user"));
    dataSource.setPassword(env.getProperty("db.password"));
    return dataSource;
  }

  /**
   * Создает и настраивает объект JdbcTemplate на основе настроенного источника данных (DataSource).
   * @return настроенный объект JdbcTemplate для приложения
   */
  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }
}

