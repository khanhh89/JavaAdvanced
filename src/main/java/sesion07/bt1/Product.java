package sesion07.bt1;

public class Product {
    private String proId;
    private String proName;
    private double proPrice;

    public Product() {
    }

    public Product(String proId, String proName, double proPrice) {
        this.proId = proId;
        this.proName = proName;
        this.proPrice = proPrice;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }

}