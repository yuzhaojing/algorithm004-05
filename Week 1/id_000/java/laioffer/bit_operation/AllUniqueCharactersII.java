package laioffer.bit_operation;

public class AllUniqueCharactersII {

    public static void main(String[] args) {
        System.out.println(new AllUniqueCharactersII().allUnique("235"));
    }

    /**
     *
     */
    public boolean allUnique(String word) {
        // Write your solution here
        if (word == null || word.length() <= 1) {
            return true;
        }

        // 创建一个大小为256的int数组作为bit map
        int[] bitMap = new int[8];

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // 字符在bitMap中对应的int位置
            int index = c / 32;

            // 字符在int中对应的bit
            int k = c % 32;

            // 将对应的int取出来，与上1左移k个bit
            if ((bitMap[index] & (1 << k)) != 0) {
                return false;
            }

            // 将对应的int取出来，或上1左移k个bit
            bitMap[index] |= (1 << k);
        }

        return true;
    }
}
