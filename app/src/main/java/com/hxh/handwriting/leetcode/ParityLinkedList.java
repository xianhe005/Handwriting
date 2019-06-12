package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/12
 * LeetCode 第 328 号问题：奇偶链表
 */
public class ParityLinkedList {

    public static void test() {
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList();
        for (int i = 0; i < 9; i++) {
            linkList.addFirstNode(9 - i);
        }
        linkList.displayAllNodes();
        System.out.println("========================");
        parityLinkedList(linkList);
        linkList.displayAllNodes();
    }

    private static void parityLinkedList(InversionLinkedList.LinkList linkList) {
        if (linkList.first == null || linkList.first.next == null) return;
        InversionLinkedList.Node odd = linkList.first, even = linkList.first.next, evenhead = even;
        while (odd.next != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenhead;
    }
}
