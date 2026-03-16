package sesion07.bt2;

public class PercentageDiscount implements DiscountStrategy {
    private double percentage;
    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - (totalAmount * percentage/100);
    }
}
