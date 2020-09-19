package laioffer.Trie;

import java.util.HashMap;
import java.util.Map;

// 字典树或者前缀树
public class TrieNode {
    private int value;
    private int count; // 记录children中还有几个word，在删除node的时候可以更加方便的删除无效的node
    private boolean isWord; // 记录当前node是word还是前缀
    private Map<Character, TrieNode> children; // 记录以当前节点为前缀的所有node，可以使用TrieNode[]来记录
    // 当children长度固定且密集的时候，使用TrieNode[]更好。因为array的overhead更小
    // 当children长度不固定或者较为稀疏的时候，使用Map更好，使用TrieNode[]需要存很多null

    public TrieNode() {
        children = new HashMap<>();
    }

    public int getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public boolean isWord() {
        return isWord;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }

        TrieNode cur = this;

        for (int i = 0; i < word.length(); i++) {
            TrieNode next = cur.children.get(word.charAt(i));
            if (next == null) {
                return false;
            }

            cur = next;
        }

        return cur.isWord;
    }

    public boolean insert(String word) {
        // 必须保证Trie中现在没有这个word，否则count计算会出错
        if (word == null || search(word)) {
            return false;
        }

        TrieNode cur = this;

        for (int i = 0; i < word.length(); i++) {
            TrieNode next = cur.children.get(word.charAt(i));
            if (next == null) {
                next = new TrieNode();
                cur.children.put(word.charAt(i), next);
            }

            // 只有这个word是新加的，才能一路加上count，因为底下必然会新加一个word
            cur.count++;
            cur = next;
        }

        cur.isWord = true;
        return true;
    }

    public boolean delete(String word) {
        // 必须保证Trie中现在有这个word，否则count计算会出错
        if (word == null || !search(word)) {
            return false;
        }

        TrieNode cur = this;

        for (int i = 0; i < word.length(); i++) {
            TrieNode next = cur.children.get(word.charAt(i));
            cur.count--;

            // 如果被删除node不是根节点，连带之前的无效node一起删除
            if (cur.count == 0) {
                cur.children.remove(word.charAt(i));
            }

            cur = next;
        }

        // 如果被删除node是根节点，那么只是将这个node标志为前缀
        cur.isWord = false;
        return true;
    }

    public TrieNode searchNode(String word) {
        if (word == null) {
            return null;
        }

        TrieNode cur = this;

        for (int i = 0; i < word.length(); i++) {
            TrieNode next = cur.children.get(word.charAt(i));
            if (next == null) {
                return null;
            }
            cur = next;
        }

        return cur;
    }
}
