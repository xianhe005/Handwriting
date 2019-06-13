package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/13
 * LeetCode 第 19 号问题：删除链表的倒数第 N 个节点
 */
public class RemoveNthFromEnd {

    public static void test() {
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList();
        for (int i = 0; i < 9; i++) {
            linkList.addFirstNode(9 - i);
        }
        linkList.displayAllNodes();
        System.out.println("========================");
        removeNthFromEnd(linkList, 3);
        linkList.displayAllNodes();
        System.out.println("========================");
        removeNthFromEnd(linkList, 2);
        linkList.displayAllNodes();
        System.out.println("========================");
        removeNthFromEnd(linkList, 1);
        linkList.displayAllNodes();
        System.out.println("========================");
        removeNthFromEnd(linkList, 0);
        linkList.displayAllNodes();
        System.out.println("========================");
        removeNthFromEnd(linkList, 5);
        linkList.displayAllNodes();
        System.out.println("========================");
        removeNthFromEnd(linkList, 5);
        linkList.displayAllNodes();
        System.out.println("========================");
        removeNthFromEnd(linkList, 5);
        linkList.displayAllNodes();
    }

    private static void removeNthFromEnd(InversionLinkedList.LinkList linkList, int lastIndex) {
        if (linkList.first == null) {
            return;
        }
        if (lastIndex <= 0) {
            return;
        }
        InversionLinkedList.Node node1 = null;
        InversionLinkedList.Node node2 = linkList.first;
        int i = 0;
        while (node2 != null) {
            if (i >= lastIndex) {
                if (node1 == null) {
                    node1 = linkList.first;
                } else {
                    node1 = node1.next;
                }
            }
            node2 = node2.next;
            i++;
        }
        if (i < lastIndex) {
            return;
        }
        if (node1 == null) {
            linkList.first = linkList.first.next;
        } else {
            node1.next = node1.next.next;
        }
    }
}
