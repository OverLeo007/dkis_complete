package ru.paskal.Lab8.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.paskal.Lab8.models.MusicalInstrument;

/**
 * Репозиторий для работы с музыкальными инструментами.
 */
//@Profile("profileBack")
@Repository
public interface MusicalInstrumentRepository extends JpaRepository<MusicalInstrument, Integer> {

  /**
   * SQL-запрос для вставки примеров инструментов в базу данных.
   */
  String insertExampleSQL = """
      INSERT INTO musical_instrument (instrument_name, instrument_type, brand, price, quantity)\s
      VALUES
          ('Acoustic Guitar', 'String', 'Fender', 799.99, 5),
          ('Electric Keyboard', 'Keyboard', 'Yamaha', 499.99, 3),
          ('Violin', 'String', 'Stradivarius', 5000.00, 2),
          ('Drum Set', 'Percussion', 'Pearl', 1200.50, 4),
          ('Saxophone', 'Wind', 'Yanagisawa', 3000.00, 2),
          ('Grand Piano', 'Keyboard', 'Steinway & Sons', 15000.00, 1),
          ('Flute', 'Wind', 'Muramatsu', 2000.00, 3),
          ('Bass Guitar', 'String', 'Ibanez', 899.99, 4),
          ('Trumpet', 'Brass', 'Bach', 1500.00, 2),
          ('Cello', 'String', 'D Z Strad', 3000.00, 2);
      """;

  /**
   * Поиск музыкальных инструментов по цене, не превышающей заданную.
   *
   * @param price  цена
   * @return  список музыкальных инструментов
   */
  List<MusicalInstrument> findByPriceLessThanEqual(float price);

  /**
   * Вставка примеров инструментов в базу данных.
   */
  @Modifying
  @Query(value = insertExampleSQL, nativeQuery = true)
  void insertExample();

}
