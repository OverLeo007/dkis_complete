package ru.paskal.Lab4.dao;

import java.sql.Types;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.paskal.Lab4.models.Instrument;


/**
 * Этот класс представляет собой объект доступа к данным (DAO) для музыкальных инструментов.
 * Он обеспечивает доступ к базе данных для выполнения операций CRUD (создание, чтение, обновление, удаление) с данными инструментов.
 */
@Component
public class InstrumentDAO {

  private final JdbcTemplate jdbcTemplate;

  /**
   * Конструирует новый объект InstrumentDAO с заданным JdbcTemplate.
   * @param jdbcTemplate JdbcTemplate для доступа к базе данных
   */
  public InstrumentDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Возвращает список всех инструментов из базы данных.
   * @return список всех инструментов
   */
  public List<Instrument> selectAll() {
    return jdbcTemplate.query(
        "SELECT * FROM MusicalInstrument",
        new BeanPropertyRowMapper<>(Instrument.class)
    );
  }

  /**
   * Выбирает инструмент из базы данных по заданному идентификатору.
   * @param id идентификатор инструмента
   * @return выбранный инструмент
   */
  public Instrument select(int id) {
    String sql = "SELECT * FROM MusicalInstrument WHERE id = ?";
    return jdbcTemplate.query(
        sql,
        new Object[]{id},
        new int[]{Types.INTEGER},
        new BeanPropertyRowMapper<>(Instrument.class))
        .stream().findAny().orElse(null);
  }

  /**
   * Вставляет новый инструмент в базу данных.
   * @param instrument инструмент для вставки
   */
  public void insert(Instrument instrument) {
    String sql = "INSERT INTO musicalinstrument(name, type, brand, price, quantity) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(
        sql,
        instrument.getName(),
        instrument.getType(),
        instrument.getBrand(),
        instrument.getPrice(),
        instrument.getQuantity()
        );
  }

  /**
   * Обновляет инструмент в базе данных по заданному идентификатору.
   * @param id идентификатор инструмента для обновления
   * @param uInstrument обновленный инструмент
   */
  public void update(int id, Instrument uInstrument) {
    String sql = "UPDATE musicalinstrument SET "
        + "name = ?, "
        + "type = ?, "
        + "brand = ?, "
        + "price = ?, "
        + "quantity = ? "
        + "WHERE id = ?";
    jdbcTemplate.update(
        sql,
        uInstrument.getName(),
        uInstrument.getType(),
        uInstrument.getBrand(),
        uInstrument.getPrice(),
        uInstrument.getQuantity(),
        id
    );
  }

  /**
   * Удаляет инструмент из базы данных по заданному идентификатору.
   * @param id идентификатор инструмента для удаления
   */
  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM musicalinstrument WHERE id = ?", id);
  }

  /**
   * Фильтрует инструменты по цене, не превышающей заданную максимальную цену.
   * @param maxPrice максимальная цена для фильтрации
   * @return отфильтрованный список инструментов
   */
  public List<Instrument> filterPrice(float maxPrice) {
    List<Instrument> filteredInstruments = jdbcTemplate.query(
        "SELECT * FROM musicalinstrument WHERE price <= ?",
        new Object[]{maxPrice},
        new int[]{Types.FLOAT},
        new BeanPropertyRowMapper<>(Instrument.class));
    filteredInstruments.sort((i1, i2) -> Float.compare(i2.getPrice(), i1.getPrice()));
    return filteredInstruments;
  }

  /**
   * Генерирует случайные инструменты и вставляет их в базу данных.
   */
  public void generateRandomInstruments() {
    String[] names = {"Guitar", "Piano", "Violin", "Drums", "Flute", "Saxophone", "Trumpet", "Cello", "Harp", "Bass"};
    String[] types = {"String", "Percussion", "Wind", "Keyboard", "Brass"};

    Random random = new Random();

    for (int i = 0; i < 50; i++) {
      String name = names[random.nextInt(10)] + " " + (i + 1);
      String type = types[random.nextInt(5)];
      String brand = "Brand" + (i + 1);
      double price = random.nextDouble() * 1000 + 100; // цены от 100 до 1100
      int quantity = random.nextInt(100);

      String sql = "INSERT INTO MusicalInstrument (name, type, brand, price, quantity) "
          + "VALUES (?, ?, ?, ?, ?);";

      jdbcTemplate.update(sql, name, type, brand, price, quantity);
    }
  }

  /**
   * Очищает базу данных от всех записей об инструментах.
   */
  public void wipeDb() {
    jdbcTemplate.update("DELETE FROM musicalinstrument");
  }

}
