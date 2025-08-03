package abstract_demo.voucher.using_interface;

import java.util.UUID;

/**
 * Voucher của chủ shop
 * 
 * @author anhtu
 */
public class SellerVoucher implements VoucherIdGenerator, GenVoucherIdAlgo {

    // @Autowired
    private VoucherUtil voucherUtil;

    @Override
    public String generateVoucherId() {
        return voucherUtil.generateVoucherId("SELLER", 10, "Seller", this);
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

