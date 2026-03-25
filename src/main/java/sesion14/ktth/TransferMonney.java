package sesion14.ktth;

import java.sql.*;

public class TransferMonney {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sesion14";
        String user = "root";
        String password = "123456";
        String fromAcc = "Acc01";
        String toAcc = "Acc02";
        double amount = 1000;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            String checkSql = "select Balance from Accounts where AccountId = ?";
            PreparedStatement ps = conn.prepareStatement(checkSql);
            ps.setString(1, fromAcc);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Tai khoan gui khong hop le");
            }
            double balance = rs.getDouble("Balance");
            if (balance < amount) {
                throw new Exception("Tai khoan khong du tien");
            }
            CallableStatement cs1 = conn.prepareCall("{call sp_UpdateBalance(?,?)}");
            cs1.setString(1, fromAcc);
            cs1.setDouble(2, -amount);
            cs1.executeUpdate();
            CallableStatement cs2 = conn.prepareCall("{call sp_UpdateBalance(?,?)}");
            cs2.setString(1, toAcc);
            cs2.setDouble(2, amount);
            cs2.executeUpdate();
            conn.commit();
            System.out.println("Chuyen tien thanh cong roi nhe");
            String resultSql = "select * from Accounts where AccountId in (?,?)";
            ps = conn.prepareStatement(resultSql);
            ps.setString(1, fromAcc);
            ps.setString(2, toAcc);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("AccountId") + " " + rs.getDouble("Balance"));
            }
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
