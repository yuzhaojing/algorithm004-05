#!/anaconda3/bin/python

class linkedlist.ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class LeetCode_21_495(object):

    def mergeTwoLists(self, l1, l2):
        prehead = linkedlist.ListNode(-1)

        prev = prehead
        while l1 and l2:
            if l1.val <= l2.val:
                prev.next = l1
                l1 = l1.next
            else:
                prev.next = l2
                l2 = l2.next
            prev = prev.next

        prev.next = l1 if l1 is not None else l2

        return prehead.next

if __name__ == '__main__':
    a = linkedlist.ListNode(11)
    ab = linkedlist.ListNode(2)
    ac = linkedlist.ListNode(3)
    ad = linkedlist.ListNode(4)
    ad = linkedlist.ListNode(4)
    a.next = ab
    ab.next = ac
    ac.next = ad
    b = linkedlist.ListNode(55)
    bb = linkedlist.ListNode(6)
    bc = linkedlist.ListNode(7)
    bd = linkedlist.ListNode(8)
    be = linkedlist.ListNode(9)
    bf = linkedlist.ListNode(10)
    bg = linkedlist.ListNode(12)
    b.next = bb
    bb.next = bc
    bc.next = bd
    bd.next = be
    be.next = bf
    bf.next = bg
    c = LeetCode_21_495().mergeTwoLists(a,b)
    while c.next:
        print(c.val)
        c = c.next
