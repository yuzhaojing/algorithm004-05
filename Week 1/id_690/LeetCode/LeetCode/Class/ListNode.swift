//
//  linkedlist.ListNode.swift
//  LeetCode
//
//  Created by 张银龙 on 2019/10/20.
//  Copyright © 2019 张银龙. All rights reserved.
//

import Cocoa

 public class linkedlist.ListNode {
     public var val: Int
     public var next: linkedlist.ListNode?
     public init(_ val: Int) {
         self.val = val
         self.next = nil
     }
 }
