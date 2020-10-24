package design_pattern.structural.facade;

/*
Ví dụ: Một công ty bán hàng online, chẳng hạn Tiki cung cấp nhiều lựa chọn
cho khách hàng khi mua sản phẩm. Khi một sản phẩm được mua, nó sẽ qua các
bước xử lý: lấy thông tin về tài khoản mua hàng, thanh toán, vận chuyển,
gửi Email/ SMS thông báo.

Ứng dụng của chúng ta được thiết kế với Facade Pattern, bao gồm các class như sau:

- Thông tin về tài khoản (AccountService): lấy thông tin cơ bản của khách hàng
  thông qua email được cung cấp.
- Dịch vụ thanh toán (PaymentService): có thể thanh toán thông qua Paypal, thẻ 
  tín dụng (Credit Card), tài khoản ngân hàng trực tuyến (E-banking), Tiền mặt (cash).
- Dịch vụ vận chuyển (ShippingService): có thể chọn Free Shipping, Standard Shipping,
  Express Shipping.
- Dịch vụ email (EmailService): có thể gửi mail cho khách hàng về tình hình đặt hàng,
  thanh toán, vận chuyển, nhận hàng.
- Dịch vụ tin nhắn (SmsService): có thể gửi thông báo SMS cho khách hàng khi thanh toán online.
- ShopFacade: là một Facade Pattern, class này bao gồm các dịch vụ có bên trong 
  hệ thống. Nó cung cấp một vài phương thức để Client có thể dễ dàng mua hàng.
  Tùy vào nghiệp vụ mà nó sẽ sử dụng những dịch tương ứng, chẳng hạn dịch vụ SMS
  chỉ được sử dụng nếu khách hàng đăng ký mua hàng thông qua hình thức thanh toán
  online (Paypal, E-banking, …).
- Client : là người dùng cuối sử dụng ShopFacade để mua hàng

Như bạn thấy phía Client chỉ sử dụng một phương thức duy nhất là có thể mua được sản
phẩm mặc dù bên dưới hệ thống có rất nhiều dịch vụ xử lý khác nhau. Nếu không có Facade,
phía Client sẽ không biết sử dụng những dịch vụ nào để có thể mua được sản phẩm.
Khi phát sinh thêm một dịch vụ sẽ rất khó khăn khi sửa đổi và code phía Client
cũng sẽ bị ảnh hưởng
*/
public class Client {
	public static void main(String[] args) {
        ShopFacade.getInstance().buyProductByCashWithFreeShipping("contact@gpcoder.com");
        ShopFacade.getInstance().buyProductByPaypalWithStandardShipping("gpcodervn@gmail.com", "0988.999.999");
    }
}
