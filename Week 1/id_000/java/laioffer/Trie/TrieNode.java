package laioffer.Trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树/前缀树是一颗k叉树，每层存储的是前缀字符
 * 主要应用场景为：对字符串的查找，前缀匹配
 *
 * 假设给定n个string，平均每个string长度为m，比较以下几种数据结构的复杂度
 * search: 找到指定string
 * insert: 在指定为止插入string，例如"b"需要插入在"a"和"c"之间
 * delete: 删除指定string
 *
 *                      search        insert        delete
 * HashMap              O(m)          O(m)          O(m)         需要O(m)计算hashCode，worst case O(nm) 哈希冲突
 * ArrayList            O(nm)         O(nm + n)     O(nm + n)    需要O(n)遍历元素，O(m)的时间比较元素，删除还需要O(n)的时间移动元素
 * ArrayList(sorted)    O(mlogn)      O(mlogn + n)  O(mlogn + n) 需要O(logn)遍历元素，O(m)的时间比较元素，删除还需要O(n)的时间移动元素
 * LinkedList           O(nm)         O(nm)         O(nm)        需要O(n)遍历元素，O(m)的时间比较元素
 * Trie                 O(m)          O(m)          O(m)         需要O(m)找到对应的node
 */
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
        // 如果找到这个word，直接返回false。
        // 这个判断是服务于count的，如果事先不知道word是否存在，无法在遍历的时候对count进行操作
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
            // 由于这个word必然存在，所以next必然存在
            TrieNode next = cur.children.get(word.charAt(i));
            cur.count--;

            // 表示在cur这个node之下只有一个node，这个node就是需要删除的node
            // 所以不需要再继续遍历寻找，直接删除这个node后的children就可以
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
