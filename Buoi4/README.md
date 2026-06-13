# Buoi4 - Product Store dùng SQLite

## Cách chạy trên Windows

Mở PowerShell tại thư mục `Buoi4/src`, sau đó chạy:

```powershell
javac -cp ".;../lib/*" *.java
java -cp ".;../lib/*" ShopGUI
```

## Các file chính

- `Product.java`: lớp biểu diễn một sản phẩm.
- `ProductDAO.java`: truy vấn danh sách sản phẩm từ bảng `products`.
- `DatabaseConnection.java`: kết nối SQLite và khởi tạo CSDL nếu cần.
- `ShopGUI.java`: giao diện Java Swing.
- `ImageLabel.java`: JLabel có hiệu ứng đổi ảnh.
- `database.sql`: script tạo bảng `products` và thêm dữ liệu mẫu.
- `db/products.db`: file cơ sở dữ liệu SQLite.
