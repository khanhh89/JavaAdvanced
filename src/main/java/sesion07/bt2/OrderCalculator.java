package sesion07.bt2;

public class OrderCalculator {
    private DiscountStrategy discountStrategy;
    public OrderCalculator(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }
    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }
    public double calculate(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}
