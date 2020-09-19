package laioffer.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrefixRelatedOperations {

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        root.insert("at");
        root.insert("apple");
        root.insert("app");
        root.insert("cap");
        root.insert("cathy");
        root.insert("dog");
        root.insert("cat");

//        System.out.println(new PrefixRelatedOperations().findAllWordsByPrefix(root, "ca"));
        System.out.println(new PrefixRelatedOperations().findAllMatchWords(root, "??p"));
    }

    /**
     * 1、给定一个string作为prefix，找出符合条件的所有word
     *
     * example:
     *  input: "ca"
     *  output: ["cap", "cat", "cathy"]
     *
     * input:  String prefix
     *         TrieNode root
     * output: List<String>
     * Assume: root != null && prefix != null
     * 加入不符合假设，找不到符合条件的word，返回空list
     *
     * high level: 使用Trie + DFS解答
     * detail level:
     *  1、对prefix进行一次search，找到Trie中前缀等于prefix的node
     *  2、对找到的node进行DFS
     *
     * 假设: prefix.length() = n  Trie的最大分叉个数 = m  Trie的最大深度 = k
     * time = O(n + m^k)
     * 找到node  = O(n)
     * 进行DFS worst case: 一共k层，每层有m个叉
     *
     * space = k + k = O(k)
     * StringBuilder存储 O(k) (最多需要存Trie最大深度的数据)
     * DFS recursion    O(k) (最多需要递归Trie最大深度层)
     */
    public List<String> findAllWordsByPrefix(TrieNode root, String prefix) {
        List<String> res = new ArrayList<>();
        if (root == null || prefix == null) {
            return res;
        }

        TrieNode node = root.searchNode(prefix);
        helper(node, new StringBuilder(prefix), res);
        return res;
    }

    private void helper(TrieNode root, StringBuilder cur, List<String> res) {
        if (root.isWord()) {
            res.add(cur.toString());
        }

        if (root.getChildren().isEmpty()) {
            return;
        }

        for (Map.Entry<Character, TrieNode> entry : root.getChildren().entrySet()) {
            cur.append(entry.getKey());
            helper(entry.getValue(), cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 2、给定一个string作为匹配字符串，里面含有"?"可以代替任何字符。
     *    找到并返回符合要求的所有字符串
     *
     * example:
     *  input: "ca?"
     *  output: ["cap", "cat"]
     *
     *  input: "??p"
     *  output: ["app", "cap"]
     *
     * input: String regex
     *        TrieNode root
     * output: List<String>
     * Assume: regex != null && root != null
     * 如果不符合假设，返回空list
     *
     * high level: 使用Trie + DFS解答
     * detail level:
     *  1、遍历regex，到regex.length()这一层停下。
     *  2、base case: index == regex.length()
     *  3、recursion rule:
     *     if (regex.charAt(index) == '?') dfs(all children)
     *     else                            dfs(match children)
     */
    public List<String> findAllMatchWords(TrieNode root, String regex) {
        List<String> res = new ArrayList<>();
        if (regex == null || root == null) {
            return res;
        }

        helper(root, 0, regex, new StringBuilder(), res);
        return res;
    }

    private void helper(TrieNode root, int index, String regex, StringBuilder cur, List<String> res) {
        if (regex.length() == index) {
            if (root.isWord()) {
                res.add(cur.toString());
            }
            return;
        }

        char c = regex.charAt(index);
        if (c == '?') {
            for (Map.Entry<Character, TrieNode> entry : root.getChildren().entrySet()) {
                cur.append(entry.getKey());
                helper(entry.getValue(), index + 1, regex, cur, res);
                cur.deleteCharAt(cur.length() - 1);
            }
        } else {
            TrieNode next = root.getChildren().get(c);
            if (next != null) {
                cur.append(c);
                helper(root.getChildren().get(c), index + 1, regex, cur, res);
                cur.deleteCharAt(cur.length() - 1);
            }
        }
    }
}
