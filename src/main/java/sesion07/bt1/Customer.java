package sesion07.bt1;

public class Customer {
    private String cusName;
    private String email;
    private String address;

    public Customer() {
    }

    public Customer(String cusName, String email, String address) {
        this.cusName = cusName;
        this.email = email;
        this.address = address;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}