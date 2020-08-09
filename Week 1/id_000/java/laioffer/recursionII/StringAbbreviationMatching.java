package laioffer.recursionII;

public class StringAbbreviationMatching {

    public static void main(String[] args) {
        System.out.println(new StringAbbreviationMatching().match("laioffercom", "5fer3"));
    }

    public boolean match(String input, String pattern) {
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
