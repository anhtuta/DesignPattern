package abstract_demo.voucher.using_interface;

import java.util.Random;

/**
 * Voucher áp dụng khi thanh toán trên các nền tảng đặc biệt
 * (chẳng hạn dùng Momo hoặc ZaloPay mới có)
 * 
 * @author anhtu
 */
public class PaymentVoucher implements VoucherIdGenerator, GenVoucherIdAlgo {
    
    // @Autowired
    private VoucherUtil voucherUtil;

    @Override
    public String generateVoucherId() {
        return voucherUtil.generateVoucherId("PAYMENT", 10, "Payment", this);
    }

    @Override
    public String genVoucherIdAlgo(String prefix, int length) {
        Random rd = new Random();

        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(rd.nextInt(1000));
        return sb.toString();
    }
}
