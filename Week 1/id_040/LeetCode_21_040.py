#将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
#
# 示例：
#
# 输入：1->2->4, 1->3->4
#输出：1->1->2->3->4->4
#
# Related Topics 链表



#leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
class linkedlist.ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def mergeTwoLists(self, l1: linkedlist.ListNode, l2: linkedlist.ListNode) -> linkedlist.ListNode:
        """
        直接比较插入
        :param l1:
        :param l2:
        :return:
        """
        l3 = linkedlist.ListNode(-1)
        pre = l3
        while l1 and l2:
            if l1.val < l2.val:
                pre.next = l1
                l1 = l1.next
            else:
                pre.next = l2
                l2 = l2.next
            pre = pre.next

        pre.next = l1 or l2  # 把最后没用完的拼接上来

        return l3.next

#leetcode submit region end(Prohibit modification and deletion)

if __name__ == '__main__':
    n1 = linkedlist.ListNode(1)
    n2 = linkedlist.ListNode(2)
    n3 = linkedlist.ListNode(4)

    n1.next = n2
    n2.next = n3

    m1 = linkedlist.ListNode(1)
    m2 = linkedlist.ListNode(3)
    m3 = linkedlist.ListNode(4)

    m1.next = m2
    m2.next = m3

    s = Solution()
    mn = s.mergeTwoLists(n1, m2)

    while mn:
        print(mn.val)
        mn = mn.next


