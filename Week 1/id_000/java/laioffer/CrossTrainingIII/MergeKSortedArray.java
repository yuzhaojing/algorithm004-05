package laioffer.CrossTrainingIII;

import laioffer.tree.ArrayToTree;
import laioffer.tree.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArray {

    public static void main(String[] args) {

    }

    /**
     * input: int[][] arrayOfArrays
     * output: int[]
     * Assume: arrayOfArrays != null && arrayOfArrays.length > 0
     * 如果不符合假设，那么二维数组中没有元素，返回空的int array
     *
     * high level: 使用k-way-merge解答
     * mid level: 有三种解法
     *  1、iterative: 每次拿两个array进行merge，谁小移谁。然后将merge好的数组再和第三个array进行merge
     *     time = 2n + 3n + ... + kn = O(k^2 * n)  space = O(kn)
     *  2、binary reducrion: 将所有的array进行俩俩分组，然后进行合并。将合并完之后的array继续之前的步骤，直达只剩一个array
     *     time = 2n * k/2 + 4n * k/4 + ... + kn = O(knlogk)  space = O(kn)
     *  3、k-way-merge: 将所有array的第一个元素放进min heap，每次pop出最小的元素，然后将该元素的下一个元素放进这个min heap中
     *     time = O(knlogk)  space = O(k)
     *  在本题的情况下，k-way-merge的方法更优
     */
    public int[] merge(int[][] arrayOfArrays) {
        // Write your solution here
        if (arrayOfArrays == null || arrayOfArrays.length == 0) {
            return new int[0];
        }

        PriorityQueue<Element> priorityQueue = new PriorityQueue<>(
                new Comparator<Element>() {
                    @Override
                    public int compare(Element e1, Element e2) {
                        if (e1.value == e2.value) {
                            return 0;
                        }

                        return e1.value < e2.value ? -1 : 1;
                    }
                }
        );

        int length = 0;
        for (int i = 0; i < arrayOfArrays.length; i++) {
            if (arrayOfArrays[i].length > 0) {
                length += arrayOfArrays[i].length;
                priorityQueue.offer(new Element(i, 0, arrayOfArrays[i][0]));
            }
        }

        int[] res = new int[length];
        int cur = 0;
        while (!priorityQueue.isEmpty()) {
            Element e = priorityQueue.poll();
            res[cur++] = e.value;
            int[] array = arrayOfArrays[e.indexOfArray];
            if (array.length - 1 > e.indexInArray) {
                e.indexInArray++;
                e.value = array[e.indexInArray];
                priorityQueue.offer(e);
            }
        }

        return res;
    }

    class Element {
        int indexOfArray;
        int indexInArray;
        int value;

        public Element(int indexOfArray, int indexInArray, int value) {
            this.indexOfArray = indexOfArray;
            this.indexInArray = indexInArray;
            this.value = value;
        }
    }
}
