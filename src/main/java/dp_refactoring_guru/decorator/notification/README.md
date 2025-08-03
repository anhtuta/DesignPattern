This is my personal implementation based on my understanding

Note:

- Trong ví dụ này, ta tạo tất cả các concrete notifier và các concrete decorator, vì ko biết trước client sẽ tạo target object là notifier nào trước rồi với decor lên nó
- Trường hợp bắt buộc phải gửi thông báo qua email trước, sau đó mới gửi thêm qua các channel khác, thì:
  - Ko cần tạo EmailDecorator, chỉ cần tạo EmailNotifier
  - Ko cần tạo các concrete notifier còn lại, vì chúng bắt buộc phải có EmailNotifier mới hoạt động được
  - Tạo các decorator khác như SMSDecorator, SlackDecorator, ... Các decorator này có thể implement logic ở đây luôn (thay vì gọi từ concrete notifier)
