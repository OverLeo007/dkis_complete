package ru.paskal.Lab7.exceptions.notCreated;

public class MusicalInstrumentNotCreatedException extends ModelNotCreatedException {

    public static final String entityType = "MusicalInstrument";

    public MusicalInstrumentNotCreatedException(String msg) {
        super(entityType, msg);
    }
}
