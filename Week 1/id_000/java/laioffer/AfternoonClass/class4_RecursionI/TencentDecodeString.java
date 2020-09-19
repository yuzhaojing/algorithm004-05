package laioffer.AfternoonClass.class4_RecursionI;

public class TencentDecodeString {

    public static void main(String[] args) {
        System.out.println(new TencentDecodeString().decode("HG[3|B[2|CA]]F"));
    }

    /**
     * 给定一个压缩后的字符串s，解压缩
     * s内仅包含大写字母、[、]、|
     * 输出解压缩后的字符串
     *
     * example:
     *  input:  HG[3|B[2|CA]]F
     *  output: HGBCACABCACABCACAF
     *
     *
     * input:  string input
     * output: string
     * Assume: input != null && input.length() > 1
     * 如果不符合假设，返回input，不做任何处理
     *
     * high level: 使用Recursion解答
     * detail level:
     *  1、遍历input，当遇到'['的时候，递归到下一层
     *  2、当遇到数字的时候，记录数字知道遇到'|'为止，然后将下一个']'之前的字符都复制遍历到数字的次数，返回上一层
     */
    public String decode(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.append(c);
            } else if (c == '[') {
                int start = i;
                int left = 1;
                int right = 0;
                while (i + 1 < input.length()) {
                    if (input.charAt(i + 1) == '[') {
                        left++;
                    } else if (input.charAt(i + 1) == ']') {
                        right++;
                    }
                    i++;
                    if (left == right) {
                        break;
                    }
                }
                String decode = decode(input.substring(start + 1, i));
                String s = sb.append(decode).toString();
                while (count > 1) {
                    sb.append(s);
                    count--;
                }
            } else if (c == ']') {
                return sb.toString();
            } else if (c >= '0' && c <= '9') {
                while (i < input.length() && input.charAt(i) != '|') {
                    count = count * 10 + (c - '0');
                    i++;
                }
            }
        }

        String s = sb.toString();
        while (count > 1) {
            sb.append(s);
            count--;
        }
        return sb.toString();
    }
}
