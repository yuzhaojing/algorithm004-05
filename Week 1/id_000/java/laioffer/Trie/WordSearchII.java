package laioffer.Trie;

import java.util.*;

public class WordSearchII {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'b','e','a','b'},{'b','b','c','b'},{'c','a','d','c'},{'e','d','b','b'},{'e','d','d','d'}};
        String[] words = {"eadb","acbace","beab","c"};
        System.out.println(new WordSearchII().findWords(board, words));
    }

    /**
     * input:  char[][] board
     *         String[] words
     * output: List<String>
     * Assume: board != null && board.length > 0 && board[0].length > 0 && words != null && words.length > 0
     * 如果不符合假设，那么没有符合要求的解，返回空list
     *
     * high level: 使用Trie + DFS进行解答
     * detail level:
     *  1、遍历所有的word，建立Trie
     *  2、遍历board，以任意一个点作为起点，DFS遍历整个board。
     *  3、将所有path去Trie中search，如果走到了isWord = true的node，将这个path加入结果集
     *  4、base case: root.isword() res.add(cur.roString())
     *     recursion rule: 遍历周边四个节点
     *
     * 假设：有k个word，board的大小为m*n，平均每个word长度为l
     * time = O(kl + mn * 4^l)
     * for word in words O(k)
     *   root.insert(word) O(l)
     *
     * for i in rows   O(m)
     *   for j in cols O(n)
     *     DFS(...)    O(4^l)
     * space = l + m * n * k = O(mnk + l)
     * visited O(mn * k)
     * dfs call stack O(l)
     */
    public List<String> findWords(char[][] board, String[] words) {
        // Write your solution here
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return new ArrayList<>();
        }

        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[board.length][board[0].length];

        Set<String> dict = new HashSet<>();
        for (String word : words) {
            dict.add(word);
        }

        TrieNode root = buildTrie(dict);

        Set<String> res = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                helper(root, board, visited, dirs, i, j, new StringBuilder(), res);
            }
        }

        return new ArrayList<>(res);
    }

    private void helper(TrieNode root, char[][] board, boolean[][] visited, int[][] dirs, int x, int y, StringBuilder cur, Set<String> res) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
            return;
        }

        TrieNode next = root.children.get(board[x][y]);
        if (next == null) {
            return;
        }

        cur.append(board[x][y]);
        visited[x][y] = true;
        if (next.isWord) {
            res.add(cur.toString());
        }

        root = next;
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            helper(root, board, visited, dirs, nx, ny, cur, res);
        }

        visited[x][y] = false;
        cur.deleteCharAt(cur.length() - 1);
    }

    private TrieNode buildTrie(Set<String> dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            root.insert(word);
        }
        return root;
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            this.children = new HashMap<>();
        }

        public boolean insert(String word) {
            if (word == null) {
                return false;
            }

            TrieNode cur = this;

            for (int i = 0; i < word.length(); i++) {
                TrieNode next = cur.children.get(word.charAt(i));
                if (next == null) {
                    next = new TrieNode();
                    cur.children.put(word.charAt(i), next);
                }

                cur = next;
            }

            cur.isWord = true;
            return true;
        }
    }

    /**
     * input:  char[][] board
     * String[] words
     * output: List<String>
     * Assume: board != null && board.length > 0 && board[0].length > 0 && words != null && words.length > 0
     * 如果不符合假设，那么没有符合要求的解，返回空list
     * <p>
     * high level: 使用DFS进行解答
     * detail level:
     * 1、遍历所有的words，对每一个word进行解决
     * 2、遍历board，以任意一个点作为起点，DFS遍历整个board
     * 3、找到word之后直接返回，将这个word加入结果集
     * 4、base case: index == word.length()
     * recursion rule: 遍历周边四个节点
     * <p>
     * 假设：有k个word，board的大小为m*n，平均每个word长度为l
     * time = O(kmn * 4^l)
     * for word in words O(k)
     * for i in rows   O(m)
     * for j in cols O(n)
     * DFS(...)    O(4^l)
     * space = l + m * n * k = O(mnk + l)
     * visited O(mnk)
     * dfs call stack O(l)
     */
    public List<String> findWords1(char[][] board, String[] words) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return res;
        }

        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Set<String> dict = new HashSet<>();
        for (String word : words) {
            dict.add(word);
        }

        for (String word : dict) {
            if (exists(word, board, dirs)) {
                res.add(word);
            }
        }

        return res;
    }

    private boolean exists(String targetWord, char[][] board, int[][] dirs) {
        // 因为底下有early return，所以visited内还有残留状态，需要每次新建一个
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper(board, dirs, visited, i, j, 0, targetWord)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean helper(char[][] board, int[][] dirs, boolean[][] visited, int x, int y, int index, String targetWord) {
        if (index == targetWord.length()) {
            return true;
        }

        // 在generation之前确定当前元素是否有效
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y] || targetWord.charAt(index) != board[x][y]) {
            return false;
        }
        visited[x][y] = true;

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            // 如果提前返回了，那么visited状态就没有清除
            if (helper(board, dirs, visited, nx, ny, index + 1, targetWord)) {
                return true;
            }

        }

        visited[x][y] = false;
        return false;
    }
}
