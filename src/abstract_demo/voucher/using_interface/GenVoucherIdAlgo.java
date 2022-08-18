package abstract_demo.voucher.using_interface;

/**
 * Algorithm to generate a random voucherID
 * 
 * @author anhtu
 *
 */
public interface GenVoucherIdAlgo {
    String genVoucherIdAlgo(String prefix, int length);
}
