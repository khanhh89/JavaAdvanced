package sesion01.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        Date birday;
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhập ngày sinh của bạn: ");
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        try{
            birday = sdf.parse(sc.nextLine());
        } catch (ParseException e){
            e.printStackTrace();
            System.out.println("");
        }
    }
}
