package laioffer.LinkedList;

public class RemoveExtraDuplicatesSortedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(3);
        listNode.next.next.next.next.next = new ListNode(3);
        listNode.next.next.next.next.next.next = new ListNode(3);
        listNode = new RemoveExtraDuplicatesSortedList().removeDup(listNode);

        System.out.println(listNode);
    }

    public ListNode removeDup(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 如果下个节点是null的话，不需要进入循环，因为后面不会有重复的元素了
        while (head != null && head.next != null) {
            // 如果当前节点和下个节点的value一样，删除这个value的所有节点
            if (head.value == head.next.value) {
                ListNode temp = head;
                while (temp.next != null && temp.value == temp.next.value) {
                    temp = temp.next;
                }
                // temp是相同value的最后一个元素，直接head = temp.next跳过temp
                head = temp.next;
                prev.next = head;
            } else {
                // value不相同，直接双指针走一步
                prev = head;
                head = head.next;
            }
        }

        return dummy.next;
    }
}
