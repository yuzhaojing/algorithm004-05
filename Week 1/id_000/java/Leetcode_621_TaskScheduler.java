import java.util.Arrays;
import java.util.Comparator;

/**
 * 1、两数之和
 */
public class Leetcode_621_TaskScheduler {

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        System.out.println(method1(tasks, 2));
    }

    private static int method1(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;

        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }

        Arrays.sort(map);

        int minCost = (map[map.length - 1] - 1) * (n + 1);
        for (int count : map) {
            if (map[map.length - 1] == count) minCost++;
        }

        return minCost < tasks.length ? tasks.length : minCost;
    }
}
