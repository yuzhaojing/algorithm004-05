package laioffer.CrossTrainingIII;

import laioffer.practice.design_queue_stack_deque.ListNode;

import java.util.*;

public class CommonNumbersOfTwoArraysI {

    public static void main(String[] args) {

    }

    /**
     * input: int[] a
     * int[] b
     * output: List<Integer>
     * Assume: a != null && b != null 并且没有重复元素
     * 如果不符合假设，那么二维数组中没有元素，返回null
     * <p>
     * high level: 使用hashset解答
     * mid level: 遍历啊，将所有元素放在set内，然后遍历b，如果在set中存在，那么放进结果集
     * <p>
     * time: O(2n + klogk) k表示相同numble的个数
     * space: O(n)
     */
    public List<Integer> common(int[] a, int[] b) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (a == null || b == null) {
            return res;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : a) {
            set.add(num);
        }

        for (int num : b) {
            if (set.contains(num)) {
                res.add(num);
            }
        }

        res.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer l1, Integer l2) {
                if (l1.equals(l2)) {
                    return 0;
                }

                return l1 < l2 ? -1 : 1;
            }
        });

        return res;
    }
}
