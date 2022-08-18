package abstract_demo.voucher.using_abstract_class;

/**
 * Dùng để check xem object được tạo ra có bị trùng trong trường hợp đa luồng ko.
 * Ý tưởng: lúc tạo random, check nó tồn tại trong db chưa, nếu chưa thì lưu xuống!
 * Các thread khác cũng vậy, khi 1 thread đã lưu xuống db rồi thì các thread khác
 * nến gen random trùng nhau sẽ bị lỗi!
 * 
 * @author anhtu
 */
public class DummyEntity {

    private String voucherId;
    private String voucherType;
    private boolean isUsed;

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}

interface DummyRepository {

    void saveAndFlush(DummyEntity entity)
            throws DataIntegrityViolationException, ConstraintViolationException;

}

class EntityManager {

}

class DataIntegrityViolationException extends Throwable {

    private static final long serialVersionUID = -5767418805170672498L;

}

class ConstraintViolationException extends Throwable {

    private static final long serialVersionUID = 2962291958556382368L;

}
