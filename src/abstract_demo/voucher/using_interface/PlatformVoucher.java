package abstract_demo.voucher.using_interface;

import java.util.Random;

/**
 * Voucher của nền tảng thương mại điện tử bạn đang dùng (Shopee, Tiki...)
 * 
 * @author anhtu
 */
public class PlatformVoucher implements VoucherIdGenerator, GenVoucherIdAlgo {
    
    // @Autowired
    private VoucherUtil voucherUtil;

    @Override
    public String generateVoucherId() {
        return voucherUtil.generateVoucherId("PLATFORM", 10, "Platform", this);
    }

    @Override
    public String genVoucherIdAlgo(String prefix, int length) {
        Random rd = new Random();
        int random = rd.nextInt((int) Math.pow(10, length - prefix.length()));

        StringBuilder sb = new StringBuilder();
        sb.append(prefix)
                .append(length)
                .append(random)
                .append(random % 10); // demo checksum
        return sb.toString();
    }
}
