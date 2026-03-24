# Phân tích & Sửa lỗi: Module Thanh toán viện phí bằng Ví nội bộ

## Phần 1 - Phân tích
1. **Tại sao việc chỉ dùng `System.out.println()` trong khối `catch` là vi phạm nguyên tắc của Transaction?**
   - Khi xảy ra lỗi (ví dụ câu truy vấn thứ 2 bị sai cú pháp), khối `try` bị ngắt ngang và nhảy vào `catch`. Lúc này, lệnh `commit()` ở cuối khối `try` không được gọi. 
   - Nếu chỉ in ra lỗi bằng `System.out.println()` mà không làm gì thêm, kết nối DB (Connection) đó vẫn đang nằm trong trạng thái mở giao dịch (Transaction chưa được đóng).
   - Hậu quả: Dữ liệu bị thay đổi ở bước 1 (trừ tiền) sẽ bị "treo" lơ lửng (Lock). Các giao dịch hay tiến trình khác muốn truy cập vào dòng dữ liệu đó sẽ bị chặn lại (Deadlock) gây treo hệ thống. Ngoài ra, nếu kết nối này được trả về Connection Pool để dùng lại, giao dịch dở dang có thể bị commit nhầm ở một tính năng khác.

2. **Hành động thiết yếu nào đã bị lập quên?**
   - Lập trình viên đã quên lệnh `con.rollback()` trong khối `catch` để hủy bỏ hoàn toàn các thay đổi và đóng giao dịch an toàn. 
   - Đồng thời, cần phải khôi phục lại trạng thái `con.setAutoCommit(true)` trong khối `finally` trước khi đóng kết nối.

## Phần 2 - Thực thi (Mã nguồn đã sửa lỗi)
Bạn có thể xem mã nguồn hoàn chỉnh với chuẩn Try-Catch-Rollback tại file `PaymentService.java` trong cùng thư mục này.