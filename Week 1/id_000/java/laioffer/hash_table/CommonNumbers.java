package laioffer.hash_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonNumbers {

    public static void main(String[] args) {
        int[] A = {1, 1, 2, 2, 3};
        int[] B = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3};
        System.out.println(new CommonNumbers().common(A, B));
    }

    /**
     * 使用hash table记录A数组重的元素及个数
     * 遍历B数组，如果在hash table中存在该元素并且不为0
     * 则往结果集中添加该元素，并使hash table中该元素的count-1
     *
     * time = O(n + m)
     *
     * Space = O(max(A中不重复元素的个数，B中不重复元素的个数))
     */
    public List<Integer> common(int[] A, int[] B) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return res;
        }

        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : A) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        for (int num : B) {
            Integer count = counter.get(num);
            if (count != null && count > 0) {
                res.add(num);
                counter.put(num, count - 1);
            }
        }

        return res;
    }

    /**
     * 使用双指针的方式
     *
     * 1.定义leftIndex和rightIndex分别位于A和B数组的0号角标
     * 2.当leftIndex和rightIndex都是有效角标的时候，进行循环
     * 3.在循环中，如果两个指针的值相等的话，将该值放入结果集中，同时移动两个指针
     * 4.不相等的话移动指向值较小的一个，因为两个数组都是升序的
     *
     * time = O(n + m)
     *
     * Space = O(1)
     */
    public List<Integer> common1(int[] A, int[] B) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return res;
        }

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < A.length && rightIndex < B.length) {
            if (A[leftIndex] == B[rightIndex]) {
                res.add(A[leftIndex]);
                leftIndex++;
                rightIndex++;
            } else if (A[leftIndex] < B[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex++;
            }
        }

        return res;
    }

    /**
     * 还有binary search的方案
     * 遍历较短的数组，取每一个元素到另外一个数组进行binary search
     * 但是本题要求返回相同元素和个数，binary search不适合查找个数
     *
     * time = O(mlogn) m < n
     *
     * space = O(1)
     */
}
