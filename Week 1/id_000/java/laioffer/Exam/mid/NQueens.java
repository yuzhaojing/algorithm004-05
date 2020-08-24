package laioffer.Exam.mid;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {

    public static void main(String[] args) {
        System.out.println(new NQueens().nqueens(1));
    }

    /**
     * 假设：n > 0
     * 如果 n <= 0,返回0。因为在n <= 0的情况下不可能有解
     * <p>
     * high level：使用深度优先遍历解决问题
     * mid level：由于同层皇后可以互相攻击，所以每层放一个皇后。
     * 然后在下一层与之前放置皇后互相无法攻击的地方放皇后
     * 无法攻击，指与之前皇后不在同一列，而且斜率也不相同
     * <p>
     * 第一层最多有n	 种选择
     * 第二层最多有n - 1种选择
     * 。。。
     * 第n层最多有1。   种选择
     * <p>
     * time = O(N!)
     * <p>
     * space = O(N)
     * 使用了三个HashSet，并且recursion tree的深度为n
     * head = O(3N)
     * stack = O(N)
     */
    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }

        Set<Integer> usedColumns = new HashSet<>();
        Set<Integer> usedDiagonal = new HashSet<>();
        Set<Integer> usedRevDiagonal = new HashSet<>();

        nqueens(n, 0, usedColumns, usedDiagonal, usedRevDiagonal, new ArrayList<>(), res);
        return res;
    }

    private void nqueens(int n, int row, Set<Integer> usedColumns, Set<Integer> usedDiagonal,
                         Set<Integer> usedRevDiagonal, List<Integer> cur, List<List<Integer>> res) {
        if (row == n) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (vaild(row, i, usedColumns, usedDiagonal, usedRevDiagonal)) {
                mark(row, i, usedColumns, usedDiagonal, usedRevDiagonal);
                cur.add(i);
                nqueens(n, row + 1, usedColumns, usedDiagonal, usedRevDiagonal, new ArrayList<>(), res);
                unMark(row, i, usedColumns, usedDiagonal, usedRevDiagonal);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean vaild(int row, int col, Set<Integer> usedColumns,
                       Set<Integer> usedDiagonal, Set<Integer> usedRevDiagonal) {
        return !usedColumns.contains(col) && !usedDiagonal.contains(row - col) && !usedRevDiagonal.contains(row + col);
    }

    private void mark(int row, int col, Set<Integer> usedColumns,
                      Set<Integer> usedDiagonal, Set<Integer> usedRevDiagonal) {
        usedColumns.add(col);
        usedDiagonal.add(row - col);
        usedRevDiagonal.add(row + col);
    }

    private void unMark(int row, int col, Set<Integer> usedColumns,
                        Set<Integer> usedDiagonal, Set<Integer> usedRevDiagonal) {
        usedColumns.remove(col);
        usedDiagonal.remove(row - col);
        usedRevDiagonal.remove(row + col);
    }
}
