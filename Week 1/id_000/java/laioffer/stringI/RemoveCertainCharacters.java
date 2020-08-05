package laioffer.stringI;

import java.util.HashSet;
import java.util.Set;

public class RemoveCertainCharacters {

    public static void main(String[] args) {
        System.out.println(new RemoveCertainCharacters().remove("abcd", "ab"));
    }

    /**
     * 使用双指针的方法求解
     * 1.将需要删除的string拆分成char array，然后添加到hashSet里
     * 2.定义双指针slow、fast
     *   slow: 在slow的左边不包含slow，全部都为需要返回的元素
     *   fast: 每轮循环走一步，直到最后数组最后一位
     * 3.只要fast指针还没到最后一位，则每次比较fast指针所对应元素是否是需要删除的元素
     * 4.如果不是需要删除的元素，则将该值赋值给slow，然后slow和fast各自前进一步
     * 5.如果是需要删除的元素，则不用处理
     * 6.返回0～slow-1角标的元素
     *
     * time = O(n + m)
     *
     * space = O(m)
     */
    public String remove(String input, String t) {
        // Write your solution here
        if (input == null || input.length() == 0 || t == null || t.length() == 0) {
            return input;
        }

        Set<Character> set = new HashSet<>();
        for (char c : t.toCharArray()) {
            set.add(c);
        }

        char[] array = input.toCharArray();
        int slow = 0;
        for (int i = 0; i < array.length; i++) {
            if (!set.contains(array[i])) {
                array[slow++] = array[i];
            }
        }

        return new String(array, 0, slow);
    }
}
