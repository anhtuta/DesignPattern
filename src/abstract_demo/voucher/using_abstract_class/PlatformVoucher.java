package abstract_demo.voucher.using_abstract_class;

import java.util.Random;

/**
 * Voucher của nền tảng thương mại điện tử bạn đang dùng (Shopee, Tiki...)
 * 
 * @author anhtu
 */
public class PlatformVoucher extends AbstractVoucher implements VoucherIdGenerator {

    @Override
    public String generateVoucherId() {
        return generateVoucherId("PLATFORM", 10, "Platform");
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
