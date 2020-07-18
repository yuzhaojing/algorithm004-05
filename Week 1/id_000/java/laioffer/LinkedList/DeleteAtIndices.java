package laioffer.LinkedList;

public class DeleteAtIndices {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode = new DeleteAtIndices().deleteNodes(listNode, new int[] {1});

        System.out.println(listNode);
    }

    public ListNode deleteNodes(ListNode head, int[] indices) {
        // Write your solution here
        if (head == null || indices == null || indices.length == 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        // index为链表当前节点角标，indicesIndex为删除数组中的角标
        int index = 0;
        int indicesIndex = 0;

        // 当head != null and indicesIndex < indices.length时，当前节点有被删除的可能性
        while (head != null && indicesIndex < indices.length) {
            // 如果节点角标不等于删除数组中的元素，忽略当前节点
            // 如果相等的话，将当前节点删除，并将indicesIndex后移一步
            if (index != indices[indicesIndex]) {
                prev = head;
            } else {
                prev.next = head.next;
                indicesIndex++;
            }
            // 移动当前节点以及角标
            index++;
            head = head.next;
        }

        return dummy.next;
    }
}
