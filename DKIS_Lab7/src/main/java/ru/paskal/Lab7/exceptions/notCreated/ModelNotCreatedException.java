package ru.paskal.Lab7.exceptions.notCreated;


public class ModelNotCreatedException extends RuntimeException {
  public ModelNotCreatedException(String entityType , String msg) {
    super(entityType + " not created because: " + msg);
  }

}
