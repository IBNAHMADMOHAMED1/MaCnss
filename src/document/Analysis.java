package document;

public class Analysis implements Reimbursed {
    private Double baseReimbursementPrice = 0.2;

    public Double getTotalAmountReimbursed() {
        return baseReimbursementPrice;
    }
}
