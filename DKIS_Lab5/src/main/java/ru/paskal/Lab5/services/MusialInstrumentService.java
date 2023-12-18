package ru.paskal.Lab5.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.paskal.Lab5.models.MusicalInstrument;
import ru.paskal.Lab5.repositories.MusicalInstrumentRepository;

/**
 * Сервис для работы с музыкальными инструментами.
 */
@Service
@Transactional(readOnly = true)
public class MusialInstrumentService {

  private final MusicalInstrumentRepository musicalInstrumentRepository;

  /**
   * Конструктор сервиса.
   *
   * @param musicalInstrumentRepository репозиторий музыкальных инструментов
   */
  @Autowired
  public MusialInstrumentService(
      MusicalInstrumentRepository musicalInstrumentRepository) {
    this.musicalInstrumentRepository = musicalInstrumentRepository;
  }

  /**
   * Получает все музыкальные инструменты.
   *
   * @return список музыкальных инструментов
   */
  public List<MusicalInstrument> findAll() {
    return musicalInstrumentRepository.findAll();
  }

  /**
   * Находит музыкальный инструмент по идентификатору.
   *
   * @param id идентификатор инструмента
   * @return найденный инструмент или null, если не найден
   */
  public MusicalInstrument findOne(int id) {
    return musicalInstrumentRepository.findById(id).orElse(null);
  }

  /**
   * Сохраняет новый музыкальный инструмент.
   *
   * @param musicalInstrument объект музыкального инструмента
   */
  @Transactional
  public void save(MusicalInstrument musicalInstrument) {
    musicalInstrumentRepository.save(musicalInstrument);
  }

  /**
   * Обновляет информацию о музыкальном инструменте.
   *
   * @param id                 идентификатор инструмента
   * @param musicalInstrument  объект музыкального инструмента
   */
  @Transactional
  public void update(int id, MusicalInstrument musicalInstrument) {
    musicalInstrument.setId(id);
    musicalInstrumentRepository.save(musicalInstrument);
  }

  /**
   * Удаляет музыкальный инструмент по идентификатору.
   *
   * @param id идентификатор инструмента
   */
  @Transactional
  public void delete(int id) {
    musicalInstrumentRepository.deleteById(id);
  }

  /**
   * Фильтрует музыкальные инструменты по цене, не превышающей заданное значение.
   *
   * @param maxPrice максимальная цена
   * @return список отфильтрованных музыкальных инструментов
   */
  public List<MusicalInstrument> filterByPrice(float maxPrice) {
    return musicalInstrumentRepository.findByPriceLessThanEqual(maxPrice);
  }


  /**
   * Заполняет базу данных примерами инструментов.
   */
  @Transactional
  public void fillExample() {
    musicalInstrumentRepository.insertExample();
  }

  /**
   * Удаляет все музыкальные инструменты из базы данных.
   */
  @Transactional
  public void wipe() {
    musicalInstrumentRepository.deleteAll();
  }
}
