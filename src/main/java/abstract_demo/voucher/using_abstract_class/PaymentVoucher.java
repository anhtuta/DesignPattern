package abstract_demo.voucher.using_abstract_class;

import java.util.Random;

/**
 * Voucher áp dụng khi thanh toán trên các nền tảng đặc biệt
 * (chẳng hạn dùng Momo hoặc ZaloPay mới có)
 * 
 * @author anhtu
 */
public class PaymentVoucher extends AbstractVoucher implements VoucherIdGenerator {

    @Override
    public String generateVoucherId() {
        return generateVoucherId("PAYMENT", 10, "Payment");
    }

    @Override
    public String genVoucherIdAlgo(String prefix, int length) {
        Random rd = new Random();

        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(rd.nextInt(1000));
        return sb.toString();
    }
}
