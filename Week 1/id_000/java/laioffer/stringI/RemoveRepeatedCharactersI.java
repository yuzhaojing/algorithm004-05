package laioffer.stringI;

public class RemoveRepeatedCharactersI {

    public static void main(String[] args) {
        System.out.println(new RemoveRepeatedCharactersI().deDup("abcd"));
    }

    /**
     * 使用双指针的方法求解
     * 1.定义双指针slow、fast
     * slow: 在slow的左边不包含slow，全部都为需要返回的元素
     * fast: 每轮循环走一步，直到最后数组最后一位
     * 2.判断fast指针和slow之前之前的值是否相等
     * 3.如果相等，则说明是重复的，fast往下走一步
     * 4.如果不相等，将fast的值赋值给slow，然后各走一步
     * <p>
     * time = O(2n) = O(n)
     * <p>
     * space = O(1)
     */
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();
        int slow = 1;
        int fast = 1;

        while (fast < array.length) {
            if (array[fast] != array[slow - 1]) {
                array[slow++] = array[fast];
            }
            fast++;
        }

        return new String(array, 0, slow);
    }
}
