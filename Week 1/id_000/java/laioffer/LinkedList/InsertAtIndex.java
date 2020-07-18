package laioffer.LinkedList;

public class InsertAtIndex {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode = new InsertAtIndex().insertWithDummy(listNode, 1, 5);

        System.out.println(listNode);
    }

    // 不使用dummy，提前判断
    public ListNode insert(ListNode head, int index, int value) {
        // Write your solution here
        ListNode target = new ListNode(value);
        if (index < 0) {
            return head;
        }

        if (index == 0) {
            target.next = head;
            return target;
        }

        ListNode prev = null;
        ListNode cur = head;

        // 循环index，因为index无效就可以不用处理了
        // 但是cur为null还需要进行判断
        while (index > 0) {
            if (cur == null) {
                return head;
            }
            prev = cur;
            cur = cur.next;
            index--;
        }

        // 跳出循环并且没有返回，只有一种可能，index = 0，cur是不是null不影响
        // 在prev和cur中间插入target
        target.next = cur;
        prev.next = target;
        return head;
    }

    // 使用dummy进行辅助
    public ListNode insertWithDummy(ListNode head, int index, int value) {
        // Write your solution here
        ListNode target = new ListNode(value);
        if (index < 0) {
            return head;
        }

        // if (index == 0) {
        //   target.next = head;
        //   return target;
        // }

        // head不确定，可以判断index为0或者dummy辅助
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        // 循环index，因为index无效就可以不用处理了
        // 但是cur为null还需要进行判断
        while (index > 0) {
            if (cur == null) {
                return head;
            }
            prev = cur;
            cur = cur.next;
            index--;
        }

        // 跳出循环并且没有返回，只有一种可能，index = 0，cur是不是null不影响
        // 在prev和cur中间插入target
        target.next = cur;
        prev.next = target;
        return dummy.next;
    }
}
