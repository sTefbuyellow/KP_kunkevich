package edu.bntu.model;

public class Geometry {

    private final Integer ID;
    private final Integer objectID;
    private final Integer height;
    private final Integer width;
    private final Integer length;
    private final Integer weight;

    public Geometry(Integer ID, Integer objectID, Integer height, Integer width, Integer length, Integer weight) {
        this.ID = ID;
        this.objectID = objectID;
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getObjectID() {
        return objectID;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getWeight() {
        return weight;
    }
}
