package laioffer.CrossTrainingI;

import java.util.Arrays;

public class RemoveAdjacentRepeatedCharactersI {

    public static void main(String[] args) {

    }

    /**
     * input: input string
     * output: string
     * 假设：input != null && input.length > 1
     * 如果不符合假设，那么没有需要删除的元素，直接返回input string
     *
     * high level: 使用左右挡板，同向而行解答
     * mid level: 定义双指针slow，fast
     *  1、slow: 在[0，slow - 1]的区间内，都是需要返回的结果
     *  2、fast: fast指针指向的元素就是当前处理的元素
     *  3、init: slow = 1, fast = 1 (第一个元素必然返回，符合物理意义)
     *  4、if (input[fast] == input[slow - 1]) fast++
     *     else                                input[slow++] = input[fast++]
     *
     * time = O(n)
     * space = O(n)
     */
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() < 2) {
            return input;
        }

        char[] array = input.toCharArray();

        int slow = 1;
        int fast = 1;

        while (fast < array.length) {
            if (array[slow - 1] == array[fast]) {
                fast++;
            } else {
                array[slow++] = array[fast++];
            }
        }

        return new String(array, 0, slow);
    }
}
