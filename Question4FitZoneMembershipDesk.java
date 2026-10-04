import java.util.Locale;

public class Question4FitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership quarterly = new Membership(asha, new QuarterlyPlan());
        Membership monthly = new Membership(ravi, new MonthlyPlan());

        System.out.println("Quarterly membership created for Asha. Fee: ₹2,700.00. Status: Active.");
        System.out.println("Monthly membership created for Ravi. Fee: ₹1,000.00. Status: Active.");

        asha.getMembership().checkIn();
        asha.getMembership().freeze();
        asha.getMembership().checkIn();

        ravi.getMembership().expire();
        System.out.println("Ravi's membership expired. Status: Expired.");
        try {
            ravi.getMembership().freeze();
        } catch (IllegalStateException ex) {
            System.out.println("Cannot freeze an Expired membership.");
        }
    }
}

class Member {
    private final String name;
    private Membership membership;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}

interface MembershipPlan {
    String getPlanName();
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {
    @Override
    public String getPlanName() {
        return "Monthly";
    }

    @Override
    public double calculateFee() {
        return 1000.0;
    }
}

class QuarterlyPlan implements MembershipPlan {
    @Override
    public String getPlanName() {
        return "Quarterly";
    }

    @Override
    public double calculateFee() {
        return 2700.0;
    }
}

class AnnualPlan implements MembershipPlan {
    @Override
    public String getPlanName() {
        return "Annual";
    }

    @Override
    public double calculateFee() {
        return 9000.0;
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Membership {
    private final Member member;
    private final MembershipPlan plan;
    private MembershipStatus status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;
        this.member.setMembership(this);
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
            return;
        }
        if (status == MembershipStatus.FROZEN) {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is Frozen.");
            return;
        }
        System.out.println("Check-in denied: " + member.getName() + "'s membership is Expired.");
    }

    public void freeze() {
        if (status == MembershipStatus.EXPIRED) {
            throw new IllegalStateException("Cannot freeze an Expired membership.");
        }
        if (status == MembershipStatus.ACTIVE) {
            status = MembershipStatus.FROZEN;
            System.out.println(member.getName() + "'s membership frozen. Status: Frozen.");
            return;
        }
        if (status == MembershipStatus.FROZEN) {
            System.out.println(member.getName() + "'s membership is already frozen.");
        }
    }

    public void unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            throw new IllegalStateException("Cannot unfreeze an Expired membership.");
        }
        if (status == MembershipStatus.FROZEN) {
            status = MembershipStatus.ACTIVE;
            System.out.println(member.getName() + "'s membership unfrozen. Status: Active.");
            return;
        }
        System.out.println(member.getName() + "'s membership is already active.");
    }

    public void expire() {
        if (status == MembershipStatus.EXPIRED) {
            return;
        }
        status = MembershipStatus.EXPIRED;
    }
}
