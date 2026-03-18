package sesion09.miniPrj;

public interface TrafficLightState {
    TrafficLightState next();
    String color();
}