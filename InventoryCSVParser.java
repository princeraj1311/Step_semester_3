public class InventoryCSVParser {

    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Split by comma
        String[] fields = csvLine.split(",");

        // Validate exactly 3 fields
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        // Trim each field to remove extra spaces
        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String quantity = fields[2].trim();

        // Validate fields are not empty
        if (productName.isEmpty() || sku.isEmpty() || quantity.isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Validate quantity is numeric
        try {
            Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.printf("Product: %s | SKU: %s | Qty: %s%n",
                productName, sku, quantity);
    }

    public static void main(String[] args) {
        // Test cases
        parseInventoryRecord("Wireless Mouse,WM-2201,150");
        parseInventoryRecord("Wireless Mouse,150");
        parseInventoryRecord("Keyboard,KB-100,50,Extra");
        parseInventoryRecord("");
        parseInventoryRecord("Laptop,LP-500,100");
    }
}