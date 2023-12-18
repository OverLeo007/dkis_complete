package ru.paskal.Lab7.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.paskal.Lab7.models.MusicalInstrument;
import ru.paskal.Lab7.services.MusialInstrumentService;

/**
 * Контроллер для работы с музыкальными инструментами.
 */
@Controller
//@Profile("profileBack")
@RequestMapping("/instruments")
public class MusicalInstrumentController {

  private final MusialInstrumentService instrumentService;

  /**
   * Конструктор контроллера.
   *
   * @param instrumentService сервис музыкальных инструментов
   */
  @Autowired
  public MusicalInstrumentController(MusialInstrumentService instrumentService) {
    this.instrumentService = instrumentService;
  }

  /**
   * Обрабатывает запрос на получение списка инструментов.
   *
   * @param price  цена для фильтрации
   * @param model  объект модели
   * @return  представление списка инструментов
   */
  @GetMapping()
  public String index(@RequestParam(name = "price", required = false) Float price, Model model) {
    if (price != null) {
      model.addAttribute("instruments", instrumentService.filterByPrice(price));
    } else {
      model.addAttribute("instruments", instrumentService.findAll());
    }
    return "instruments/instrument_main";
  }

  /**
   * Обрабатывает запрос на получение информации об инструменте по его идентификатору.
   *
   * @param id  идентификатор инструмента
   * @param model  объект модели
   * @return  представление информации об инструменте
   */
  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("musical_instrument", instrumentService.findOne(id));
    return "instruments/show";
  }

  /**
   * Обрабатывает запрос на редактирование информации об инструменте.
   *
   * @param id  идентификатор инструмента
   * @param model  объект модели
   * @return  представление для редактирования
   */
  @GetMapping("/{id}/edit")
  public String edit(@PathVariable("id") int id, Model model) {
    model.addAttribute("musical_instrument", instrumentService.findOne(id));
    return "instruments/edit";
  }

  /**
   * Обрабатывает запрос на создание нового инструмента.
   *
   * @param instrument  объект музыкального инструмента
   * @return  представление для создания нового инструмента
   */
  @GetMapping("/new")
  public String newInstrument(@ModelAttribute("musical_instrument") MusicalInstrument instrument) {
    return "instruments/new";
  }

  /**
   * Обрабатывает запрос на сохранение нового инструмента.
   *
   * @param instrument  объект музыкального инструмента
   * @param bindingResult  результаты валидации
   * @return  представление для создания нового инструмента или перенаправление на список инструментов
   */
  @PostMapping()
  public String create(
      @ModelAttribute("musical_instrument") @Valid MusicalInstrument instrument,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return "instruments/new";
    }

    instrumentService.save(instrument);
    return "redirect:/instruments";
  }

  /**
   * Обрабатывает запрос на обновление информации об инструменте.
   *
   * @param instrument  объект музыкального инструмента
   * @param bindingResult  результаты валидации
   * @param id  идентификатор инструмента
   * @return  представление для редактирования или перенаправление на список инструментов
   */
  @PatchMapping("/{id}")
  public String update(
      @ModelAttribute("musical_instrument") @Valid MusicalInstrument instrument,
      BindingResult bindingResult,
      @PathVariable("id") int id
  ) {
    if (bindingResult.hasErrors()) {
      return "instruments/edit";
    }
    instrumentService.update(id, instrument);
    return "redirect:/instruments";
  }

  /**
   * Обрабатывает запрос на удаление инструмента.
   *
   * @param id  идентификатор инструмента
   * @return  перенаправление на список инструментов
   */
  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    instrumentService.delete(id);
    return "redirect:/instruments";
  }

  /**
   * Обрабатывает запрос на удаление всех инструментов.
   *
   * @return  перенаправление на список инструментов
   */
  @DeleteMapping()
  public String deleteAll() {
    instrumentService.wipe();
    return "redirect:/instruments";
  }

  /**
   * Обрабатывает запрос на заполнение примера инструментов.
   *
   * @return  перенаправление на список инструментов
   */
  @PatchMapping()
  public String fillExample() {
    instrumentService.fillExample();
    return "redirect:/instruments";
  }
}
