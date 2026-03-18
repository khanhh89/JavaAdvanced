package sesion09.miniPrj;

public class Yellow implements TrafficLightState{
    public TrafficLightState next() { return new Red(); }
    public String color() { return "YELLOW"; }
}