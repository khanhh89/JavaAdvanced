package sesion07.bt2;

public class Main {
    public static void main(String[] args) {
        double amount = 1000;
        OrderCalculator calc = new OrderCalculator(new PercentageDiscount(amount));
        System.out.println("10%: "+calc.calculate(amount));
        calc = new OrderCalculator(new PercentageDiscount(50000));
        System.out.println("50000: "+calc.calculate(amount));
        calc = new OrderCalculator(new NoDiscount());
        System.out.println("No discount: "+calc.calculate(amount));
        calc = new OrderCalculator(new HolidayDiscount());
        System.out.println("Holiday discount: "+calc.calculate(amount));
    }
}
