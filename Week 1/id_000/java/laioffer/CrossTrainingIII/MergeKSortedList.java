package laioffer.CrossTrainingIII;

import laioffer.practice.design_queue_stack_deque.ListNode;
import laioffer.tree.ArrayToTree;

import java.util.*;

public class MergeKSortedList {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(5);
        ListNode listNode2 = new ListNode(3);
        listNode2.next = new ListNode(7);

        List<ListNode> listOfLists = Arrays.asList(listNode1, listNode2);
        ListNode listNode = new MergeKSortedList().merge1(listOfLists);
    }

    /**
     * input: List<ListNode> listOfLists
     * output: ListNode
     * Assume: listOfLists != null && listOfLists.size() > 0
     * 如果不符合假设，那么二维数组中没有元素，返回null
     *
     * high level: 使用k-way-merge解答
     * mid level: 有三种解法
     *  1、iterative: 每次拿两个ListNode进行merge，谁小移谁。然后将merge好的数组再和第三个array进行merge
     *  time = 2n + 3n + ... + kn = O(k^2 * n)  space = O(1)
     *  2、binary reduction: 将所有的ListNode进行俩俩分组，然后进行合并。将合并完之后的ListNode继续之前的步骤，直达只剩一个ListNode
     *  time = 2n * k/2 + 4n * k/4 + ... + kn = O(knlogk)  space = O(1)
     *  3、k-way-merge: 将所有ListNode的第一个元素放进min heap，每次pop出最小的元素，然后将该元素的下一个元素放进这个min heap中
     *  time = O(knlogk)  space = O(k)
     *
     * 在本题的情况下，binary reduction的方法更优
     */
    public ListNode merge1(List<ListNode> listOfLists) {
        // Write your solution here/.
        if (listOfLists == null || listOfLists.size() == 0) {
            return null;
        }

        return mergeSort(listOfLists, 0, listOfLists.size() - 1);
    }

    private ListNode mergeSort(List<ListNode> listOfLists, int left, int right) {
        if (left == right) {
            return listOfLists.get(left);
        }

        int mid = left + (right - left) / 2;
        ListNode leftNode = mergeSort(listOfLists, left, mid);
        ListNode rightNode = mergeSort(listOfLists, mid + 1, right);
        return merge(leftNode, rightNode);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (left != null && right != null) {
            if (left.value < right.value) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        if (left != null) {
            cur.next = left;
        } else if (right != null) {
            cur.next = right;
        }

        return dummy.next;
    }

    /**
     * input: List<ListNode> listOfLists
     * output: ListNode
     * Assume: listOfLists != null && listOfLists.size() > 0
     * 如果不符合假设，那么二维数组中没有元素，返回null
     * <p>
     * high level: 使用k-way-merge解答
     * mid level: 有三种解法
     * 1、iterative: 每次拿两个ListNode进行merge，谁小移谁。然后将merge好的数组再和第三个array进行merge
     * time = 2n + 3n + ... + kn = O(k^2 * n)  space = O(1)
     * 2、binary reducrion: 将所有的ListNode进行俩俩分组，然后进行合并。将合并完之后的ListNode继续之前的步骤，直达只剩一个ListNode
     * time = 2n * k/2 + 4n * k/4 + ... + kn = O(knlogk)  space = O(1)
     * 3、k-way-merge: 将所有ListNode的第一个元素放进min heap，每次pop出最小的元素，然后将该元素的下一个元素放进这个min heap中
     * time = O(knlogk)  space = O(k)
     * 在本题的情况下，k-way-merge的方法更优
     */
    public ListNode merge(List<ListNode> listOfLists) {
        // Write your solution here/.
        if (listOfLists == null || listOfLists.size() == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(
                new Comparator<ListNode>() {
                    @Override
                    public int compare(ListNode l1, ListNode l2) {
                        if (l1.value == l2.value) {
                            return 0;
                        }

                        return l1.value < l2.value ? -1 : 1;
                    }
                }
        );

        for (ListNode listNode : listOfLists) {
            if (listNode != null) {
                priorityQueue.offer(listNode);
            }
        }

        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                priorityQueue.offer(node.next);
            }
        }

        return dummy.next;
    }
}
