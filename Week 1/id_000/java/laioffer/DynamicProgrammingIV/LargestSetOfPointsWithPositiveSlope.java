package laioffer.DynamicProgrammingIV;

import java.util.Arrays;
import java.util.Comparator;

public class LargestSetOfPointsWithPositiveSlope {

    /**
     * input:  Point[] points
     * output: int
     * Assume: points != null && points.length > 1
     *
     * high level: 使用一维DP，linear scan回头看解答
     * detail level:
     *  1、分解题意：求斜率为正，即(y2 - y1) / (x2 - x1) > 0
     *     所以当x2 - x1 > 0时，y2 - y1 >0，当x1 - x2 > 0时，y1 - y2 >0
     *     即x1与x2的大小关系确定了y1与y2的大小关系
     *     所以求斜率为正的点的最大集合，实际上是求将x排序后，y的最大上升子序列
     *  2、将points根据x从小到大排序
     *  3、lowestEnding[i]表示长度为i的最优子序列
     *     base case: lowestEnding[1] = array[0];
     *     通过binary search找到比array[i]小的最大值，lowestEnding[index + 1] = array[i]
     *  4、注意：排序需要按x升序，按y降序。因为当x相等的时候，斜率为0不满足题意。如果y不降序排列，可以会统计错误
     *          如果maxLen < 2，则说明没有集合满足，返回0
     *
     * time = nlogn + nlogn = O(nlogn)
     * 排序points nlogn
     * DP + binary nlogn
     *
     * space = O(n)
     * 排序 logn或n
     * 最优子序列 n
     */
    public int largest(Point[] points) {
        // Write your solution here.
        if (points == null || points.length < 2) {
            return 0;
        }

        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.x == p2.x) {
                    if (p1.y == p2.y) {
                        return 0;
                    }

                    return p1.y < p2.y ? 1 : -1;
                }

                return p1.x < p2.x ? -1 : 1;
            }
        });

        int[] lowestEnding = new int[points.length + 1];
        lowestEnding[1] = points[0].y;

        int maxLen = 1;

        for (int i = 1; i < points.length; i++) {
            int index = smallerLargest(lowestEnding, 1, maxLen, points[i].y);
            lowestEnding[index + 1] = points[i].y;
            if (index == maxLen) {
                maxLen++;
            }
        }

        return maxLen < 2 ? 0 : maxLen;
    }

    private int smallerLargest(int[] array, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        if (array[right] < target) {
            return right;
        }

        if (array[left] < target) {
            return left;
        }

        return 0;
    }

    class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
