package laioffer.hash_table;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] combo = {"d", "a", "c", "b", "d", "a", "b", "b", "a", "d", "d", "a", "d"};
        System.out.println(Arrays.toString(new TopKFrequentWords().topKFrequent(combo, 5)));
    }

    public String[] topKFrequent(String[] combo, int k) {
        // Write your solution here
        if (combo == null || combo.length == 0 || k <= 0) {
            return new String[0];
        }

        HashMap<String, Integer> FrequentMap = new HashMap<>();
        for (String s : combo) {
            if (!FrequentMap.containsKey(s)) {
                FrequentMap.put(s, 1);
            } else {
                FrequentMap.put(s, FrequentMap.get(s) + 1);
            }
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (FrequentMap.get(s1).equals(FrequentMap.get(s2))) {
                    return 0;
                }
                return FrequentMap.get(s1) < FrequentMap.get(s2) ? -1 : 1;
            }
        }
        );

        for (String s : FrequentMap.keySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(s);
            } else if (FrequentMap.get(minHeap.peek()) < FrequentMap.get(s)) {
                minHeap.poll();
                minHeap.offer(s);
            }
        }

        int size = FrequentMap.size() < k ? FrequentMap.size() : k;
        String[] res = new String[size];
        for (int i = size - 1; i >= 0; i--) {
            res[i] = minHeap.poll();
        }

        return res;
    }
}
