package sesion01.bt4;

import java.io.File;
import java.io.IOException;

public class UseFile {
    public static void savaFile(String data) throws IOException {
        throw new IOException("Lỗi ghi dữ liệu");
    }
    public static void processUserData(String userData) throws IOException {
        savaFile(userData);
    }

    public static void main(String[] args) {
        try{
            processUserData("Thong tin người dùng");
            System.out.println("Đang xử lý");
        }catch (IOException e){
            System.out.println("Xảy ra lỗi"+e.getMessage());
        }
    }

}
