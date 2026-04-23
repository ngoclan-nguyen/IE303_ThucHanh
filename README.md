# Thực hành môn IE303 🚀

Repository này dùng để lưu trữ toàn bộ mã nguồn các bài tập thực hành của môn **IE303**.

## 👤 Thông tin sinh viên
- **Họ và tên:** Nguyễn Ngọc Lan
- **MSSV:** 23520842
- **Lớp:** IE303.Q22.1
  
## LAB 1 

### Câu 1: Tính diện tích hình tròn tâm O(0, 0) bán kính r không dùng hằng số
* **Ý tưởng:** Sử dụng phương pháp xác suất Monte Carlo. Chọn ngẫu nhiên một số lượng lớn điểm nằm trong một hình vuông bao quanh đường tròn (hình vuông có cạnh 2r). Tỉ lệ số điểm rơi vào bên trong đường tròn trên tổng số điểm đã ném ra sẽ xấp xỉ với tỉ lệ giữa diện tích hình tròn và diện tích hình vuông.
* **Cách thực hiện:** Cho vòng lặp chạy 1.000.000 lần. Mỗi lần sinh ngẫu nhiên toạ độ x và y trong khoảng [-r, r]. Kiểm tra điều kiện điểm nằm trong đường tròn bằng định lý Pytago (tổng bình phương tọa độ x và y nhỏ hơn hoặc bằng bình phương bán kính r). Diện tích hình tròn xấp xỉ được tính bằng công thức: Lấy số điểm trong vòng tròn chia cho tổng số điểm, sau đó nhân với diện tích hình vuông (4 * r * r).

### Câu 2: Xấp xỉ giá trị của Pi thông qua đường tròn đơn vị
* **Ý tưởng:** Áp dụng phương pháp xác suất Monte Carlo tương tự câu 1 nhưng cụ thể cho đường tròn đơn vị có bán kính r=1. Diện tích hình tròn lúc này chính là số Pi, và diện tích hình vuông bao quanh là 4. Giá trị Pi sẽ xấp xỉ bằng 4 nhân với tỉ lệ điểm rơi vào vòng tròn.
* **Cách thực hiện:** Tạo ngẫu nhiên tọa độ x, y trong khoảng [-1, 1]. Đếm số lượng điểm thỏa mãn điều kiện nằm trong đường tròn. Lấy số điểm này chia cho tổng số điểm đã sinh ra để tính tỉ lệ xác suất, sau đó nhân tỉ lệ này với 4 để ra giá trị xấp xỉ của hằng số Pi.

### Câu 3: Xác định các trạm cảnh báo trong khu bảo tồn
* **Ý tưởng:** Bài toán được mô hình hóa thành việc tìm Bao lồi (Convex Hull) trong hình học tính toán. Mục tiêu là tìm một tập hợp các trạm phát sóng nằm ở vị trí ngoài cùng sao cho chúng tạo thành một đa giác lồi bao bọc toàn bộ các trạm bên trong.
* **Cách thực hiện:** Áp dụng thuật toán Monotone Chain. Đầu tiên, loại bỏ các điểm trùng lặp và sắp xếp các trạm theo toạ độ x tăng dần (nếu trùng x thì ưu tiên xét theo y). Tiếp theo, duyệt qua danh sách để xây dựng nửa bao lồi trên và nửa bao lồi dưới bằng cách tính Tích có hướng, qua đó loại bỏ các điểm tạo thành góc rẽ trái (góc lõm). Cuối cùng, ghép hai nửa đồ thị lại để trích xuất danh sách tọa độ các trạm cảnh báo.

### Câu 4: Tìm dãy con dài nhất có tổng bằng k
* **Ý tưởng:** Sử dụng thuật toán Quay lui (Backtracking) kết hợp vét cạn để duyệt qua các tổ hợp dãy con có thể có, nhằm tìm ra dãy có chiều dài lớn nhất thỏa mãn điều kiện tổng các phần tử bằng chính xác k.
* **Cách thực hiện:** Xây dựng hàm đệ quy với tham số là tổng mục tiêu còn lại (currentSum). Tại mỗi bước, thử đưa một phần tử vào dãy con và trừ currentSum đi giá trị phần tử đó. Nếu currentSum giảm về đúng 0, tiến hành đối chiếu và lưu lại dãy con nếu nó dài hơn dãy con tốt nhất hiện tại. Sau đó thực hiện thao tác quay lui (xóa phần tử vừa thêm ra khỏi mảng tạm) để tiếp tục duyệt các nhánh số học khác nhằm tìm ra kết quả tối ưu nhất.


## LAB 2 

* **Ý tưởng chung:** Ứng dụng tư duy Lập trình Hướng đối tượng (OOP), sử dụng Java Swing và bộ công cụ java.awt để vẽ đồ hoạ và xử lý các sự kiện trong một vòng lặp game (Game Loop).

### Câu 1: Xây dựng cửa sổ Flappy Bird và đặt ảnh nền
* **Ý tưởng:** Tạo một không gian chơi có kích thước cố định, đảm bảo hệ tọa độ khi vẽ vật thể và xét tính va chạm không bị xô lệch nếu người dùng cố tình thay đổi kích cỡ cửa sổ.
* **Cách thực hiện:** Tạo class chính kế thừa từ JPanel và đặt trong một JFrame có kích thước cố định là 360x640. Khóa tính năng thay đổi kích thước bằng hàm setResizable(false). Tiến hành tải các tài nguyên hình ảnh (background, chim, ống nước) vào bộ nhớ và vẽ lớp nền lên màn hình thông qua phương thức paintComponent.

### Câu 2: Khởi tạo đối tượng Bird và xử lý lên xuống
* **Ý tưởng:** Mô phỏng tương tác vật lý cơ bản cho nhân vật. Chú chim sẽ liên tục chịu tác động của trọng lực kéo rơi xuống đáy và chỉ nảy lên khi nhận được lực tác động từ người chơi.
* **Cách thực hiện:** Khởi tạo class Model Bird chứa các thuộc tính tọa độ (x, y), kích thước và thông số vật lý (vận tốc rơi, trọng lực, lực nảy). Xây dựng bộ lắng nghe sự kiện bàn phím (KeyListener) ở màn hình chính. Khi người chơi nhấn phím Space hoặc Enter, hệ thống lập tức ghi đè vận tốc rơi hiện tại bằng một xung lực âm để đẩy tọa độ chim bay ngược lên trên.

### Câu 3: Thiết lập gameloop và Pipe cho trò chơi
* **Ý tưởng:** Thiết lập một vòng lặp thời gian chạy liên tục để cập nhật hình ảnh, tạo cảm giác chuyển động mượt mà. Đồng thời, các chướng ngại vật (ống nước) phải được tự động sinh ra và di chuyển cuốn về phía chim.
* **Cách thực hiện:** Sử dụng bộ đếm thời gian javax.swing.Timer với độ trễ 16ms (tương đương 60 FPS) làm Game Loop để liên tục tính toán tọa độ mới và vẽ lại khung hình. Dùng một Timer thứ hai để tự động tạo đối tượng Pipe (ống nước) mới mỗi 1.5 giây. Chiều cao của các ống nước được tính toán ngẫu nhiên, luôn đảm bảo chừa lại một khoảng trống cố định (pipeGap) ở giữa màn hình để chim có thể bay qua an toàn.

### Câu 4: Thực hiện cơ chế tính điểm và restart trò chơi
* **Ý tưởng:** Xây dựng cơ chế để theo dõi điểm số, phát hiện va chạm dẫn đến kết thúc trò chơi (Game Over) và cho phép người chơi khởi động lại màn chơi mới nhanh chóng.
* **Cách thực hiện:** Sử dụng đối tượng Rectangle để tạo các hộp khung va chạm vô hình bao quanh nhân vật chim và ống nước. Nếu các khung này giao cắt nhau hoặc chim rơi chạm tọa độ đáy màn hình, cờ trạng thái gameOver sẽ được bật sáng. Nếu chim bay vượt qua thành công tọa độ X của một ống nước, điểm số sẽ được cộng thêm 1. Tại màn hình Game Over, nếu nhận được tín hiệu nhấn phím từ người chơi, hệ thống sẽ làm sạch toàn bộ mảng chướng ngại vật, đưa điểm số và tọa độ chim về vạch xuất phát để bắt đầu một lượt chơi mới.

## LAB 3

### Thiết kế website bán sản phẩm bằng Java Swing

**Yêu cầu 1: Thiết kế bố cục giao diện (8 điểm)**
* **Ý tưởng:** Ứng dụng Java Swing để thiết kế phần mềm Desktop mang phong cách trực quan, hiện đại giống một trang web thương mại điện tử (E-commerce UI). Giao diện được chia làm hai khu vực chính: danh sách sản phẩm dạng lưới có thể cuộn ở bên phải, và khu vực xem chi tiết sản phẩm được chọn với ảnh lớn ở bên trái.
* **Cách thực hiện:** Áp dụng tư duy lập trình hướng đối tượng (OOP), tạo class `Product` riêng biệt để lưu trữ dữ liệu. Sử dụng trình quản lý `BorderLayout` cho khung cửa sổ chính. Vùng bên phải (danh sách) sử dụng `GridLayout(0, 4)` đặt bên trong `JScrollPane` (được cấu hình ẩn thanh cuộn ngang, ép hiển thị thanh cuộn dọc) để chứa các thẻ (card) sản phẩm. Vùng bên trái dùng `BoxLayout` xếp dọc để hiển thị chi tiết sản phẩm. Các thẻ sản phẩm được làm đẹp bằng cách kết hợp `EmptyBorder` và `LineBorder` bo góc.

**Yêu cầu 2: Thêm tính năng click chọn sản phẩm (1 điểm)**
* **Ý tưởng:** Khi người dùng tương tác với bất kỳ thẻ sản phẩm nào ở danh sách bên phải, thông tin của sản phẩm đó phải lập tức được ánh xạ và cập nhật sang khu vực hiển thị chính ở bên trái.
* **Cách thực hiện:** Đăng ký sự kiện `MouseListener` (thông qua lớp `MouseAdapter`) trên mỗi `JPanel` đại diện cho thẻ sản phẩm. Khi bắt được sự kiện `mouseClicked`, hệ thống sẽ gọi hàm cập nhật (`updateLeftPanel`), truyền đối tượng `Product` tương ứng vào để thay đổi đồng loạt nội dung của các `JLabel` bên trái (Tên, giá, thương hiệu, mô tả, hình ảnh). Đồng thời, bổ sung thêm hiệu ứng đổi màu nền thẻ khi lướt chuột qua (`mouseEntered` / `mouseExited`).

**Yêu cầu 3: Thêm hiệu ứng thay đổi sản phẩm (1 điểm)**
* **Ý tưởng:** Để thao tác trải nghiệm mềm mại và sát với các website thực tế hơn, hình ảnh hiển thị ở khu vực bên trái không thay đổi một cách đột ngột mà sẽ có hiệu ứng mờ dần rồi rõ nét lên (Fade-in).
* **Cách thực hiện:** Xây dựng một class tùy chỉnh `ImageLabel` kế thừa trực tiếp từ `JLabel`. Ghi đè phương thức vẽ đồ họa `paintComponent` và sử dụng `AlphaComposite` để can thiệp vào độ trong suốt của ảnh. Thiết lập một bộ đếm `javax.swing.Timer` chạy lặp mỗi 20ms. Mỗi khi click chọn sản phẩm mới, `Timer` sẽ kích hoạt để tăng dần biến độ mờ (alpha) từ 0 đến 1, qua đó tạo ra hiệu ứng chuyển đổi hình ảnh mượt mà.
