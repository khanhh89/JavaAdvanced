package sesion01.bt5;

public class user {
    private String name;
    private int age;

    public user(String name) {
        this.name = name;
    }
    public void setAge(int age) throws  IllegalArgumentException {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException();
        }
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
}
