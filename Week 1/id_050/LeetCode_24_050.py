# Definition for singly-linked list.
# class linkedlist.ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def swapPairs(self, head):
        """
        :type head: linkedlist.ListNode
        :rtype: linkedlist.ListNode
        """
        if head is None or head.next is None:
            return head
        dummy = linkedlist.ListNode(0)
        dummy.next = head
        p = dummy
        while p.next and p.next.next:
            tmp = p.next.next
            p.next.next = tmp.next
            tmp.next = p.next
            p.next = tmp
            p = tmp.next
        return dummy.next
