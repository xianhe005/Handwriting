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
        InversionLinkedList.Node n1 = linkList.first;
        if (n1 == null) {
            return;
        }
        InversionLinkedList.Node n2 = n1.next;
        if (n2 == null) {
            return;
        }

        // TODO: 2019/6/13 未完
        while (n2.next != null) {
            n1.next = n2.next;
            n2.next = n1;

            n1 = n2;
            n2 = n2.next;
        }
    }
}
