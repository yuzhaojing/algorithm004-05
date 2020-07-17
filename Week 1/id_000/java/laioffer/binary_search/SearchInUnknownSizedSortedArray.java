package laioffer.binary_search;

interface Dictionary {
    public Integer get(int index);
}

public class SearchInUnknownSizedSortedArray {

    public static void main(String[] args) {

    }

    public int search(Dictionary dict, int target) {
        // Write your solution here
        if (dict == null) {
            return -1;
        }

        int left = 0;
        int right = 1;

        while (dict.get(right) != null && dict.get(right) < target) {
            left = right;
            right *= 2;
        }

        return binarySearch(dict, target, left, right);
    }

    public int binarySearch(Dictionary dict, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 由于int == null会引发NPE，所以需要优先处理dict.get(mid) == null的情况
            if (dict.get(mid) == null || dict.get(mid) > target) {
                right = mid - 1;
            } else if (dict.get(mid) == target) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
