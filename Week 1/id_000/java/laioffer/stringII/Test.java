package laioffer.stringII;

public class Test {
    public static void main(String[] args) {
        System.out.println(new Test().compress("aaaaaaaaaaaanneeeeeeefffffffwwwwwwwwwwwwwwfffhhhhhhhhhhhhhhhheeeeeeeeeeeeeeedddddddddddddddddddddddddddddgggggggggggggggggggggllllllllllllllllllvvvvvvvvvvvjggggggggggggggggggggccccccccccccccccjjjjttttttttttttttttttttttttttttttmdddkkkkkkkkkkkkkkkkkooooooooooooooooooooaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhyyyyyyyyyyyyyyyyyyyyyyyyyyyyoooooooooooooohhhhhelnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjuuuuuuuuuuuuffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaappppppppppppppppppppppppppppppfffffffffffffffffffffffffffffggeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeevvvvvvvvvvvvvvveeeeeeeeeeeeeeeeeeeeeeellllllllllllllllllllaaaaaaiiiiiiiiiiiiillllggggggggggggggggggggggggggkgo"));
    }

    public String compress(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();
        return encode(array);
    }

    private String encode(char[] array) {
        int slow = 0;
        int fast = 0;
        int newLength = 0;

        while (fast < array.length) {
            int begin = fast;
            while (fast < array.length && array[fast] == array[begin]) {
                fast++;
            }
            array[slow++] = array[begin]; //将循环进入时fast的值copy给slow，因为此时fast已经指向下一个不相等的字符了

            if (fast - begin == 1) {
                newLength += 2;
            } else {
                int len = copyDigits(array, slow, fast - begin);
                newLength += len + 1; // len为数字长度，还要加上原先的元素，所以是len + 1
                slow += len; // 移动数字长度
            }
        }

        char[] res = new char[newLength];
        fast = slow - 1;
        slow = newLength - 1;

        while (fast >= 0) {
            if (Character.isDigit(array[fast])) {
                // 跳过所有数字
                while (fast >= 0 && Character.isDigit(array[fast])) {
                    res[slow--] = array[fast--];
                }
                // 跳过以上数组表示的字符
                res[slow--] = array[fast--];
            } else {
                // 没有被计数的字符，说明只有一个
                res[slow--] = '1';
                res[slow--] = array[fast--];
            }
        }

        return new String(res);
    }

    private int copyDigits(char[] array, int start, int counter) {
        int len = 0;
        for (int i = counter; i > 0; i /= 10) {
            len++;
            start++;
        }

        for (int i = counter; i > 0; i /= 10) {
            array[--start] = (char) ((i % 10) + '0');
        }

        return len;
    }
}
