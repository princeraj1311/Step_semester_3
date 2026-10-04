public class Question2WarehouseGridSummary {

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

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        System.out.println(warehouseSummary(grid));
    }
}
