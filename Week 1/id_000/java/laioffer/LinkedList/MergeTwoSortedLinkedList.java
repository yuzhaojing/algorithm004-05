package laioffer.LinkedList;

public class MergeTwoSortedLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = new MergeTwoSortedLinkedList().merge(listNode, new ListNode(5));

        System.out.println(listNode);
    }

    public ListNode merge(ListNode one, ListNode two) {
        // Write your solution here
        if (one == null) {
            return two;
        }

        if (two == null) {
            return one;
        }

        // head不确定，使用dummy方便，可以少做很多判断
        ListNode dummy = new ListNode(0);
        // 需要一直往dummy这个链表后面添加元素，保持一个指针在链表末尾
        ListNode cur = dummy;

        while (one != null && two != null) {
            if (one.value < two.value) {
                cur.next = one;
                one = one.next;
            } else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }

        // 循环结束后一定有一个链表中还有元素
        // 一下两个判断只会进入一个，所以cur不需要移动了
        if (one != null) {
            cur.next = one;
        }

        if (two != null) {
            cur.next = two;
        }

        return dummy.next;
    }
}
