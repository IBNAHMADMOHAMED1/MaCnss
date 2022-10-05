package document;

public class Analysis implements Reimbursed {
    private static Double baseReimbursementPrice = 0.3;
    private static final Double BASE_REIMBURSEMENT_PRICE = 1000.0;

    public Double getTotalAmountReimbursed() {
        return baseReimbursementPrice;
    }

    public static Double getAmountReimbursed(Double amount) {
        if (amount > BASE_REIMBURSEMENT_PRICE) {
            return 1000 * baseReimbursementPrice;
        } else {
            return amount * baseReimbursementPrice;
        }
    }
}
