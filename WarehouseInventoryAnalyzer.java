public class WarehouseInventoryAnalyzer {

    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA.length != sectionB.length) {
            System.out.println("Error: Arrays must be of equal length");
            return;
        }

        int totalA = 0;
        int totalB = 0;
        int highestQuantity = Integer.MIN_VALUE;
        int highestSection = -1;
        int highestIndex = -1;

        // Calculate totals and find highest quantity
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];

            // Check Section A
            if (sectionA[i] > highestQuantity) {
                highestQuantity = sectionA[i];
                highestSection = 1; // Section A
                highestIndex = i;
            }

            // Check Section B
            if (sectionB[i] > highestQuantity) {
                highestQuantity = sectionB[i];
                highestSection = 2; // Section B
                highestIndex = i;
            }
        }

        // Print section totals
        System.out.printf("Section A Total: %d | Section B Total: %d | ", totalA, totalB);

        // Check if balanced
        if (totalA == totalB) {
            System.out.print("Status: Balanced | ");
        } else {
            System.out.print("Status: Not Balanced | ");
        }

        // Print highest quantity
        String sectionName = (highestSection == 1) ? "Section A" : "Section B";
        System.out.printf("Highest Quantity: %d (%s, Item %d)%n",
                highestQuantity, sectionName, highestIndex + 1);
    }

    public static void main(String[] args) {
        // Test cases
        int[] sectionA1 = {20, 15, 30};
        int[] sectionB1 = {25, 10, 30};

        System.out.println("Test Case 1:");
        analyzeInventory(sectionA1, sectionB1);

        System.out.println("\nTest Case 2:");
        int[] sectionA2 = {10, 20, 15};
        int[] sectionB2 = {10, 25, 10};
        analyzeInventory(sectionA2, sectionB2);
    }
}
