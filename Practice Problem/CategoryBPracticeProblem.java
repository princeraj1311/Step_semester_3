import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryBPracticeProblem {

    static class Book {
        String isbn;
        String title;

        Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    static class SummaryResult {
        int totalItems;
        int row;
        int col;

        SummaryResult(int totalItems, int row, int col) {
            this.totalItems = totalItems;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", (" + row + ", " + col + "))";
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

    public static SummaryResult warehouseSummary(int[][] grid) {
        int totalItems = 0;
        int maxValue = Integer.MIN_VALUE;
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                totalItems += grid[i][j];

                if (grid[i][j] > maxValue) {
                    maxValue = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        return new SummaryResult(totalItems, maxRow, maxCol);
    }

    public static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();

        for (int value : nums) {
            int complement = target - value;

            if (seen.contains(complement)) {
                return true;
            }

            seen.add(value);
        }

        return false;
    }

    public static int maxContainerArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int height = Math.min(heights[left], heights[right]);
            int area = height * width;

            if (area > maxArea) {
                maxArea = area;
            }

            if (heights[left] <= heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
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

        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };
        System.out.println(warehouseSummary(grid));

        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {3, 4, 6};
        System.out.println(hasPairWithSum(nums1, 9));
        System.out.println(hasPairWithSum(nums2, 20));

        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxContainerArea(heights));
    }
}
