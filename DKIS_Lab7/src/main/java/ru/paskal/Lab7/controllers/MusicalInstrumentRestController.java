package ru.paskal.Lab7.controllers;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.paskal.Lab7.exceptions.notCreated.MusicalInstrumentNotCreatedException;
import ru.paskal.Lab7.exceptions.notDeleted.MusicalInstrumentNotDeletedException;
import ru.paskal.Lab7.exceptions.notFound.MusicalInstrumentNotFoundException;
import ru.paskal.Lab7.exceptions.notUpdated.MusicalInstrumentNotUpdatedException;
import ru.paskal.Lab7.models.MusicalInstrument;
import ru.paskal.Lab7.services.MusialInstrumentService;
import ru.paskal.Lab7.utils.CrudErrorHandlers;

@RestController
//@Profile("profileBack")
@RequestMapping("/instruments/api")
public class MusicalInstrumentRestController extends CrudErrorHandlers<
    MusicalInstrumentNotCreatedException,
    MusicalInstrumentNotFoundException,
    MusicalInstrumentNotUpdatedException,
    MusicalInstrumentNotDeletedException
    > {

  private final MusialInstrumentService service;

  @Autowired
  public MusicalInstrumentRestController(MusialInstrumentService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<MusicalInstrument>> getAll(
      @RequestParam(name = "price", required = false) Float price) {
    if (price != null) {
      return new ResponseEntity<>(service.filterByPrice(price), HttpStatus.OK);
    }
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MusicalInstrument> show(@PathVariable("id") int id) {
    return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<HttpStatus> create(
      @RequestBody @Valid MusicalInstrument musicalInstrument,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      throw new MusicalInstrumentNotCreatedException(getError(bindingResult));
    }
    service.save(musicalInstrument);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<HttpStatus> update(
      @RequestBody @Valid MusicalInstrument musicalInstrument,
      BindingResult bindingResult,
      @PathVariable("id") int id
  ) {
    if (bindingResult.hasErrors()) {
      throw new MusicalInstrumentNotUpdatedException(getError(bindingResult));
    }
    try {
      service.update(id, musicalInstrument);
    } catch (MusicalInstrumentNotFoundException e) {
      throw new MusicalInstrumentNotUpdatedException(e.getMessage());
    }
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
    try {
      service.delete(id);
    } catch (MusicalInstrumentNotFoundException e) {
      throw new MusicalInstrumentNotDeletedException(e.getMessage());
    }
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<HttpStatus> deleteAll() {
    service.wipe();
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PatchMapping
  public ResponseEntity<HttpStatus> fillExample() {
    service.fillExample();
    return ResponseEntity.ok(HttpStatus.OK);
  }

  private String getError(BindingResult bindingResult) {
    StringBuilder errorMsg = new StringBuilder();
    List<FieldError> errors = bindingResult.getFieldErrors();
    for (FieldError error:
        errors) {
      errorMsg
          .append(error.getField())
          .append(" - ")
          .append(error.getDefaultMessage())
          .append(";");
    }
    return errorMsg.toString();
  }

}
