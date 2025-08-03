package abstract_demo.voucher.using_interface;

// import org.hibernate.Session;
// import org.hibernate.exception.ConstraintViolationException;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.stereotype.Component;
// import javax.persistence.EntityManager;

public class VoucherUtil {

    // @Autowired
    private DummyRepository dummyRepository;

    // @Autowired
    // private EntityManager entityManager;

    private final int MAX_RETRY_GENERATION = 10;

    /**
     * Generate random voucherId, retry if it existed in database
     * 
     * @param genVoucherIdAlgo the algorithm used to generate random voucherId
     */
    public String generateVoucherId(String prefix, int len, String voucherType,
            GenVoucherIdAlgo genVoucherIdAlgo) {

        int retry = 0;
        String voucherId;

        while (true) {
            voucherId = genVoucherIdAlgo.genVoucherIdAlgo(prefix, len);

            DummyEntity entity = new DummyEntity();
            entity.setVoucherId(voucherId);
            entity.setVoucherType(voucherType);
            entity.setUsed(true);

            // Session session = entityManager.unwrap(Session.class);
            try {
                dummyRepository.saveAndFlush(entity);
                break;
            } catch (DataIntegrityViolationException | ConstraintViolationException e) {
                // session.clear();
                retry++;
                if (retry <= MAX_RETRY_GENERATION) {
                    System.out.println("Retry times: " + retry);
                } else {
                    System.out.println("Can not generate voucher Id. Max retries reached");
                    // throw new Exception("Can not generate voucher Id. Max retries reached");
                }
            } catch (Exception e) {
                // throw new VoucherIdNotGeneratedException("Unexpected exception occur");
            }
        }

        return voucherId;
    }
}
