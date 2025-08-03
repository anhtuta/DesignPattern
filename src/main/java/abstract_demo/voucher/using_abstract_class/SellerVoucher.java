package abstract_demo.voucher.using_abstract_class;

import java.util.UUID;

/**
 * Voucher của chủ shop
 * 
 * @author anhtu
 */
public class SellerVoucher extends AbstractVoucher implements VoucherIdGenerator {

    @Override
    public String generateVoucherId() {
        return generateVoucherId("SELLER", 10, "Seller");
    }

    @Override
    public String genVoucherIdAlgo(String prefix, int length) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix)
                .append(length)
                .append(UUID.randomUUID().toString().substring(0, length - prefix.length()));
        return sb.toString();
    }
}

