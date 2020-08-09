package laioffer.recursionII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {

    public static void main(String[] args) {
        System.out.println(new NQueens().nqueens(8));
    }

    /**
     *   1  2  3  4
     * 1 0  -1 -2 -3
     * 2 1  0  -1 -2
     * 3 2  1  0  -1
     * 4 3  2  1  0
     * 可见对角线的差为常数
     *   1  2  3  4
     * 1 2  3  4  5
     * 2 3  4  5  6
     * 3 4  5  6  7
     * 4 5  6  7  8
     * 可见反对角线的和为常数
     *
     */
    public List<List<Integer>> nqueens(int n) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }

        Set<Integer> usedColumns = new HashSet<>(); // 已经被使用的列
        Set<Integer> usedDiagonals = new HashSet<>(); // 已经被使用的对角线
        Set<Integer> usedRevDiagonals = new HashSet<>(); // 已经被使用的反对角线

        nqueens(0, n, new ArrayList<Integer>(), usedColumns, usedDiagonals, usedRevDiagonals, res);
        return res;
    }

    private void nqueens(int row, int levels, List<Integer> cur, Set<Integer> usedColumns, Set<Integer> usedDiagonals, Set<Integer> usedRevDiagonals, List<List<Integer>> res) {
        // base case
        // 当row等于levels的时候，说明前面n层都已经有效了，将结果加入res中并返回
        if (row == levels) {
            res.add(new ArrayList<>(cur));
            return;
        }

        // 遍历每一个col，判断是否有效
        for (int i = 0; i < levels; i++) {
            if (vaild(row, i, usedColumns, usedDiagonals, usedRevDiagonals)) {
                // 如果该col是有效的，将col加入cur中
                // 然后将该col影响到的值放入三个set中
                cur.add(i);
                mark(row, i, usedColumns, usedDiagonals, usedRevDiagonals);

                // 递归到下一层
                nqueens(row + 1, levels, cur, usedColumns, usedDiagonals, usedRevDiagonals, res);

                // 回溯状态，避免影响下一次遍历
                cur.remove(cur.size() - 1);
                unMark(row, i, usedColumns, usedDiagonals, usedRevDiagonals);
            }
        }
    }

    // 只有当三个set中都没有对这个row和col产生影响的时候，才判断是有效的
    private boolean vaild(int row, int col, Set<Integer> usedColumns, Set<Integer> usedDiagonals, Set<Integer> usedRevDiagonals) {
        return !usedColumns.contains(col) && !usedDiagonals.contains(row - col) && !usedRevDiagonals.contains(row + col);
    }

    // 在n*n的矩阵中，对角线的差为常数，所以只要记录这一个常数的值，就可以避免整个对角线的元素再次加入
    // 在n*n的矩阵中，反对角线的和为常数，所以只要记录这一个常数的值，就可以避免整个反对角线的元素再次加入
    private void mark(int row, int col, Set<Integer> usedColumns, Set<Integer> usedDiagonals, Set<Integer> usedRevDiagonals) {
        usedColumns.add(col);
        usedDiagonals.add(row - col);
        usedRevDiagonals.add(row + col);
    }

    private void unMark(int row, int col, Set<Integer> usedColumns, Set<Integer> usedDiagonals, Set<Integer> usedRevDiagonals) {
        usedColumns.remove(col);
        usedDiagonals.remove(row - col);
        usedRevDiagonals.remove(row + col);
    }
}
