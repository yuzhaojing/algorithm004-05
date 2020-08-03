package laioffer.dfs;


import java.util.ArrayList;
import java.util.List;

public class InsertSpace {

    public static void main(String[] args) {
        String set = "abc";
        System.out.println(new InsertSpace().insertSpace(set));
    }

    public List<String> insertSpace(String set) {
        // Write your solution here.
        List<String> res = new ArrayList<>();
        if (set == null || set.length() == 0) {
            return res;
        }

        char[] array = set.toCharArray();

        insertSpace(array, new StringBuilder(set), 0, 0, res);
        return res;
    }

    private void insertSpace(char[] array, StringBuilder cur, int index, int spaces, List<String> res) {
        if (array.length == index) {
            res.add(cur.toString());
            return;
        }

        cur.insert(index + spaces + 1, ' ');
        insertSpace(array, cur, index + 1, spaces + 1, res);
        cur.deleteCharAt(index + spaces + 1);
        insertSpace(array, cur, index + 1, spaces - 1, res);
    }

    private void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
