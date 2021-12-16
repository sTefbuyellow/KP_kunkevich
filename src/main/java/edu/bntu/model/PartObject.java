package edu.bntu.model;

public class PartObject {

    private Integer ID;
    private String name;
    private String series;
    private String manufacturer;
    private Integer forceH;

    public PartObject(Integer ID, String name, String series, String manufacturer, Integer forceH) {
        this.ID = ID;
        this.name = name;
        this.series = series;
        this.manufacturer = manufacturer;
        this.forceH = forceH;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getForceH() {
        return forceH;
    }

    public void setForceH(Integer forceH) {
        this.forceH = forceH;
    }
}
