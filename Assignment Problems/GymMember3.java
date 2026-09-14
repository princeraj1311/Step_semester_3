import java.util.Arrays;

class GymMember3 {
    private String memberId;
    private int monthlyFee;
    private int[] lateFeeHistory;
    private int feeCount;

    public GymMember3(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.lateFeeHistory = new int[10];
        this.feeCount = 0;
    }

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount++] = amount;
        }
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }
}

class PremiumMember3 extends GymMember {
    private String trainerName;

    public PremiumMember3(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }


    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}