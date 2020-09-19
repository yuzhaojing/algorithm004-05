package laioffer.BFSII;

import java.util.*;

public class WordLadderII {

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
        System.out.println(new WordLadderII().findLadders1("red", "tax", wordList));
    }

    // input:  String beginWord
    //         String endWord
    //         List<String> wordList
    // output: List<List<String>>
    // Assume: wordList内所有单词长度一致，并且都为小写。并且wordList中不能有重复元素

    // high level: 使用BFS1进行解答
    // detail level: 以beginWord作为起点，尝试generation每一个有可能的neighbor，然后查看是否能够存在wordList中
    //               然后进行层序遍历，记录每个node是从哪些node访问过来的，直到有一层遍历到endWord这个node结束遍历
    //               然后以endWord这个node为起点，对整个node访问记录进行dfs
    //
    // 假设wordList中一共有n个单词，每个单词的长度为m
    // time =
    // BFS = V + E = n + n * 25m^2
    // vertex: n个  edge: n * 25m
    // DFS = ???
    // 最短路径是多少，就有多少层。每层存储endWord这个node到root的路径 n
    // 对于每个endWord途径的node，有几个node访问过来，就有几个case n
    //
    // space = ???
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //write your code here
        List<List<String>> res = new ArrayList<>();
        if (beginWord == null || endWord == null) {
            return res;
        }

        if (beginWord.equals(endWord)) {
            res.add(Arrays.asList(beginWord, endWord));
            return res;
        }

        Set<String> dict = new HashSet<>(wordList);
        Map<String, List<String>> prev = new HashMap<>();
        Map<String, Integer> levels = new HashMap<>();

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        prev.put(beginWord, new ArrayList<>());
        int level = 0;
        levels.put(beginWord, level);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> neighbors = getNeighbors(cur, dict);
                for (String nei : neighbors) {
                    if (!levels.containsKey(nei)) {
                        prev.put(nei, new ArrayList<>());
                        queue.offer(nei);
                        levels.put(nei, level + 1);
                    }

                    if (levels.get(nei) == level + 1) {
                        prev.get(nei).add(cur);
                    }
                }
            }

            if (prev.containsKey(endWord)) {
                break;
            }
            level++;
        }

        if (!prev.containsKey(endWord)) {
            return res;
        }

        dfs(prev, endWord, beginWord, new ArrayList<>(), res);
        return res;
    }

    private void dfs(Map<String, List<String>> prev, String targetWord, String beginWord,
                     List<String> cur, List<List<String>> res) {
        if (prev.get(targetWord).isEmpty()) {
            if (targetWord.equals(beginWord)) {
                cur.add(targetWord);
                List<String> tmp = new ArrayList<>(cur);
                Collections.reverse(tmp);
                res.add(tmp);
                cur.remove(cur.size() - 1);
            }
            return;
        }

        cur.add(targetWord);
        for (String word : prev.get(targetWord)) {
            dfs(prev, word, beginWord, cur, res);
        }
        cur.remove(cur.size() - 1);
    }

    private List<String> getNeighbors(String word, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            char origin = word.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == origin) {
                    continue;
                }

                sb.setCharAt(i, c);
                if (dict.contains(sb.toString())) {
                    neighbors.add(sb.toString());
                }
            }
            sb.setCharAt(i, origin);
        }

        return neighbors;
    }


    // input:  String beginWord
    //         String endWord
    //         List<String> wordList
    // output: List<List<String>>
    // Assume: wordList内所有单词长度一致，并且都为小写。并且wordList中不能有重复元素

    // high level: 使用BFS1和DFS进行解答
    // detail level: 在word ladder I的基础上，修改以下wordToStep的value，存储从beginWord到这个word的最短路径的上一个word。
    //               进行BFS遍历的时候，一层一层的遍历，如果遍历到endWord的话，就不继续遍历下一层，因为不是最短距离了。
    //               之后再对进行DFS，找出所有路径
    //
    // 假设wordList中一共有n个单词，每个单词的长度为m
    // time =
    // 预处理: n*m^2   BFS: n + n*m^2   DFS:
    // 最短路径是多少，就有多少层。每层存储endWord这个node到root的路径 n
    // 对于每个endWord途径的node，有几个node访问过来，就有几个case n
    //
    // space = n^2 + n^2 + n = O(n^2)
    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        //write your code here
        List<List<String>> res = new ArrayList<>();
        if (beginWord == null || endWord == null) {
            return res;
        }

        if (beginWord.equals(endWord)) {
            res.add(Arrays.asList(beginWord, endWord));
            return res;
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

        Map<String, List<String>> prev = new HashMap<>();
        Map<String, Integer> levels = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        prev.put(beginWord, new ArrayList<>());
        int level = 1;
        levels.put(beginWord, level);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nei : getNeighbors(word, wordMap)) {
                    if (!levels.containsKey(nei)) {
                        queue.offer(nei);
                        prev.put(nei, new ArrayList<>());
                        levels.put(nei, level + 1);
                    }

                    if (levels.get(nei) == level + 1) {
                        prev.get(nei).add(word);
                    }
                }
            }

            if (levels.containsKey(endWord)) {
                break;
            }
            level++;
        }

        if (!levels.containsKey(endWord)) {
            return res;
        }

        dfs1(prev, beginWord, endWord, new ArrayList<>(), res);
        return res;
    }

    private void dfs1(Map<String, List<String>> prev, String targetWord, String endWord, List<String> cur, List<List<String>> res) {
        if (prev.get(endWord).isEmpty()) {
            if (targetWord.equals(endWord)) {
                cur.add(endWord);
                List<String> tmp = new ArrayList<>(cur);
                Collections.reverse(tmp);
                res.add(tmp);
                cur.remove(cur.size() - 1);
            }

            return;
        }

        cur.add(endWord);
        for (String word : prev.get(endWord)) {
            dfs1(prev, targetWord, word, cur, res);
        }
        cur.remove(cur.size() - 1);
    }

    private List<String> getNeighbors(String word, Map<String, List<String>> wordMap) {
        List<String> neighbors = new ArrayList<>();
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            sb.setCharAt(i, '*');
            String s = sb.toString();
            for (String nei : wordMap.getOrDefault(s, new ArrayList<>())) {
                neighbors.add(nei);
            }
            sb.setCharAt(i, word.charAt(i));
        }

        return neighbors;
    }
}
