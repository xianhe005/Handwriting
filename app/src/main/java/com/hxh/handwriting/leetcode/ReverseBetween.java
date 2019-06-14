package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/14
 * LeetCode 第 92 号问题：反转链表 II
 */
public class ReverseBetween {

    public static void test() {
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList();
        for (int i = 0; i < 9; i++) {
            linkList.addFirstNode(9 - i);
        }
        linkList.displayAllNodes();
        System.out.println("========================");
        reverseBetween(linkList, 2, 4);
        linkList.displayAllNodes();
        System.out.println("========================");
        reverseBetween(linkList, 2, 5);
        linkList.displayAllNodes();
        System.out.println("========================");
        reverseBetween(linkList, 2, 8);
        linkList.displayAllNodes();
        System.out.println("========================");
        reverseBetween(linkList, 2, 9);
        linkList.displayAllNodes();
        System.out.println("========================");
        reverseBetween(linkList, 1, 4);
        linkList.displayAllNodes();
        System.out.println("========================");
        reverseBetween(linkList, 1, 10);
        linkList.displayAllNodes();
        System.out.println("========================");
        reverseBetween(linkList, 0, 10);
        linkList.displayAllNodes();
    }

    private static void reverseBetween(InversionLinkedList.LinkList linkList, int from, int to) {
        if (linkList.first == null || linkList.first.next == null || from > to) {
            return;
        }
        InversionLinkedList.Node node = new InversionLinkedList.Node(-1);
        // 在前面增加一个辅助节点,这个想法很好
        node.next = linkList.first;
        InversionLinkedList.Node preNode = node;

        for (int i = 0; i < from - 1; i++) {
            preNode = preNode.next;
        }
        //反转区间的头结点
        InversionLinkedList.Node cur = preNode.next;
        for (int i = 0; i < to - from; i++) {
            // 思路：采用头插法，不断地把要反转的节点插到反转区间头节点的前面。
            // 重点就是记录第m个结点的前驱结点和第n个结点的后续结点。
            InversionLinkedList.Node temp = cur.next;
            if (temp == null) {
                break;
            }
            cur.next = temp.next;
            temp.next = preNode.next;
            preNode.next = temp;
        }
        // ps:一定要注意重置链表首接口
        linkList.first = node.next;
        node.next = null;
    }
}
