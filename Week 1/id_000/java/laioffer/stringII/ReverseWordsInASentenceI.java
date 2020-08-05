package laioffer.stringII;

public class ReverseWordsInASentenceI {

    public static void main(String[] args) {
        System.out.println(new ReverseWordsInASentenceI().reverseWords("AC B C D"));
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
    public String reverseWords(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();

        reverse(array, 0, array.length - 1);

        /*
          需要考虑的情况
          1.头部(有/没有)空格
          2.尾部(有/没有)空格
          3.单词之间的空格数不止一个
         */
        int start = 0;
        //头尾无空格的写法
        //for (int i = 0; i < array.length; i++) {
        //    if (array[i] == ' ' && i > 0) {
        //        reverse(array, start, i - 1);
        //        start = i + 1;
        //    }

        //    if (i == array.length - 1) {
        //        reverse(array, start, i);
        //    }
        //}

        // 头尾有空格的写法
        for (int i = 0; i < array.length; i++) {
            // 当i不是空格，并且当前位置为0或者上一个位置为空格的时候，将start指向i
            // 表示这个位置是一个单词的起始位置
            if (array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) {
                start = i;
            }

            // 当i不是空格，并且当前位置为最后一个位置或者下一个位置为空格的时候
            // 翻转start～i，表示这个位置是一个单词的结束位置
            if (array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) {
                reverse(array, start, i);
            }
        }

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
