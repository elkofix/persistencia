package src.model;

public class Hoarding {
    private int width;
    private int height;
    private boolean inUse;
    private String brand;

    public Hoarding(int width, int height, boolean inUse, String brand) {
        this.width = width;
        this.height = height;
        this.inUse = inUse;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return width+"     "+height+"     "+inUse+"    "+brand;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
