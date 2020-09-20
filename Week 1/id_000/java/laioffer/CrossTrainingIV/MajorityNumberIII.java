package laioffer.CrossTrainingIV;

import java.util.*;

public class MajorityNumberIII {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 5);
        map.put(2, 5);
        map.put(3, 5);
        map.put(4, 5);
        map.put(5, 2);
        map.put(6, 1);

        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            if (entry.getValue() == 1) {
                iter.remove();
            } else {
                entry.setValue(entry.getValue() - 1);
            }
        }

        System.out.println(new MajorityNumberIII().majority(new int[]{1, 4, 3, 5, 2, 2, 1, 6}, 4));
    }

    /**
     * input:  int[] array
     * output: int
     * Assume: array != null && array.length > 0
     * high level: 使用摩尔投票算法解答。假设一共m个元素，里面超过1/k的元素为n个， 那么 n / m > 1 / k
     *             此时在array中同时删除k个不同的元素，不会使得结果改变。
     *             证明：1、删除的k个元素中没有要找的元素 n / (m - k) > n / m > 1 / k
     *                  2、删除的k个元素中有要找的元素 (n - 1) / (m - k) > (m / k - 1) / (m - k) = 1 / k
     * detail level:
     *  1、使用一个HashMap，里面只记录两个元素
     *  2、遍历array，每个元素判断一下map里是否内该元素。如果有就count++，否则所有元素count--
     *     如果count归0，那么删除这个元素，map内没有元素，当新的元素过来直接put
     *  3、最后遍历map中剩余的元素，找到数量超过1 / k的那个元素
     *
     * time = O(n)
     * space = O(k)
     */
    public List<Integer> majority(int[] array, int k) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (array == null || array.length == 0) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else if (map.size() < k - 1) {
                map.put(num, 1);
            } else {
                Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iter.next();
                    if (entry.getValue() == 1) {
                        iter.remove();
                    } else {
                        entry.setValue(entry.getValue() - 1);
                    }
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.setValue(0);
        }

        for (int num : array) {
            if (map.containsKey(num)) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() * k > array.length) {
                res.add(entry.getKey());
            }
        }

        return res;
    }
}
