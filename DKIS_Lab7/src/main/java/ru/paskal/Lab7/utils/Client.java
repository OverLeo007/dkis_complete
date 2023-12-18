package ru.paskal.Lab7.utils;

import static ru.paskal.Lab7.utils.TestLogger.log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.paskal.Lab7.models.MusicalInstrument;
import ru.paskal.Lab7.services.ClientService;

@Component
public class Client implements CommandLineRunner {

  private final ClientService clientService;
  List<Integer> ids = new ArrayList<>();
  List<Float> prices = new ArrayList<>();


  @Autowired
  public Client(ClientService clientService) {
    this.clientService = clientService;
  }

  public void showAll() {
    Mono<List<MusicalInstrument>> instrumentsMono = clientService.getAll();
    instrumentsMono
        .doOnSuccess(instruments -> log("Все записи:\n" + instruments.stream()
            .map(instrument -> {
              ids.add(instrument.getId());
              prices.add(instrument.getPrice());
              return instrument.toString();
            })
            .collect(Collectors.joining("\n")), "Client"))
        .subscribeOn(Schedulers.boundedElastic())
        .block();
  }

  public void showOne(int id) {
    Mono<MusicalInstrument> instrumentMono = clientService.getById(id);
    instrumentMono
        .doOnSuccess(musicalInstrument -> log("Запись с id = " + id + ": " + musicalInstrument, "Client"))
        .subscribeOn(Schedulers.boundedElastic())
        .block();
  }

  public void showFilteredByPrice(Float price) {
    Mono<List<MusicalInstrument>> instrumentsMono = clientService.getFilteredByPrice(price);
    instrumentsMono
        .doOnSuccess(instruments -> log("Записи с ценой меньше или равной " + price + " :\n" + instruments.stream()
            .map(Object::toString)
            .collect(Collectors.joining("\n")), "Client"))
        .subscribeOn(Schedulers.boundedElastic())
        .block();
  }

  public void addInstrument() {
    MusicalInstrument instrument = new MusicalInstrument(
        "testName1",
        "testType1",
        "testBrand1",
        1000f,
        1
    );
    log("Добавляем инструмент: " + instrument, "Client");
    clientService.create(instrument);
  }

  public void updateInstrument(int id) {
    MusicalInstrument instrument = new MusicalInstrument(
        "testName2",
        "testType2",
        "testBrand2",
        5000f,
        100
    );
    log(String.format("Обновляем инструмент с id = %d на ", id) + instrument, "Client");
    clientService.update(id, instrument);
  }

  public void deleteInstrument(int id) {
    log(String.format("Удаляем инструмент с id = %d", id), "Client");
    clientService.delete(id);
  }

  public void wipeInstruments() {
    log("Удаляем все записи из таблицы", "Client");
    clientService.wipeAll();
  }

  public void fillByExample() {
    log("Вставляем тестовые данные", "Client");
    clientService.fillExample();
  }


  @Override
  public void run(String... args) {
    String allowRun = System.getProperty("allow.run");

    if (!(allowRun == null || "true".equals(allowRun))) {
      return;
    }
    log("============== REST CLIENT STARTED ==============", "Client");

    fillByExample();

    log("=======================================================================");
    log("Вывод всех записей:", "Client");
    showAll();
    log("=======================================================================");

    log("Вывод одной записи:", "Client");
    if (!ids.isEmpty()) {
      showOne(ids.get(0));
    } else {
      log("Нет записей в базе", "Client");
    }
    log("=======================================================================");

    log("Вывод записей отфильтрованных по макс. цене:", "Client");
    if (!ids.isEmpty()) {
      showFilteredByPrice(
          ((float) prices.stream().mapToDouble(Float::doubleValue).average().orElse(1000f)));
    } else {
      log("Нет записей в базе", "Client");
    }
    log("=======================================================================");

    log("Добавление записи:", "Client");
    addInstrument();
    showAll();
    log("=======================================================================");

    log("Редактирование записи:", "Client");
    if (!ids.isEmpty()) {
      updateInstrument(ids.get(0));
    } else {
      log("Нет записей в базе", "Client");
    }
    showAll();
    log("=======================================================================");

    log("Удаление записи:", "Client");
    if (!ids.isEmpty()) {
      deleteInstrument(ids.get(0));
    } else {
      log("Нет записей в базе", "Client");
    }
    showAll();
    log("=======================================================================");

    log("Удаление всех записей:", "Client");
    wipeInstruments();
    showAll();

    log("Приложение работает в штатном режиме, можно совершать запросы извне :)");
  }


}
