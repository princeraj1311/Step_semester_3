import java.util.HashMap;
import java.util.Map;

public class Question3MostPopularCanteenOrder {
    public static PopularItem mostPopular(String[] orders) {
        if (orders == null || orders.length == 0) {
            throw new IllegalArgumentException("Orders list cannot be null or empty.");
        }

        Map<String, Integer> counts = new HashMap<>();
        for (String order : orders) {
            counts.put(order, counts.getOrDefault(order, 0) + 1);
        }

        String bestItem = null;
        int bestCount = 0;

        for (String order : orders) {
            int currentCount = counts.get(order);
            if (currentCount > bestCount) {
                bestCount = currentCount;
                bestItem = order;
            }
        }

        return new PopularItem(bestItem, bestCount);
    }

    public static void main(String[] args) {
        String[] orders = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println(mostPopular(orders));
    }

    public static class PopularItem {
        private final String item;
        private final int count;

        public PopularItem(String item, int count) {
            this.item = item;
            this.count = count;
        }

        public String getItem() {
            return item;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "(" + item + ", " + count + ")";
        }
    }
}
