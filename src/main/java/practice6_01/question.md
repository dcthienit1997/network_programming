## Đề bài:
Xây dựng server cung cấp dịch vụ ngân hàng qua mạng e-Banking với các yêu cầu sau:
 + Client tương tác với Server qua TCP, địa chỉ 127.0.0.1, cổng 12345;
 + Server sử dụng dữ liệu chung là danh sách các người dùng (sử dụng ArrayList). Danh sách này là duy nhất trong toàn bộ chương trình và được chia sẻ cho tất cả các Client;
 + Server cung cấp các dịch vụ chính: đăng nhập, gửi tiền, lấy tiền và chuyển khoản;
 
##### 2a
Hãy thiết kế lớp User với các thuộc tính Tên, Mật khẩu, Số tài khoản kiểu String và Số tiền kiểu Double. Hãy thiết kế lớp Dao chứa dữ liệu nói trên (danh sách người dùng) với các phương thức cần thiết để thao tác với dữ liệu, đồng thời hãy đảm bảo dữ liệu trên là duy nhất trong toàn bộ hệ thống.

Hãy tạo tập tin (text file) user.txt trong thư mục Project chứa dữ liệu ban đầu như sau:

Tên       | Mật khẩu     | Số tài khoản    |Số tiền
----------|:------------:|:---------------:|:--------:
Pvtinh    |pvtinhnlu     |kcntt111         |100
Lphung    |lphungnlu     |kcntt222         |150
Nvan      |nvannlu       |kcntt000         |200

------------------
*Chú thích:* dữ liệu cho mỗi người dùng là một dòng. Các trường ngăn cách bởi dấu tab `\t`.

Hãy hiện thực phương thức `public void napDuLieuBanDau()` trong lớp `Dao` để tạo danh sách người dùng từ file dữ liệu nói trên.

##### 2b
Xây dựng các lớp cần thiết cho Server để phục vụ cho Client. Client dùng telnet. Hệ thống hoạt động theo các bước sau:
 + Sau khi kết nối thành công với Server, Client sẽ nhận được dòng "Welcome to NLU e-Bank..." từ Server và hiển thị trên màn hình.
 + Trước khi có các thao tác tài chính, người dùng phải đăng nhập vào hệ thống. Quá trình đăng nhập tương tự như giao thức POP3. Để đăng nhập, người dùng nhập lệnh:
 `TEN ten_nguoi_dung`;
 + Server sẽ cho biết tên người dùng có được chấp nhận hay không (có tồn tại trong CSDL hay không). Nếu OK, người dùng sẽ nhập tiếp mật khẩu:
 `MATKHAU mat_khau`;
 + Sau khi Client đăng nhập thành công, Client có thể thực hiện các thao tác:
   + Gửi tiền:      `GUI so_tien`;
   + Lấy tiền:      `LAY so_tien`;
   + Chuyển khoản:  `CHUYEN so_tai_khoan_chuyen_den so_tien`;
   + Các giao dịch có thể thực hiện nhiều lần cho tới khi người dùng nhập chuỗi `QUIT` yêu cầu kết thúc.

*Chú thích:* khi nhập liệu, các trường ngăn cách bởi dấu tab `\t`.

Hãy sử dụng telnet làm client để test chương trình.
