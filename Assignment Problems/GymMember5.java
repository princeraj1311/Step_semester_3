class GymMember5 {
    private static int totalEnrolled = 0;

    public final String membershipNumber;
    private int monthlyFee;
    private int feesPaid;

    public GymMember5(int monthlyFee) {
        totalEnrolled++;
        this.membershipNumber = "GYM-" + (2000 + totalEnrolled);
        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;
    }

    public static int getMembersEnrolled() {
        return totalEnrolled;
    }

    public void payFee(int amount) {
        this.feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return this.feesPaid;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'G') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        if (members == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (member instanceof GroupClassMember) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + groupCount + " group | " + individualCount + " individual";
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }
}