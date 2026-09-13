public class AssignmentProblem2 {

    public static class AccessChecker {

        public static String classifyAccess(String fieldModifier, String accessorContext) {
            switch (fieldModifier) {
                case "private":
                    return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
                case "default":
                    return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext))
                            ? "ALLOWED" : "DENIED";
                case "protected":
                    if ("SAME_CLASS".equals(accessorContext) ||
                            "SAME_PACKAGE".equals(accessorContext) ||
                            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                        return "ALLOWED";
                    } else {
                        return "DENIED";
                    }
                case "public":
                    return "ALLOWED";
                default:
                    return "DENIED";
            }
        }

        public static String firstDeniedAttempt(String[][] attempts) {
            if (attempts == null) return "None Denied";

            for (int i = 0; i < attempts.length; i++) {
                String[] attempt = attempts[i];
                if (attempt == null || attempt.length < 2) continue;

                String modifier = attempt[0];
                String context = attempt[1];
                String status = classifyAccess(modifier, context);

                if ("DENIED".equals(status)) {
                    return modifier + " via " + context + " (attempt #" + (i + 1) + ")";
                }
            }
            return "None Denied";
        }
    }

    public static void main(String[] args) {
        String[][] attempts1 = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(AccessChecker.firstDeniedAttempt(attempts1));

        String[][] attempts2 = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(AccessChecker.firstDeniedAttempt(attempts2));
    }
}