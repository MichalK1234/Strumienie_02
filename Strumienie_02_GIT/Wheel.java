package Strumienie_02_GIT;

/**
 * Created by Michal on 2017-02-17.
 */
public class Wheel {

    private String manufacturer;
    private int size;
    private TiresType tiresType;


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TiresType getTiresType() {
        return tiresType;
    }

    public void setTiresType(TiresType tiresType) {
        this.tiresType = tiresType;
    }

    public Wheel() {

    }

    public Wheel(String manufacturer, int size, TiresType tiresType) {
        this.manufacturer = manufacturer;
        this.size = size;
        this.tiresType = tiresType;
    }

    @Override
    public String toString() {
        return manufacturer + " " + size + " " + tiresType;
    }
}
