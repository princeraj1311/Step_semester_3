public class AssignmentProblem4 {

    public static class LibraryMember {
        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String securityAnswerHash;

        public LibraryMember() {
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String id) {
            if (this.membershipId == null) {
                this.membershipId = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(boolean premiumMember) {
            this.premiumMember = premiumMember;
        }

        public void setSecurityAnswer(String answer) {
            if (answer != null) {
                this.securityAnswerHash = String.valueOf(answer.hashCode());
            }
        }
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println(m.getMembershipId());

        m.setMembershipId("FAKE-0000"); // Ignored (write-once)
        System.out.println(m.getMembershipId());

        System.out.println(m.isPremiumMember());

        m.setSecurityAnswer("BlueMountain"); // Write-only property stored securely
    }
}