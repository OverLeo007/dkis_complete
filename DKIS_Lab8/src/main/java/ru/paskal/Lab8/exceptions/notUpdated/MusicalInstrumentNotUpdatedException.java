package ru.paskal.Lab8.exceptions.notUpdated;

public class MusicalInstrumentNotUpdatedException extends ModelNotUpdatedException {

    public static final String entityType = "MusicalInstrument";

    public MusicalInstrumentNotUpdatedException(String msg) {
        super(entityType, msg);
    }
}