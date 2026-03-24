# Phân tích & Sửa lỗi: Kê đơn thuốc đặc trị (Transaction)

## Phần 1 - Phân tích Logic
Dựa vào nguyên lý hoạt động của Transaction và chế độ Auto-Commit mặc định của JDBC:
1. **Auto-Commit mặc định:** Khi tạo một `Connection` trong JDBC, chế độ `Auto-Commit` mặc định được bật (`true`). Điều này có nghĩa là mỗi khi một câu lệnh SQL (`executeUpdate`, `executeQuery`) được chạy, JDBC sẽ lập tức bọc nó thành một Transaction độc lập và gửi lệnh `COMMIT` xuống Database ngay tắp lự.
2. **Nguyên nhân gây lỗi thất thoát:** Trong đoạn code của bạn Junior, do chưa tắt Auto-Commit, nên khi chạy lệnh `executeUpdate()` thứ nhất (Trừ thuốc trong kho), Database lập tức ghi nhận (Commit) sự thay đổi này xuống ổ cứng. Sau đó, khi chuẩn bị chạy đến lệnh thứ 2 thì xảy ra sự cố (đứt mạng, lỗi cú pháp SQL, v.v.), làm phát sinh Exception. Tuy đoạn code dừng lại và nhảy vào `catch`, nhưng lệnh thứ 1 đã "ván đóng thuyền" không thể rút lại được nữa. 
3. **Kết luận:** Lỗi này vi phạm tính **Atomicity (Tính Nguyên tử)** của Transaction. Để 2 thao tác thành công cùng nhau hoặc thất bại cùng nhau, chúng ta phải nhóm chúng vào cùng một phiên giao dịch bằng cách tắt Auto-Commit.

## Phần 2 - Thực thi (Mã nguồn)
Xem chi tiết cách xử lý `setAutoCommit(false)`, `commit()` và `rollback()` an toàn trong file `PrescriptionService.java`.