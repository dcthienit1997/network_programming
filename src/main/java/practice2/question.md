## Đề bài:
**Sử dụng RMI**, xây dựng hệ thống **Client/Server** cung cấp dịch vụ gửi nhận file ... với các yêu cầu sau:
- Client tương tác với Server thông qua **RMI**, địa chỉ 127.0.0.1, cổng 12345;
- Người dùng tương tác với Client qua màn hình console;
- Sau khi lookup thành công tới dịch vụ của Server, Client sẽ lấy thông tin ban đầu là "*Welcome to NLU File Service...*" từ Server và hiển thị lên màn hình;
- Trước khi có thao tác chính, người dùng phải đăng nhập vào hệ thống. Quá trình tương tự như giao thức POP3. Để đăng nhập, người dùng nhập lệnh:
   - **TEN tên_người_dùng**
- Server sẽ cho biết tên người dùng có được chấp nhận hay không (có tồn tại trong hệ thống). Nếu OK, người dùng sẽ nhạp tiếp mật khẩu:
   - **MAT KHAU mật_khẩu**
- Nếu nhập không đúng dữ liệu có thể nhập lại hoặc kết thúc bằng lệnh `QUIT`.
- Sau khi Client đăng nhập thành công, Client có thể thực hiện các thao tác thông qua các lệnh sau trên console:
   - **SEND** *source_file* *dest_file*
   - **GET** *source_file* *dest_file*
   - **QUIT**

### Mô tả chi tiết
- Dùng ArrayList lưu trữ thông tin đăng nhập (user_name, password) - sinh viên ...
- `SEND source_file dest_file` để gửi một file có nội dung bất kỳ từ Client tới Server (bao gồm cả đường dẫn);
- `GET source_file dest_file` để lấy (nhận) một file có nội dung bất kỳ từ Server về Client với tên file đầy đủ cho trước (bao gồm cả đường dẫn).
- `QUIT` kết thúc chương trình Client;
- Server phải đồng thời phục vụ nhiều Client (giải quyết vấn đề tranh chấp tài nguyên);
- Xử lý 2 ngoại lệ: File nguồn không mở được (ví dụ không tồn tại) và không tạo ... .
