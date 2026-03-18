package sesion09.ktth;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();
        while (true) {
            System.out.println("Quan ly san pham");
            System.out.println("1. Them san pham");
            System.out.println("2. Xem danh sach");
            System.out.println("3. Cap nhat");
            System.out.println("4. Xoa");
            System.out.println("5. Thoat");
            System.out.print("Nhap lua chon: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("1. Digital");
                    System.out.println("2. Physical");
                    int type = Integer.parseInt(sc.nextLine());
                    Product product = ProductFactory.createProduct(type, sc);
                    db.addProduct(product);
                    break;
                case 2:
                    for (Product p : db.getAllProducts()) {
                        p.displayInfor();
                    }
                    break;
                case 3:
                    System.out.println("Nhap ID can sua: ");
                    String idUpdate = sc.nextLine();
                    Product productUpdate = db.findById(idUpdate);

                    if (productUpdate != null) {
                        System.out.println("Nhap ten moi: ");
                        productUpdate.setName(sc.nextLine());
                        System.out.println("Nhap gia moi: ");
                        productUpdate.setPrice(Double.parseDouble(sc.nextLine()));
                        System.out.println("Cap nhat thanh cong");
                    } else {
                        System.out.println("Khong tim thay san pham co id: " + idUpdate);
                    }
                    break;
                case 4:
                    System.out.println("Nhap ID can xoa: ");
                    String idDelete = sc.nextLine();
                    Product productDelete = db.findById(idDelete);
                    if (productDelete == null) {
                        System.out.println("Khong tim thay san pham co id: " + idDelete);
                        break;
                    }
                    db.deleteProduct(idDelete);
                    break;
                case 5:
                    System.out.println("Thoat chuong trinh");
                    System.exit(0);
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        }
    }
}
//commit sesion09