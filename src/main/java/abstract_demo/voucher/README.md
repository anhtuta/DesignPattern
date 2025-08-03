## So sánh cách dùng interface và abstract class

Bài toán: các class `PlatformVoucher`, `PaymentVoucher`, `SellerVoucher` đều implement `VoucherIdGenerator` vì chúng đều có chức năng `generateVoucherId`

Việc `generateVoucherId` có logic khá giống nhau => tách logic đó sang 1 class khác (`VoucherUtil` chẳng hạn)

Method `generateVoucherId` của 3 kiểu voucher chỉ khác nhau cách gen chuỗi ngẫu nhiên thôi (thằng thì cần thêm checksum, thằng thì chỉ gen số, thằng thì gen string...)

=> Method của `VoucherUtil` phải để các voucher tự gen random string => khá rắc rối chỗ này

=> Solution: mỗi kiểu voucher sẽ định nghĩa cách gen random string, và gửi cho `VoucherUtil`

=> Dùng interface để làm việc này (check code)

=> Nhưng phải truyền this cho bên VoucherUtil

```java
@Override
public String generateVoucherId() {
    return voucherUtil.generateVoucherId("PAYMENT", 10, "Payment", this);
}
```

Rõ ràng trường hợp này, dùng abstract class hợp lý hơn, vì như vậy:

- Ko cần class VoucherUtil nữa (thay nó = abstract class)
- Ko cần truyền `this` qua lại nữa
- Code flow trông đơn giản và dễ hiểu hơn