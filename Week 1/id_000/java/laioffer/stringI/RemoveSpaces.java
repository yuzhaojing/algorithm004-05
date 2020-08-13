package laioffer.stringI;

public class RemoveSpaces {

    public static void main(String[] args) {
        System.out.println(new RemoveSpaces().removeSpaces("abcd"));
    }

    /**
     * 使用双指针的方法求解
     * 1.定义双指针slow、fast
     *   slow: 在slow的左边不包含slow，全部都为需要返回的元素
     *   fast: 每轮循环走一步，直到最后数组最后一位
     * 3.判断fast指针对应的值是否为空格
     * 4.如果是空格，当前指针指向0或者前一个值也是空格的时候直接忽略
     * 5.否则将fast对应的值赋值给slow，并且各走一步
     * 6.最后检查一下slow的前一位是不是空格，是的话将slow回退一步
     *
     * time = O(2n) = O(n)
     *
     * space = O(1)
     */
    public String removeSpaces(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;

        while (fast < array.length) {
            // 排除反例，将不用copy的情况列出来
            // 当前指针指向空格的时候，如果该指针指向0或者=该指针的前一位是空格，那么不copy
            if (array[fast] == ' ' && (fast == 0 || array[fast - 1] == ' ')) {
                fast++;
            } else {
                array[slow++] = array[fast++];
            }
        }

        // 确认有元素并且对最后一个元素进行处理，如果为空格就删除
        if (slow > 0 && array[slow - 1] == ' ') {
            slow--;
        }

        return new String(array, 0, slow);
    }
}
