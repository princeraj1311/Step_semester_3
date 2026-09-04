public class ATMPINValidator {

    public static void checkPinLength(String pin) {
        if (pin == null) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
            return;
        }

        // Get length using length()
        int pinLength = pin.length();

        // Check if exactly 4 digits
        if (pinLength != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        // Test cases
        checkPinLength("482");
        checkPinLength("4820");
        checkPinLength("12345");
        checkPinLength("12");
        checkPinLength(null);
    }
}
