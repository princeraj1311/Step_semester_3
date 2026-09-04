public class PhoneNumberMasker {

    public static String maskPhoneNumber(String phone) {
        // Validate input
        if (phone == null || phone.isEmpty()) {
            return "Invalid phone number";
        }

        // Remove any spaces or special characters
        String cleanPhone = phone.replaceAll("[^0-9]", "");

        // Validate exactly 10 digits
        if (cleanPhone.length() != 10) {
            return "Invalid phone number";
        }

        // Extract last 4 digits
        String lastFour = cleanPhone.substring(6);

        // Build masked number using StringBuilder
        StringBuilder maskedNumber = new StringBuilder();
        maskedNumber.append("XXXXXX");
        maskedNumber.append("-");
        maskedNumber.append(lastFour);

        return maskedNumber.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(maskPhoneNumber("9876543210"));
        System.out.println(maskPhoneNumber("98765"));
        System.out.println(maskPhoneNumber("123-456-7890"));
        System.out.println(maskPhoneNumber("1234567890"));
    }
}
