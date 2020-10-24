package laioffer.hash_table;


import java.util.*;

public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] combo = {"d", "a", "c", "b", "d", "a", "b", "b", "a", "d", "d", "a", "d"};
        System.out.println(Arrays.toString(new TopKFrequentWords().topKFrequent(combo, 5)));
    }

    /**
     * 1、遍历combo，使用hashmap统计每个string和出现的次数
     * 2、遍历hashmap，将数据放在小顶堆中，如果比堆顶的大，就更新
     *
     * combo长度为n，一共有m个不同的string
     * time = O(n + k + (m - k)logk)
     * 遍历combo，统计: O(n)
     * 将前k个string通过heapify构建成小顶堆: O(k)
     * 将后面的所有string和堆顶元素比较并更新: O((m - k)logk)
     *
     * space = O(m + k)
     * hashMap: O(m)
     * minHeap: O(k)
     */
    public String[] topKFrequent(String[] combo, int k) {
        // Write your solution here
        if (combo == null || combo.length == 0 || k <= 0) {
            return new String[0];
        }

        Map<String, Integer> counter = new HashMap<>();
        for (String s : combo) {
            counter.put(s, counter.getOrDefault(s, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                if (entry1.getValue().equals(entry2.getValue())) {
                    return 0;
                }

                return entry1.getValue() < entry2.getValue() ? -1 : 1;
            }
        }
        );

        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else {
                if (minHeap.peek().getValue() < entry.getValue()) {
                    minHeap.poll();
                    minHeap.offer(entry);
                }
            }
        }

        int size = k < minHeap.size() ? k : minHeap.size();
        String[] res = new String[size];

        for (int i = size - 1; i >= 0; i--) {
            res[i] = minHeap.poll().getKey();
        }

        return res;
    }
}
