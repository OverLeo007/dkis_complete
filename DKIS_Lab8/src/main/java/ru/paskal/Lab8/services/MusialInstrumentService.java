package ru.paskal.Lab8.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.paskal.Lab8.exceptions.notFound.MusicalInstrumentNotFoundException;
import ru.paskal.Lab8.models.MusicalInstrument;
import ru.paskal.Lab8.repositories.MusicalInstrumentRepository;

/**
 * Сервис для работы с музыкальными инструментами.
 */
//@Profile("profileBack")
@Service
@Transactional(readOnly = true)
public class MusialInstrumentService {

  private final MusicalInstrumentRepository repository;

  /**
   * Конструктор сервиса.
   *
   * @param repository репозиторий музыкальных инструментов
   */
  @Autowired
  public MusialInstrumentService(
      MusicalInstrumentRepository repository) {
    this.repository = repository;
  }

  /**
   * Получает все музыкальные инструменты.
   *
   * @return список музыкальных инструментов
   */
  public List<MusicalInstrument> findAll() {
    return repository.findAll().stream()
        .filter(musicalInstrument -> musicalInstrument.getQuantity() > 0)
        .collect(Collectors.toList());
  }

  /**
   * Находит музыкальный инструмент по идентификатору.
   *
   * @param id идентификатор инструмента
   * @return найденный инструмент или null, если не найден
   */
  public MusicalInstrument findOne(int id) throws MusicalInstrumentNotFoundException {
    return repository.findById(id).orElseThrow(() -> new MusicalInstrumentNotFoundException(id));
  }

  /**
   * Сохраняет новый музыкальный инструмент.
   *
   * @param musicalInstrument объект музыкального инструмента
   */
  @Transactional
  public void save(MusicalInstrument musicalInstrument) {
    repository.save(musicalInstrument);
  }

  /**
   * Обновляет информацию о музыкальном инструменте.
   *
   * @param id                идентификатор инструмента
   * @param musicalInstrument объект музыкального инструмента
   */
  @Transactional
  public void update(int id, MusicalInstrument musicalInstrument) throws MusicalInstrumentNotFoundException {
    if (repository.existsById(id)) {
      musicalInstrument.setId(id);
      repository.save(musicalInstrument);
    } else {
      throw new MusicalInstrumentNotFoundException(id);
    }

  }

  /**
   * Удаляет музыкальный инструмент по идентификатору.
   *
   * @param id идентификатор инструмента
   */
  @Transactional
  public void delete(int id) throws MusicalInstrumentNotFoundException {
    if (repository.existsById(id)) {
      repository.deleteById(id);
    } else {
      throw new MusicalInstrumentNotFoundException(id);
    }
  }

  @Transactional
  public void buy(int id) {
    MusicalInstrument instrument = repository.findById(id).orElseThrow(
        () -> new MusicalInstrumentNotFoundException(id)
    );
    if (instrument.getQuantity() > 0) {
      instrument.setQuantity(instrument.getQuantity() - 1);
      repository.save(instrument);
    } else {
      throw new MusicalInstrumentNotFoundException("This instrument is out of stock");
    }

  }

  /**
   * Фильтрует музыкальные инструменты по цене, не превышающей заданное значение.
   *
   * @param maxPrice максимальная цена
   * @return список отфильтрованных музыкальных инструментов
   */
  public List<MusicalInstrument> filterByPrice(float maxPrice) {
    return repository.findByPriceLessThanEqual(maxPrice)
        .stream()
        .filter(musicalInstrument -> musicalInstrument.getQuantity() > 0)
        .collect(Collectors.toList());
  }


  /**
   * Заполняет базу данных примерами инструментов.
   */
  @Transactional
  public void fillExample() {
    repository.insertExample();
  }

  /**
   * Удаляет все музыкальные инструменты из базы данных.
   */
  @Transactional
  public void wipe() {
    repository.deleteAll();
  }
}
