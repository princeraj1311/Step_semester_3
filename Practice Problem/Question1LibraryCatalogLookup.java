import java.util.ArrayList;
import java.util.List;

public class Question1LibraryCatalogLookup {

    static class Book {
        String isbn;
        String title;

        Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    public static String findBook(List<Book> catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = catalog.get(mid).isbn.compareTo(targetIsbn);

            if (compare == 0) {
                return catalog.get(mid).title;
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<Book> catalog = new ArrayList<>();
        catalog.add(new Book("0001112223", "Introduction to Algebra"));
        catalog.add(new Book("0002223334", "Beginning Python"));
        catalog.add(new Book("0003334445", "Classic Mythology"));
        catalog.add(new Book("0004445556", "Data and Society"));
        catalog.add(new Book("0005556667", "European History"));

        System.out.println(findBook(catalog, "0003334445"));
        System.out.println(findBook(catalog, "0009998887"));
    }
}
