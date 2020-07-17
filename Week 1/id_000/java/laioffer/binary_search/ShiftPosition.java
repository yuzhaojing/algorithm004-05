package laioffer.binary_search;

public class ShiftPosition {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(shiftPosition(array));
    }

    public static int shiftPosition(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[0] < array[mid] && array[0] > array[array.length - 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return array[left] < array[right] ? left : right;
    }
}
