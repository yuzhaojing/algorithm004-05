package laioffer.stringII;

public class ReverseString {

    public static void main(String[] args) {
        System.out.println(new ReverseString().reverse("abcd"));
    }

    /**
     * 迭代
     *
     * time = O(n)
     *
     * space = O(n)
     * 如果输入为char array，则space为O(1)
     */
    public String reverse(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();
        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            swap(array, left++, right--);
        }

        return new String(array);
    }

    /**
     * 递归
     *
     * time = O(n)
     *
     * space = O(n)
     * 如果输入为char array，则space为O(n/2) = O(n)
     */
    public String reverse1(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();
        reverse(array, 0, input.length() - 1);
        return new String(array);
    }

    private void reverse(char[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        reverse(array, left + 1, right - 1);
        swap(array, left, right);
    }

    private void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
