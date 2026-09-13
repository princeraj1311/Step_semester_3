import java.util.LinkedHashMap;
import java.util.Map;

public class AssignmentProblem1 {

    static class LibraryMember {
        private String membershipPin;
        String branchCode;         // package-private (default)
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipPin, String branchCode, double finesOwed, String displayName) {
            this.membershipPin = membershipPin;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    public static class AccessChecker {

        public static String classifyAccess(String fieldModifier, String accessorContext) {
            switch (fieldModifier) {
                case "private":
                    return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
                case "default":
                    return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext))
                            ? "ALLOWED" : "DENIED";
                case "protected":
                    return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext))
                            ? "ALLOWED" : "DENIED";
                case "public":
                    return "ALLOWED";
                default:
                    return "DENIED";
            }
        }

        public static String summarizeByModifier(String[][] attempts) {
            Map<String, int[]> counts = new LinkedHashMap<>();
            counts.put("private", new int[]{0, 0});
            counts.put("default", new int[]{0, 0});
            counts.put("protected", new int[]{0, 0});
            counts.put("public", new int[]{0, 0});

            if (attempts != null) {
                for (String[] attempt : attempts) {
                    if (attempt == null || attempt.length < 2) continue;
                    String modifier = attempt[0];
                    String context = attempt[1];
                    String result = classifyAccess(modifier, context);

                    if (counts.containsKey(modifier)) {
                        if ("ALLOWED".equals(result)) {
                            counts.get(modifier)[0]++;
                        } else {
                            counts.get(modifier)[1]++;
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, int[]> entry : counts.entrySet()) {
                if (!first) {
                    sb.append(" ");
                }
                sb.append(entry.getKey()).append(": ")
                        .append(entry.getValue()[0]).append(" allowed / ")
                        .append(entry.getValue()[1]).append(" denied");
                first = false;
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(AccessChecker.summarizeByModifier(attempts));
    }
}