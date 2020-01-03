## Đề bài:
Xây dựng hệ thống *Client/Server* của một ngân hàng cung cấp các dịch vụ cho khách hàng với các yêu cầu sau:
- *Client* tương tác với *Server* thông qua **giao thức TCP**, địa chỉ **127.0.0.1**, cổng **12345**;
- Người dùng tương tác với *Client* qua màn hình console;
- *Client* có thể thực hiện các thao tác thông qua các lệnh sau trên console:
   - `withdraw name amt` : Rút `amt` tiền từ tài khoản `name`
   - `deposit name amt` : Nạp `amt` vào tài khoản `name`
   - `getBalance name` : Kiểm tra số dư của tài khoản `name`
   - `transfer thisName amt thatName` : Chuyển `amt` từ tài khoản `thisNam` sang tài khoản `thatName`
   
*`amt`: amount: số tiền (double)*

### Yêu cầu:
- *Server* phải kết nối xuống CSDL Access
- Tạo một bảng dữ liệu ở Access với các thông tin như sau:

| NAME           | AMOUNT   |
| ---------------| :------: |
| MrTonyStark    | 150.0    |
| MrPeter        | 100.0    |
| MrsLoan        | 750.0    |
| MsPhuong       | 840.0    |