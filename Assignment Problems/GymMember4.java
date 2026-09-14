class GymMember4 {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember4(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public int getSessionsAttended() {
        return this.sessionsAttended;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < members.length; i++) {
            GymMember member = members[i];
            sb.append(member.displayInfo());

            if (member instanceof PremiumMember) {
                PremiumMember pm = (PremiumMember) member;
                sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
            }

            if (i < members.length - 1) {
                sb.append(" | ");
            }
        }

        return sb.toString();
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return this.trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}