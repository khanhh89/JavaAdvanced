package sesion08.bt5;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}
