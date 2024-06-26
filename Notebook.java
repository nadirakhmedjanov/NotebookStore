public class Notebook {
    private String brand;
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Notebook(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Notebook [brand=" + brand + ", ram=" + ram + "GB, hdd=" + hdd + "GB, os=" + os + ", color=" + color + "]";
    }
}
