package edu.bntu.model;

public class Parameter {

    private final Integer ID;
    private final Integer objectID;
    private final String name;
    private final String value;

    public Parameter(Integer ID, Integer objectID, String name, String value) {
        this.ID = ID;
        this.objectID = objectID;
        this.name = name;
        this.value = value;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getObjectID() {
        return objectID;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
