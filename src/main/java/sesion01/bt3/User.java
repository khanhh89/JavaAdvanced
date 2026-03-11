package sesion01.bt3;

public class User {
    private int age;

    public User(String khanhdao) {
    }
    public void setAge(int age) {
        if(age<0){
            throw  new IllegalArgumentException("Tuổi không được âm!!");
        }
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public static void main(String[] args) {
        User user = new User("Khanhdao");
        user.setAge(10);
        System.out.println("Tuổi hợp lệ: "+user.getAge());
        //không hợp lệ
        try{
            user.setAge(-5);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
