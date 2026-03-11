package sesion04.bt2;
public class UserService {
    public boolean checkRegistrationAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuoi khong the la so am");
        }
        return age >= 18;
    }
}