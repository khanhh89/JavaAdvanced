# Báo cáo phân tích: Giao dịch "All or Nothing" - Xử lý xuất viện và thanh toán

## 1. Phân tích bài toán (I/O)
- **Input:** 
  - `maBenhNhan` (kiểu `int`): Mã định danh duy nhất của bệnh nhân trong cơ sở dữ liệu.
  - `tienVienPhi` (kiểu `double`): Số tiền viện phí cần thanh toán để xuất viện.
- **Output:** 
  - `void`: Giao dịch diễn ra âm thầm, nếu hoàn thành 3 bước sẽ không văng lỗi và in ra thông báo thành công.
  - Nếu thất bại, hàm sẽ ném ra các ngoại lệ (`Exception`) kèm thông báo chi tiết (Ví dụ: Thiếu tiền, Không tìm thấy bệnh nhân, Không tìm thấy giường...). Mọi thao tác dở dang trong Database sẽ bị loại bỏ hoàn toàn (Rollback).

## 2. Đề xuất giải pháp
Sử dụng nguyên lý **ACID** trong cơ sở dữ liệu thông qua cơ chế quản lý **Transaction** của Java JDBC:
- Sử dụng `connection.setAutoCommit(false)`: Ngăn chặn JDBC tự động lưu dữ liệu ngay lập tức sau mỗi câu lệnh.
- Bao bọc toàn bộ quá trình Truy vấn (SELECT) và Cập nhật (UPDATE) bên trong một khối `try-catch`.
- **Cách xử lý Bẫy 1 (Thiếu tiền):** Thực hiện 1 lệnh `SELECT` để kiểm tra trước số dư hiện tại của bệnh nhân. Nếu `số dư < viện phí`, lập tức ném ra lỗi `IllegalArgumentException`. Điều này khiến tiến trình chui vào `catch` và kích hoạt lệnh `rollback()`.
- **Cách xử lý Bẫy 2 (Dữ liệu ảo):** Tại từng lệnh `UPDATE` (trừ tiền, đổi trạng thái giường, đổi trạng thái BN), ta gán kết quả vào biến `int rowsAffected = executeUpdate();`. Nếu kết quả này bằng `0` (nghĩa là ID hoặc mã giường không tồn tại), hệ thống sẽ không sinh lỗi nội bộ nhưng ta phải chủ động dùng lệnh `if(rowsAffected == 0)` để ném ra `SQLException`. Tương tự, điều này sẽ kích hoạt `rollback()`.
- Chỉ gọi `connection.commit()` khi chạy thành công trót lọt đến cuối khối `try`.

## 3. Thiết kế các bước xử lý (Flow)
1. Mở kết nối Database (`Connection`).
2. Tắt Auto-Commit: `conn.setAutoCommit(false)`.
3. Lệnh SELECT: Khởi tạo `PreparedStatement` truy vấn lấy `so_du_tam_ung` và `ma_giuong`.
   - Nếu ResultSet rỗng -> Ném Exception (Bệnh nhân không tồn tại).
   - **[Bẫy 1]** Nếu `so_du_tam_ung < tienVienPhi` -> Ném Exception (Thiếu tiền).
4. Lệnh UPDATE 1: Cập nhật trừ tiền vào số dư. 
   - **[Bẫy 2]** Kiểm tra `rowsAffected == 0` -> Ném Exception.
5. Lệnh UPDATE 2: Giải phóng giường (Update trạng thái 'Trống').
   - **[Bẫy 2]** Kiểm tra `rowsAffected == 0` -> Ném Exception (Mã giường ảo).
6. Lệnh UPDATE 3: Cập nhật trạng thái bệnh nhân thành 'Đã xuất viện'.
   - **[Bẫy 2]** Kiểm tra `rowsAffected == 0` -> Ném Exception.
7. Chạy thành công toàn bộ: Gọi `conn.commit()`.
8. Tại khối `catch (Exception e)`: Kích hoạt `conn.rollback()` để đảm bảo tính "Nothing" nếu có 1 bước lỗi.
9. Tại khối `finally`: Luôn chạy `conn.setAutoCommit(true)` để hoàn trả thiết lập gốc, và gọi `conn.close()` để giải phóng kết nối mạng.