package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/14
 * LeetCode 第 86 号问题：分割链表
 */
public class PartitionLinkedList {
    public static void test() {
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList();
        for (int i = 0; i < 9; i++) {
            linkList.addFirstNode(i + 1);
        }
        /*linkList.addFirstNode(6);
        linkList.addFirstNode(2);
        linkList.addFirstNode(3);
        linkList.addFirstNode(5);*/
        linkList.displayAllNodes();
        System.out.println("========================");
        partition(linkList, 3);
        linkList.displayAllNodes();
        System.out.println("========================");
        partition(linkList, 7);
        linkList.displayAllNodes();
        System.out.println("========================");
        partition(linkList, 1);
        linkList.displayAllNodes();
        System.out.println("========================");
        partition(linkList, 0);
        linkList.displayAllNodes();
        System.out.println("========================");
        partition(linkList, 8);
        linkList.displayAllNodes();
        System.out.println("========================");
        partition(linkList, 10);
        linkList.displayAllNodes();
    }

    private static void partition(InversionLinkedList.LinkList linkList, int val) {
        if (linkList.first == null) {
            return;
        }
        if (linkList.first.next == null) {
            return;
        }
        InversionLinkedList.Node n = linkList.first;
        InversionLinkedList.Node n1 = null;
        InversionLinkedList.Node n2 = null;
        InversionLinkedList.Node n2Head = null;

        while (n != null) {
            if (n.data <= val) {
                if (n1 == null) {
                    linkList.first = n1 = n;
                } else {
                    n1.next = n;
                    n1 = n;
                }
            } else {
                if (n2Head == null) {
                    n2Head = n2 = n;
                } else {
                    n2.next = n;
                    n2 = n;
                }
            }
            n = n.next;
        }
        if (n1 == null) {
            linkList.first = n2Head;
        } else if (n2Head != null) {
            n1.next = n2Head;
            n2.next = null;
        }
    }
}
