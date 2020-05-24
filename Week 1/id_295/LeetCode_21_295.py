# Definition for singly-linked list.
class linkedlist.ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def mergeTwoLists(self, l1: linkedlist.ListNode, l2: linkedlist.ListNode) -> linkedlist.ListNode:
        prehead = linkedlist.ListNode(-1)
        prev = prehead

        while l1 and l2:
            if l1.val < l2.val:
                prev.next = l1
                l1 = l1.next
            else:
                prev.next = l2
                l2 = l2.next
            prev = prev.next
        # if l1 is None:
        prev.next = l2 if l1 is None else l1
        return prehead.next
        # else:
