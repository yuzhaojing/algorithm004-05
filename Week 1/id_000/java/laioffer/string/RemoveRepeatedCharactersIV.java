package laioffer.string;

public class RemoveRepeatedCharactersIV {

    public static void main(String[] args) {
        System.out.println(new RemoveRepeatedCharactersIV().deDup("aababab"));
    }

    /**
     * 使用双指针的方法求解
     * 1.定义双指针slow、fast，使用slow指针模拟栈
     *   slow: 栈顶元素，初始值为-1，因为刚开始没有元素入栈
     *   fast: 每轮循环走一步，直到最后数组最后一位
     * 2.如果slow为-1，代表栈内没有元素，直接将fast对应的值赋值给slow的下一位，然后各走一步
     * 3.如果slow对应的值等于fast对应的值，代表是重复元素。
     *   此时fast指针一直走到值不等于slow的位置，然后slow回退一步，代表删除栈顶元素
     * 4.如果slow对应的值不等于fast对应的值，代表该元素不是重复元素，
     *   直接将fast对应的值赋值给slow的下一位，然后各走一步
     * 5.最后返回0～slow+1，因为slow是栈顶
     *
     * time = O(2n) = O(n)
     *
     * space = O(1)
     */
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();
        int slow = -1;
        int fast = 0;

        while (fast < array.length) {
            if (slow == -1) {
                array[++slow] = array[fast++];
            } else if (array[slow] == array[fast]) {
                while (fast < array.length && array[slow] == array[fast]) {
                    fast++;
                }
                slow--;
            } else {
                array[++slow] = array[fast++];
            }
        }

        return new String(array, 0, slow + 1);
    }
}
