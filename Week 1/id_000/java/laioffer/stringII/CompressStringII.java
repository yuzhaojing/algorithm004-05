package laioffer.stringII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CompressStringII {

    public static void main(String[] args) {
        char[] c = {'a'};
        c[0] = 102 % 10 + '0';
        System.out.println(c[0]);
        System.out.println(new CompressStringII().compress("wfwpycyirxzfilyrbcqbgefzwsoivsarmnxccbdfxfmoyqgqxewkiyuowkxwwvgjzpfpfnptmvgpjjxxptazmvnyzojvywmczceblgpwgxbxezxllldxbfarycoyhpuyjzymlvnpigkouqbeobcyhzhvuklgsgzzcmpcmwuwgtecltwhzaydhfdbgckkagddsmmnkjmtxbjlglnrvckrehtoeqblrzywjroyqqrdasxidxedvxmravqedfoejrinacqypromovniaedzcphocqihmynjebywmvhekggrjaamrnsyhjvkvgdbwxprkxrhkjtlnacspdtmnzsauvywyfpxxwhrbgpyaffyagxlagmhtxaovgnxfnziczakzossyxusqggtpwbuhvbnnsvbybjocbqgvzxnpoidixqhnhisiduynyvmjfrcfebicpqxbjchsdzvicrnlpio"));
    }

    /**
     * 使用StringBuilder完成
     * 初始化slow和fast指针在角标0
     * 如果相等就将fast指针移动一步，不相等将slow和fast-slow添加到StringBuilder中
     *
     * time = O(n)
     *
     * spcae = O(n)
     */
    public String compress(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        StringBuilder sb = new StringBuilder();
        int slow = 0;
        int fast = 0;
        while (fast < input.length()) {
            if (input.charAt(fast) == input.charAt(slow)) {
                fast++;
            } else {
                sb.append(input.charAt(slow)).append(fast - slow);
                slow = fast;
            }
        }

        // 处理最后一个元素，因为fast指针下一步就跳出循环了，所以在循环外执行
        sb.append(input.charAt(slow)).append(fast - slow);

        return sb.toString();
    }

    /**
     * time = O(n)
     * <p>
     * space = O(n)
     */
    public String compress1(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();

        // slow 物理意义: slow的左边不包括slow，全部都是已经压缩好的
        // fast 物理意义: fast表示当前扫描的最新位置
        int slow = 0;
        int fast = 0;
        int newLength = 0;
        while (fast < array.length) {
            // 记录fast刚刚进入循环时的角标，此时slow不等于fast！
            int begin = fast;
            // 当fast不等于刚进入循环的值时，进行下一步
            while (fast < array.length && array[begin] == array[fast]) {
                fast++;
            }

            //将循环进入时fast的值copy给slow，因为此时fast已经指向下一个不相等的字符了
            array[slow++] = array[begin];

            // 如果fast - begin == 1表示只有一个字符
            // 此时不但不能压缩，还会增加一个overhead，可能导致数组越界
            // 所以在这一轮循环中先不处理
            if (fast - begin == 1) {
                newLength += 2;
            } else {
                // 如果有多个字符，至少不会增加overhead，那么可以直接处理
                int len = copyDigits(array, slow, fast - begin);
                slow += len; // len为数字长度，还要加上原先的元素，所以是len + 1
                newLength += len + 1;
            }
        }

        // 根据计算出的需要长度，创建新的char array
        // fast指针指向上一轮循环的最后一个元素
        // slow指针指向新数组最后一个元素
        char[] result = new char[newLength];

        // 需要先重置fast角标，因为fast的位置依赖于slow
        fast = slow - 1;
        slow = newLength - 1;

        // 从后向前遍历
        while (fast >= 0) {
            // 如果是数字，那么把相邻的所有数字copy到新数组
            // 如果不是数组，那么copy一个1到新数组
            // 最后将该字符copy到新数组
            if (Character.isDigit(array[fast])) {
                while (fast >= 0 && Character.isDigit(array[fast])) {
                    result[slow--] = array[fast--];
                }
            } else {
                result[slow--] = '1';
            }
            result[slow--] = array[fast--];
        }

        return new String(result);
    }

    // 将数字转换成char并存储到char array中
    private int copyDigits(char[] array, int index, int counter) {
        int len = 0;
        // 记录数字位数，并移动相应的数组角标
        for (int i = counter; i > 0; i /= 10) {
            len++;
            index++;
        }

        // 从个位数开始向数组中写入元素
        for (int i = counter; i > 0; i /= 10) {
            int digit = i % 10;
            array[--index] = (char) ('0' + digit);
        }

        return len;
    }
}
