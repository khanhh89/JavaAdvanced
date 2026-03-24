# Phân tích Thiết kế - Tối ưu hóa Dashboard Y tá trưởng (N+1 Query Problem)

## 1. Phân tích Input / Output
- **Input:** Không có (Truy xuất toàn bộ danh sách 500 bệnh nhân đang cấp cứu trong ngày).
- **Output:** `List<BenhNhanDTO>` (Mỗi bệnh nhân kèm theo `List<DichVu>`).
- **Nghiệp vụ cốt lõi:** Không bỏ sót bệnh nhân chưa có dịch vụ (Bẫy 2) và thời gian phản hồi < 1 giây (Bẫy 1).

## 2. Đề xuất 2 giải pháp truy vấn

### Giải pháp 1: Lazy Loading (Vòng lặp N+1 Query - Thường là nguyên nhân gây chậm)
- **Cách làm:** 
  1. Dùng lệnh `SELECT * FROM BenhNhan` để lấy danh sách 500 bệnh nhân.
  2. Dùng vòng lặp `for` duyệt qua 500 bệnh nhân này. 
  3. Ở mỗi vòng lặp, tiếp tục bắn thêm 1 câu lệnh SQL `SELECT * FROM DichVuSuDung WHERE maBenhNhan = ?` để lấy dịch vụ của người đó.
- **Tại sao nó tệ?** Nếu có 500 bệnh nhân, hệ thống phải thực hiện 1 + 500 = 501 câu truy vấn liên tục gửi qua mạng tới Database. Network I/O (Thời gian truyền tải gói tin) sẽ làm hệ thống "quay mòng mòng" 10-15s.

### Giải pháp 2: Eager Loading với LEFT JOIN và Gom nhóm bằng HashMap (Giải pháp Tối ưu)
- **Cách làm:** 
  1. Viết 1 câu lệnh SQL duy nhất: `SELECT b.maBenhNhan, b.ten, d.maDichVu, d.tenDichVu FROM BenhNhan b LEFT JOIN DichVuSuDung d ON b.maBenhNhan = d.maBenhNhan`. 
     - *Tại sao là LEFT JOIN?* Để bắt được cả những bệnh nhân chưa có dịch vụ nào (Bẫy 2). Nếu dùng INNER JOIN, các bệnh nhân trống dịch vụ sẽ bị mất tích.
  2. Dữ liệu trả về sẽ bị lặp dòng (Do 1 bệnh nhân có nhiều dịch vụ).
  3. Ở phía Java (RAM), ta dùng một vòng lặp `while(rs.next())` kết hợp với cấu trúc dữ liệu `LinkedHashMap<String, BenhNhanDTO>` để "gộp" các dịch vụ lại vào cùng một bệnh nhân dựa trên `maBenhNhan`.

## 3. So sánh & Lựa chọn

| Tiêu chí | Giải pháp 1 (N+1 Query) | Giải pháp 2 (LEFT JOIN + Map) |
| :--- | :--- | :--- |
| **Số lượng Query gửi tới DB** | 501 câu truy vấn. Rất lãng phí. | **Đúng 1 câu truy vấn duy nhất.** |
| **Băng thông mạng (Network I/O)** | Cực lớn (do độ trễ của 501 lần kết nối qua lại). | Rất thấp. DB trả về 1 cục kết quả lớn 1 lần duy nhất. |
| **Tải trên DB Server** | Rất nặng, phải mở nhiều luồng thực thi liên tục. | Rất nhẹ, Query Engine của DB được tối ưu cực tốt cho lệnh JOIN. |
| **Độ phức tạp xử lý trên RAM (Java)** | Thấp, dễ code. Cứ lặp và nhét vào List. | Cao hơn một chút (O(N) time complexity). Đòi hỏi tư duy dùng HashMap để gộp ID trùng lặp. |

**=> LỰA CHỌN TỐI ƯU CHỐT LẠI:** Giải pháp 2 (LEFT JOIN và Gom nhóm bằng HashMap). Giải pháp này xóa bỏ hoàn toàn hiện tượng chậm lag, xử lý cả nghìn bản ghi chỉ trong khoảng ~100ms.

## 4. Thiết kế Triển khai (Code Java)
**Các bước (Mã giả / Data Flow trên Java RAM):**
1. Khởi tạo `Map<String, BenhNhanDTO> map = new LinkedHashMap<>()` (Giữ nguyên thứ tự lấy ra từ DB).
2. Lặp qua từng dòng của ResultSet trả về từ LEFT JOIN.
3. Lấy `maBenhNhan` của dòng hiện tại. Kiểm tra xem nó đã có trong Map chưa:
   - Nếu chưa có: Tạo Object `BenhNhanDTO` mới (khởi tạo luôn cái `List<DichVu>` rỗng) và bỏ vào Map.
   - Lấy Object bệnh nhân đó ra khỏi Map.
4. Kiểm tra `maDichVu` của dòng hiện tại (Xử lý Bẫy 2 NullPointerException):
   - Nếu `maDichVu` != NULL (Nghĩa là bệnh nhân này có xài dịch vụ): Tạo Object `DichVu` và add vào `List<DichVu>` của bệnh nhân.
   - Nếu `maDichVu` == NULL (Bệnh nhân mới vào, LEFT JOIN sinh ra NULL): Bỏ qua, không add gì cả. Code không văng lỗi.
5. Trả về `new ArrayList<>(map.values())`.
