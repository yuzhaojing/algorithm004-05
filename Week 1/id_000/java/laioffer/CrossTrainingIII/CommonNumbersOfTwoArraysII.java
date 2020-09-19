package laioffer.CrossTrainingIII;

import java.util.*;

public class CommonNumbersOfTwoArraysII {

    public static void main(String[] args) {

    }

    /**
     * input: int[] a
     *        int[] b
     * output: List<Integer>
     * Assume: a != null && b != null
     * 如果不符合假设，那么二维数组中没有元素，返回null
     *
     * high level: 使用hashmap解答
     * mid level: 遍历A，将所有元素放在map内，然后遍历b，如果在map中存在并且value大于0，那么放进结果集并将map的value减1
     *
     * time: O(2n + klogk) k表示相同number的个数
     * space: O(n)
     */
    public List<Integer> common(int[] A, int[] B) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (A == null || B == null) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : B) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                res.add(num);
                map.put(num, count - 1);
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
