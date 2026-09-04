public class FileExtensionValidator {

    public static String validateFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "Rejected — invalid file type";
        }

        // Find last dot
        int lastDotIndex = filename.lastIndexOf('.');

        // If no dot found
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        // Extract extension (including the dot)
        String extension = filename.substring(lastDotIndex + 1);

        // Check against accepted extensions (case-insensitive)
        if (extension.equalsIgnoreCase("pdf") ||
                extension.equalsIgnoreCase("docx") ||
                extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        } else {
            return "Rejected — invalid file type";
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(validateFileExtension("Assignment1.PDF"));
        System.out.println(validateFileExtension("notes.txt"));
        System.out.println(validateFileExtension("document.docx"));
        System.out.println(validateFileExtension("archive.ZIP"));
        System.out.println(validateFileExtension("file"));
    }
}
