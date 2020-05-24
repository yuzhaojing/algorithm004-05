# Definition for singly-linked list.
# class linkedlist.ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def reverseList(self, head):
        """
        :type head: linkedlist.ListNode
        :rtype: linkedlist.ListNode
        """
        prev = None
        while head:
            tmp = head.next
            head.next = prev
            prev = head
            head = tmp
        return prev
