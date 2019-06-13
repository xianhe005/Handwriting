package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/13
 * LeetCode 第 21 号问题：合并两个有序链表
 */
public class MergeTwoSortedList {

    public static void test() {
        InversionLinkedList.LinkList l1 = new InversionLinkedList.LinkList();
        for (int i = 0; i < 9; i += 2) {
            l1.addFirstNode(9 - i);
        }
        l1.displayAllNodes();
        System.out.println("========================");
        InversionLinkedList.LinkList l2 = new InversionLinkedList.LinkList();
        for (int i = 1; i < 9; i += 2) {
            l2.addFirstNode(9 - i);
        }
        l2.displayAllNodes();
        System.out.println("========================");
        InversionLinkedList.Node node = mergeTwoLists(l1.first, l2.first);
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList(node);
        linkList.displayAllNodes();
    }


    private static InversionLinkedList.Node mergeTwoLists(InversionLinkedList.Node l1, InversionLinkedList.Node l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        InversionLinkedList.Node head;
        if (l1.data <= l2.data) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }
}
