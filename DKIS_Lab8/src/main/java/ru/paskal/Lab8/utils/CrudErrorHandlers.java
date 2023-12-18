package ru.paskal.Lab8.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.paskal.Lab8.exceptions.notCreated.ModelNotCreatedException;
import ru.paskal.Lab8.exceptions.notDeleted.ModelNotDeletedException;
import ru.paskal.Lab8.exceptions.notFound.ModelNotFoundException;
import ru.paskal.Lab8.exceptions.notUpdated.ModelNotUpdatedException;
import ru.paskal.Lab8.exceptions.responses.ErrorResponse;

//@Controller
public class CrudErrorHandlers
    <
    C extends ModelNotCreatedException,
    R extends ModelNotFoundException,
    U extends ModelNotUpdatedException,
    D extends ModelNotDeletedException
        > {

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handleCreateException(C e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handleReadException(R e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handleUpdateException(U e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handleDeleteException(D e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
  }

}
