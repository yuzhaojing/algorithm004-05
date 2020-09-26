package laioffer.Exam.Final;

import java.util.HashMap;
import java.util.Map;

public class ChainCircle {

    public static void main(String[] args) {
        String[] input = {"aaa", "aab", "bbb", "bba", "aaa"};
        System.out.println(new ChainCircle().isInfiniteLoop(input));
    }

    public boolean isInfiniteLoop(String[] input) {
        if (input == null || input.length == 0) {
            return false;
        }

        return DFS(input, 1);
    }

    private boolean DFS(String[] input, int index) {
        if (input.length == index) {
            if (input[0].charAt(0) == input[input.length - 1].charAt(input[input.length - 1].length() - 1)) {
                return true;
            }

            return false;
        }

        for (int i = index; i < input.length; i++) {
            if (input[index - 1].charAt(input[index - 1].length() - 1) == input[i].charAt(0)) {
                swap(input, i, index);
                if (DFS(input, index + 1)) {
                    return true;
                }
                swap(input, i, index);
            }
        }

        return false;
    }

    private void swap(String[] array, int left, int right) {
        String temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    /**
     * input:  String[] input
     * output: boolean
     * Assume: input != null && input.length() > 1
     * <p>
     * high level: 使用DFS解答
     * detail level:
     * 1、将每个string作为起点进行DFS
     * 2、每次recursion遍历input，找到第一个字符和当前string最后一个字符相等的string，进行recursion
     * 3、直到递归层数等于input.length(), 返回true
     * <p>
     * time = n! * n
     * 每层最多n个叉，n = input.length()，每递归一层少一个叉
     * 最多n层
     * space = O(n)
     */
    public boolean isChainCircle(String[] input) {
        if (input == null || input.length < 2) {
            return true;
        }

        Map<String, Integer> strToCount = new HashMap<>();
        for (String str : input) {
            strToCount.put(str, strToCount.getOrDefault(str, 0) + 1);
        }


        return helper("", 1, strToCount, input.length);
    }

    private boolean helper(String str, int level, Map<String, Integer> strToCount, int target) {
        if (level == target) {
            return true;
        }

        for (Map.Entry<String, Integer> entry : strToCount.entrySet()) {
            int value = entry.getValue();
            if (entry.getValue() > 0 && (str.isEmpty() || entry.getKey().charAt(0) == str.charAt(str.length() - 1))) {
                entry.setValue(value - 1);
                if (helper(entry.getKey(), level + 1, strToCount, target)) {
                    return true;
                }
                entry.setValue(value);
            }
        }

        return false;
    }
}
