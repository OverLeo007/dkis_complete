package ru.paskal.Lab8.exceptions.notUpdated;

public class ModelNotUpdatedException extends RuntimeException {
  public ModelNotUpdatedException(String entityType , String msg) {
    super(entityType + " not updated because: " + msg);
  }

}
