import java.util.HashSet;
import java.util.Set;

public class Question3PairWithTargetSum {

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

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {3, 4, 6};

        System.out.println(hasPairWithSum(nums1, 9));
        System.out.println(hasPairWithSum(nums2, 20));
    }
}
