import java.util.*;

/**
 * 1、两数之和
 */
public class Leetcode_56_MergeIntervals {

    public static void main(String[] args) {
        int[][] nums = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = method1(nums);
    }

    private static int[][] method1(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) return res.toArray(new int[0][]);

        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        int index = -1;
        for (int[] interval : intervals) {
            if (res.size() == 0 || res.get(index)[1] < interval[0]) {
                res.add(interval);
                index++;
            } else {
                res.get(index)[1] = Math.max(interval[1], res.get(index)[1]);
            }
        }

        return res.toArray(new int[0][]);
    }
}
