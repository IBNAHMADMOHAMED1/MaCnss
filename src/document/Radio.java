package document;

public class Radio {
    private static Double baseReimbursementPrice = 0.4;
    private static final Double BASE_REIMBURSEMENT_PRICE = 3000.0;

    // getAmountReimbursed
    public static Double getAmountReimbursed(Double amount) {
        if (amount > BASE_REIMBURSEMENT_PRICE) {
            return BASE_REIMBURSEMENT_PRICE * baseReimbursementPrice;
        } else {
            return amount * baseReimbursementPrice;
        }
    }

}
