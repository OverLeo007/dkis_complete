package ru.paskal.Lab8.exceptions.notDeleted;

public class MusicalInstrumentNotDeletedException extends ModelNotDeletedException {

    public static final String entityType = "MusicalInstrument";

    public MusicalInstrumentNotDeletedException(String msg) {
        super(entityType, msg);
    }
}