public class BankReferenceValidator {

    public static String normalizeReference(String raw) {
        if (raw == null) {
            return "";
        }
        // Trim spaces and convert first 3 characters to uppercase
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }

        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        return bankCode + rest;
    }

    public static String validateAndFormat(String reference) {
        // Normalize the reference
        String normalized = normalizeReference(reference);

        // Validate length
        if (normalized.length() != 14) {
            return "Invalid: wrong length (expected 14 characters, got " + normalized.length() + ")";
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Validate remaining 11 characters are digits
        for (int i = 3; i < normalized.length(); i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        // Extract components
        String bankCode = normalized.substring(0, 3);
        String datePart = normalized.substring(3, 9);
        String seqPart = normalized.substring(9);

        // Format date as dd/MM/yy
        String day = datePart.substring(0, 2);
        String month = datePart.substring(2, 4);
        String year = datePart.substring(4, 6);
        String formattedDate = day + "/" + month + "/" + year;

        // Build formatted display using StringBuilder
        StringBuilder result = new StringBuilder();
        result.append("[");
        result.append(bankCode);
        result.append("] DATE: ");
        result.append(formattedDate);
        result.append(" | SEQ: ");
        result.append(seqPart);

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println("Test 1: " + validateAndFormat(" hdf03022600042 "));
        System.out.println("Test 2: " + validateAndFormat("12F03022600042"));
        System.out.println("Test 3: " + validateAndFormat("HDF03022600042123"));
        System.out.println("Test 4: " + validateAndFormat("HDF030226000A2"));
        System.out.println("Test 5: " + validateAndFormat("ABC15092412345"));
    }
}