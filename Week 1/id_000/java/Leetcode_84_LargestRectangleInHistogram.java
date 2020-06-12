import java.util.Stack;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_84_LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights = {6, 7, 5, 5, 2, 4, 5, 9, 3};
        System.out.println(method1(heights));
    }

    private static int method1(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }

        return maxArea;
    }

}
