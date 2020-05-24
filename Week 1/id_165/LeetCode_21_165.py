# 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
#
# 示例：
#
# 输入：1->2->4, 1->3->4
# 输出：1->1->2->3->4->4
#
# Related Topics 链表


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
class linkedlist.ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: linkedlist.ListNode
        :type l2: linkedlist.ListNode
        :rtype: linkedlist.ListNode
        """
        head = linkedlist.ListNode(-1)
        current = head

        while l1 and l2:

            if l1.val <= l2.val:
                current.next = l1
                l1 = l1.next
            else:
                current.next = l2
                l2.next = l2

        current.next = l1 if l1 else l2

        return head.next









# leetcode submit region end(Prohibit modification and deletion)
