package laioffer.recursionII;

public class StringAbbreviationMatching {

    public static void main(String[] args) {
        System.out.println(new StringAbbreviationMatching().match("laioffercom", "5fer3"));
    }

    /**
     * 下面的递归是一个尾递归，所以可以很简单的改成迭代
     *
     * input = n pattern = m
     *
     * time = O(m)
     * pattern的每一个元素都需要遍历，而input不需要
     *
     * space = O(1)
     */
    public boolean match(String input, String pattern) {
        // Write your solution here
        if (input == null || pattern == null) {
            return false;
        }

        int slow = 0;
        int fast = 0;

        while (fast < pattern.length() && slow < input.length()) {
            if (!Character.isDigit(pattern.charAt(fast))) {
                if (input.charAt(slow) != pattern.charAt(fast)) {
                    return false;
                }
                fast++;
                slow++;
            } else {
                int num = 0;
                while (fast < pattern.length() && Character.isDigit(pattern.charAt(fast))) {
                    num = num * 10 + (pattern.charAt(fast++) - '0');
                }
                slow += num;
            }
        }

        return fast == pattern.length() && slow == input.length();
    }

    public boolean match1(String input, String pattern) {
        // Write your solution here
        if (input == null || pattern == null) {
            return false;
        }

        return match(input, pattern, 0, 0);
    }

    private boolean match(String input, String pattern, int inputIndex, int startIndex) {
        if (inputIndex == input.length() && startIndex == pattern.length()) {
            return true;
        }

        if (inputIndex >= input.length() || startIndex >= pattern.length()) {
            return false;
        }

        if (!Character.isDigit(pattern.charAt(startIndex))) {
            if (input.charAt(inputIndex) != pattern.charAt(startIndex)) {
                return false;
            }
            return match(input, pattern, inputIndex + 1, startIndex + 1);
        } else {
            int num = 0;
            while (startIndex < pattern.length() && Character.isDigit(pattern.charAt(startIndex))) {
                num = num * 10 + (pattern.charAt(startIndex) - '0');
                startIndex++;
            }
            return match(input, pattern, inputIndex + num, startIndex);
        }
    }
}
