public class ISBNNormalizerValidator {

    // Method 1: Normalize the code
    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }

        // Trim spaces
        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed;
        }

        // Uppercase first 3 characters only
        String publisherCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        return publisherCode + rest;
    }

    // Method 2: Validate and format
    public static String validateAndFormat(String code) {
        // Normalize the code
        String normalized = normalizeCode(code);

        // Check length
        if (normalized.length() != 13) {
            return "Invalid: wrong length (expected 13 characters, got " + normalized.length() + ")";
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Validate remaining 10 characters are digits
        for (int i = 3; i < normalized.length(); i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        // Extract components
        String publisherCode = normalized.substring(0, 3);
        String year = normalized.substring(3, 7);
        String catalog = normalized.substring(7);

        // Build formatted display using StringBuilder
        StringBuilder result = new StringBuilder();
        result.append("[");
        result.append(publisherCode);
        result.append("] YEAR: 20");
        result.append(year);
        result.append(" | CATALOG: ");
        result.append(catalog);

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(validateAndFormat("pen2026004251"));
        System.out.println(validateAndFormat("12N20260004251"));
        System.out.println(validateAndFormat("PEN2026004251"));
        System.out.println(validateAndFormat("pen20260042512"));
        System.out.println(validateAndFormat("pe2026004251"));
        System.out.println(validateAndFormat("pen2026 04251"));
        System.out.println(validateAndFormat(" pen2026004251 "));
    }
}