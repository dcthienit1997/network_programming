## Đề bài:

#### Câu 1: *(4 điểm)*
Viết chương trình lưu kết quả xổ số của các tỉnh thành xuống file như sau. Người dùng sẽ nhập dữ liệu từ bàn phím với các thông tin: tỉnh thành, giải, số trúng.
Ví dụ:

    TIENGIANG
    Giải đặc biệt: 49435
    Giải nhất: 37998
    Giải nhì: 64385 - 97726
    Giải ba: 44013 - 92535 - 56951 - 50362 - 01231 - 82573
    Giải tư: 2650 - 8893 - 6053 - 9491
    Giải năm: 6359 - 1534 - 9335 - 5444 - 4794 - 2963
    Giải sáu: 302 - 944 - 177
    Giải bảy: 25 - 83 - 72 - 29
    
Hãy viết phương thức lấy kết quả xổ số của một tỉnh thành cho trước từ file vừa lưu xuống.

<hr>

#### Câu 2: *(6 điểm)*
Áp dụng phương thức UDP ứng dụng hoạt động chương trình lấy kết quả xố số theo mô hình client-server như sau:

+ Server hoạt động ở cổng 2000, địa chỉ localhost, kết quả xổ số nằm trên Server (trong file vừa tạo ở câu 1);
+ Client tương tác với Server theo cú pháp: `SOXO <tentinhthanh>` (ví dụ: `SOXO TIENGIANG`), server có nhiệm vụ phải gửi trả kết quả xổ số của tỉnh thành tương ứng. Trong trường hợp không có tỉnh thành mà người dùng yêu cầu thì sẽ thông báo không tìm thấy.

Viết chương trình để test các phương thức trên.

<hr>

*Ghi chú:*
Sinh viên lưu bài với tên project là họ tên_mã số sinh viên vào thư mục do giám thị chỉ định.
