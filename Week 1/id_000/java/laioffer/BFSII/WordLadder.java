package laioffer.BFSII;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("d", "b", "a");
        System.out.println(new WordLadder().ladderLength1("b", "a", wordList));
    }

    /**
     * input:  String beginWord
     *         String endWord
     *         List<String> wordList
     * output: int
     * Assume: wordList内所有单词长度一致，并且都为小写。并且wordList中不能有重复元素
     *
     * high level: 使用BFS1进行解答
     * detail level: 以beginWord作为起点，尝试generation每一个有可能的neighbor，然后查看是够存在wordList中
     *  1、initial state: beginWord
     *  2、generation rule:
     *     expand word , generation all posiable change one element
     *  3、termination condition: expand endWord
     *
     * 假设wordList中一共有n个单词，每个单词的长度为m
     * time = n + n * 25m^2 = O(nm^2)
     * space = n + n = O(n)
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Write your solution here
        if (beginWord == null || endWord == null) {
            return -1;
        }

        if (beginWord.equals(endWord)) {
            return 0;
        }

        Set<String> dict = new HashSet<>(wordList);
        Map<String, Integer> wordToStep = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        wordToStep.put(beginWord, 1);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            int step = wordToStep.get(cur);
            StringBuilder sb = new StringBuilder(cur);

            for (int i = 0; i < cur.length(); i++) {
                // 记录index = i的位置原来的字符
                char origin = cur.charAt(i);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == origin) {
                        continue;
                    }

                    sb.setCharAt(i, c);
                    if (sb.toString().equals(endWord)) {
                        return step + 1;
                    }
                    if (dict.contains(sb.toString()) && !wordToStep.containsKey(sb.toString())) {
                        queue.offer(sb.toString());
                        wordToStep.put(sb.toString(), step + 1);
                    }
                }
                // 遍历之后还原
                sb.setCharAt(i, origin);
            }
        }

        return 0;
    }

    /**
     * input:  String beginWord
     *         String endWord
     *         List<String> wordList
     * output: int
     * Assume: wordList内所有单词长度一致，并且都为小写。并且wordList中不能有重复元素
     *
     * high level: 使用BFS1进行解答
     * detail level:
     *  1、先进行一次预处理，将所有的word转换成通配符形式，并将word以list的形式存在value内
     *  2、之后进行BFS1，遍历每个poll的word，将其所有的通配符形式去查找匹配的word
     *
     *  3、遍历所有匹配出得word
     * time = n * m^2 + n + n * (m *(m + n)) = O(nm(n+m))
     * 预处理 n*m^2
     * V: n  Edge: n * (m *(m + n))
     * space = n^2 + n + n = O(n^2)
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // Write your solution here
        if (beginWord == null || endWord == null) {
            return 0;
        }

        if (beginWord.equals(endWord)) {
            return 1;
        }

        Map<String, List<String>> wordMap = new HashMap<>();
        for (String word : wordList) {
            StringBuilder sb = new StringBuilder(word);
            for (int i = 0; i < word.length(); i++) {
                sb.setCharAt(i, '*');
                String s = sb.toString();
                List<String> words = wordMap.getOrDefault(s, new ArrayList<>());
                words.add(word);
                wordMap.put(s, words);
                sb.setCharAt(i, word.charAt(i));
            }
        }

        Map<String, Integer> wordToStep = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        wordToStep.put(beginWord, 1);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int step = wordToStep.get(word);
            StringBuilder sb = new StringBuilder(word);

            for (int i = 0; i < word.length(); i++) {
                sb.setCharAt(i, '*');
                String s = sb.toString();
                for (String nei : wordMap.getOrDefault(s, new ArrayList<>())) {
                    if (nei.equals(endWord)) {
                        return step + 1;
                    }
                    if (!wordToStep.containsKey(nei)) {
                        queue.offer(nei);
                        wordToStep.put(nei, step + 1);
                    }
                }
                sb.setCharAt(i, word.charAt(i));
            }
        }

        return 0;
    }
}
