package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/13
 * LeetCode 第 24 号问题：两两交换链表中的节点
 */
public class SwapPairs {

    public static void test() {
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList();
        for (int i = 0; i < 9; i++) {
            linkList.addFirstNode(9 - i);
        }
        linkList.displayAllNodes();
        System.out.println("========================");
        swapPairs(linkList);
        linkList.displayAllNodes();
    }

    private static void swapPairs(InversionLinkedList.LinkList linkList) {
        InversionLinkedList.Node n = linkList.first;
        if (n == null) {
            return;
        }
        if (n.next == null) {
            return;
        }
        linkList.first = n.next;
        InversionLinkedList.Node pre = null;
        while (n.next != null) {
            InversionLinkedList.Node next = n.next;
            n.next = next.next;
            next.next = n;
            if (pre != null) {
                pre.next = next;
            }

            pre = n;
            n = n.next;
        }
    }
}
