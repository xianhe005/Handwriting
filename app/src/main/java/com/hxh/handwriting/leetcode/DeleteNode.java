package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 237 号问题：删除链表中的节点
 */
public class DeleteNode {

    public static void test() {
        InversionLinkedList.Node node = new InversionLinkedList.Node(3);
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList(node);
        linkList.addFirstNode(2);
        linkList.addFirstNode(1);
        linkList.add(3, 4);
        linkList.add(4, 5);
        linkList.displayAllNodes();
        deleteNode(node);
        linkList.displayAllNodes();
        System.out.println(node.data);
    }

    private static void deleteNode(InversionLinkedList.Node node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }
}
