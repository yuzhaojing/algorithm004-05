package laioffer.stringII;

public class RightShiftByNCharacters {

    public static void main(String[] args) {
        System.out.println(new RightShiftByNCharacters().rightShift("AC B C D", 2));
    }

    /**
     * 翻转单词顺序
     * 1.翻转整个string
     * 2.翻转每个单词
     *
     * time = O(n)
     * 第一遍翻转O(n),第二遍翻转O(n)
     *
     * space = O(n)
     * 如果传入char array则为 O(1)
     */
    public String rightShift(String input, int n) {
        // Write your solution here
        // n == 0的时候，返回原字符串即可
        if (input == null || input.length() == 0 || n <= 0) {
            return input;
        }

        char[] array = input.toCharArray();
        int rotate = n % array.length;
        reverse(array, 0, array.length);
        reverse(array, 0, rotate - 1);
        reverse(array, rotate, array.length);
        return new String(array);
    }

    private void reverse(char[] array, int left, int right) {
        while (left < right) {
            swap(array, left++, right--);
        }
    }

    private void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
