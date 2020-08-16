package laioffer.DynamicProgrammingII;

public class EditDistance {

    public static void main(String[] args) {
        System.out.println(new EditDistance().editDistanceDp("sigh", "asith"));
    }

    /**
     * 使用DP的方式解答
     * base case:
     *  建立一个二维dp数组，长度比输入字符串长度大1
     *  在横轴或纵轴角标为0的位置上，初始化对应的需要修改次数
     * induction rule:
     *  one.charAt(i - 1) == two.charAt(j - 1) M[i][j] = M[i - 1][j - 1]
     *  说明此时两个字符串的对应字符相等，可以不用修改，直接进行下一对字符的比较
     *  else M[i][j] = 1 + Math.min(Math.min(M[i - 1][j], M[i][j - 1]), M[i - 1][j - 1])
     *  次数字符不相等，则需要进行一次操作，然后看哪次操作更加快速
     *
     *  二维数组的填写顺序必须从上到下，从左到右
     *  因为M[i][j] 依赖于 M[i - 1][j]、M[i][j - 1]、M[i - 1][j - 1]
     *  而这三个元素都位于M[i][j]的左方或者上方
     */
    public int editDistanceDp(String one, String two) {
        // Write your solution here
        if (one == null || two == null) {
            return -1;
        }

        // 创建二维dp数组，用于存储对应长度时，需要修改的最少次数
        // 由于字符串有出现空串的可能性，所以多建了一行一列
        int[][] M = new int[one.length() + 1][two.length() + 1];

        // i的物理意义为one的长度
        for (int i = 0; i < M.length; i++) {
            // j的物理意义为two的长度
            for (int j = 0; j < M[0].length; j++) {
                if (i == 0) {
                    // base case
                    // 当one的长度为0时，two的长度为多少就要修改多少次
                    M[i][j] = j;
                } else if (j == 0) {
                    // base case
                    // 同理，当two的长度为0时，one的长度为多少就要修改多少次
                    M[i][j] = i;
                } else if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    // 此时两个字符串的比较与两个字符串将这个字符去掉之后的操作次数相同
                    M[i][j] = M[i - 1][j - 1];
                } else {
                    M[i][j] = 1 + Math.min(Math.min(M[i - 1][j], M[i][j - 1]), M[i - 1][j - 1]);
                }
            }
        }

        return M[one.length()][two.length()];
    }

    /**
     * 使用DFS的方式解答
     * base case:
     *  当one或者two其中一个char array的index到达数组最后一个元素的时候
     *  返回另一个char array的index到数组最后一个元素的长度，即还需要需改的次数
     *  由于此index为还未进行匹配的index，所以长度为
     *  (array.length - 1) - index + 1 (最后一个元素的角标减去当前角标加一)
     *  = array.length - index
     * recursion rule:
     *  修改一个单词有3种方式，修改，增加，删除
     *  replace: (0或1) + recursion call (oneIndex + 1, twoIndex + 1)
     *  delete: 1 + recursion call (oneIndex + 1, twoIndex)
     *  insert: 1 + recursion call (oneIndex, twoIndex + 1)
     * <p>
     * time = O(3^(n+m))
     * 假设one的长度为n，two的长度为m
     * 则recution tree共有n + m层
     * 每层分三个叉(即最多三种情况)
     * time = 3^0 + 3^1 + ... + 3^(n+m) = O(3^(n+m))
     * <p>
     * space = O(n + m) = O(height)
     */
    public int editDistanceDFS(String one, String two) {
        // Write your solution here
        // corner case
        if (one == null || two == null) {
            return -1;
        }

        // base case
        if (one.length() == 0) {
            return two.length();
        }

        if (two.length() == 0) {
            return one.length();
        }

        return editDistance(one.toCharArray(), 0, two.toCharArray(), 0);
    }

    private int editDistance(char[] one, int oneIndex, char[] two, int twoIndex) {
        if (oneIndex == one.length) {
            return two.length - twoIndex;
        }

        if (twoIndex == two.length) {
            return one.length - oneIndex;
        }

        int replace = (one[oneIndex] == two[twoIndex] ? 0 : 1) + editDistance(one, oneIndex + 1, two, twoIndex + 1);

        int delete = 1 + editDistance(one, oneIndex + 1, two, twoIndex);

        int insert = 1 + editDistance(one, oneIndex, two, twoIndex + 1);

        return Math.min(Math.min(replace, delete), insert);
    }
}
