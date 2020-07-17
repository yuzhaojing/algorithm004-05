package laioffer.binary_search;

public class SearchInShiftedSortedArrayI {

    public static void main(String[] args) {
        int[] array = {2, 1};
        System.out.println(search(array, 1));
    }

    public static int search(int[] array, int target) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else {
                if (array[0] <= array[mid]) {
                    if (target >= array[0] && target < array[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (target > array[mid] && target <= array[array.length - 1]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }

        return -1;
    }
}
