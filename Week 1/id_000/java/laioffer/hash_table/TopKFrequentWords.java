package laioffer.hash_table;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] combo = {"d","a","c","b","d","a","b","b","a","d","d","a","d"};
        System.out.println(Arrays.toString(topKFrequent(combo, 5)));
    }

    private static String[] topKFrequent(String[] combo, int k) {
        if (combo == null || combo.length == 0) return combo;

        HashMap<String, Integer> map = new HashMap<>();

        for (String s : combo) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        // 大顶堆，全部入堆然后取k个
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o2) - map.get(o1);
            }
        });

        queue.addAll(map.keySet());

        int size = map.size() < k ? map.size() : k;
        String[] res = new String[size];
        for (int i = 0; i < size; i++) {
            res[i] = queue.poll();
        }

        //小顶堆，维持堆内元素个数为k个，每个新元素和堆顶元素比较，谁大留谁
        /*PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (String s : map.keySet()) {
            if (queue.size() < k) {
                queue.add(s);
            } else {
                String peek = queue.peek();
                if (map.get(peek) < map.get(s)) {
                    queue.poll();
                    queue.add(s);
                }
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }*/

        return res;
    }
}
