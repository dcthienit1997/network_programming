## Đề bài:
Xây dựng hệ thống Client/Server cung cấp dịch vụ gửi nhận file qua mạng (tương tự giao thức FTP) với các yêu cầu sau:
    - *Client* tương tác với *Server* thông qua kết nối TCP, địa chỉ **127.0.0.1**, cổng **12345**;
    - *Client* tương tác với *Server* bằng dữ liệu nhị phân (sử dụng ==DataInputStream== và ==DataOutputStream==);
    - Người dùng tương tác với *Client* qua màn hình console;
    - Các lệnh có thể thực hiện nhiều lần cho tới khi người dùng nhập chuỗi `QUIT` yêu cầu kết thúc;
    - Người dùng có thể nhập các lệnh sau trên console:
    + `SET_SERVER_DIR directory`
    + `SET_SERVER_DIR directory`
    + `SEND source_file dest_file`
    + `GET source_file dest_file`
    + `QUIT`

### Mô tả chi tiết:
-   Mặc định dữ liệu (file khi server nhận hoặc khi server trả về cho *Client*) trên nằm trên thư mục C:\\dest.
Và dữ liệu trên *Client* nằm trong thư mục C:\\source.
-   `SET_SERVER_DIR directory` sử dụng khi người dùng muốn thay đổi thư mục mặc định trên *Server*. Ví dụ
`SET_SERVER_DIR d:\\abc`
-   `SET_CLIENT_DIR directory` sử dụng khi người dùng muốn thay đổi thư mục mặc định trên *Client*. Ví dụ
`SET_CLIENT_DIR d:\\xyz`
-   `SEND source_file dest_file` để gửi một file có nội dung bất kỳ từ *Client* với tên file cho trước (source_file)
nằm trong thư mục mặc định của *Client* đến *Server* và được ghi vòa thư mục mặc định của Server với tên file quy định
bởi dest_file.
-   `GET source_file dest_file` để lấy (nhận) một file có nội dung bất kỳ từ *Server* và được ghi vào thư mục mặc định
của *Client* với tên file quy định bởi dest_file.
-   `QUIT` kết thúc chương trình *Client*.
-   *Server* phải phục vụ được đồng thời nhiều *Client*.

