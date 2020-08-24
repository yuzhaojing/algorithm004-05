package laioffer.Exam.mid;

public class PrintAllPossibleIfBlocks {

    public static void main(String[] args) {
        new PrintAllPossibleIfBlocks().printAllPossibleIfBlocks(3);
    }

    /**
     * 假设：n > 0
     * 如果 n <= 0,没有有效解，可以与面试官商量返回值，暂定null
     *
     * high level: 使用DFS来进行解题
     * mid level: 本质上是生成有效括号
     *  1、根据给定的n，生成有效的大括号对
     *  2、遍历生成的大括号，将其改写成 if {}的样子
     *
     * time = O(2^n * n)
     * 生成有效的括号，每层最多2种可能性，一共n层 time = O(2^n)
     * 在每个叶子节点，需要花费O(n)的时间将大括号改写成if语句
     *
     * space = O(n) call stack
     */
    public void printAllPossibleIfBlocks(int n) {
        if (n <= 0) {
            return;
        }

        DFS(n, 0, 0, new StringBuilder());
    }

    private void DFS(int n, int left, int right, StringBuilder cur) {
        if (left == n && right == n) {
            printIfBlocks(cur);
            return;
        }

        if (left < n) {
            cur.append("{");
            DFS(n, left + 1, right, cur);
            cur.deleteCharAt(cur.length() - 1);
        }

        if (left > right) {
            cur.append("}");
            DFS(n, left, right + 1, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    private void printIfBlocks(StringBuilder sb) {
        int spaces = 0;
        for (int i = 0; i < sb.length(); i++) {
            StringBuilder cur = new StringBuilder();
            char c = sb.charAt(i);
            if (c == '{') {
                for (int j = 0; j < spaces; j++) {
                    cur.append(" ");
                }
                cur.append("if {\n");
                spaces += 2;
            } else {
                spaces -= 2;
                for (int j = 0; j < spaces; j++) {
                    cur.append(" ");
                }
                cur.append("}\n");
            }
            System.out.println(cur.toString());
        }
    }

}
