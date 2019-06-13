package com.hxh.handwriting.leetcode;

import java.util.PriorityQueue;

/**
 * Created by HXH at 2019/6/13
 * LeetCode 第 23 号问题：合并 K 个排序链表
 */
public class MergeKList {

    public static void test() {
        int size = 4;
        InversionLinkedList.LinkList[] lists = new InversionLinkedList.LinkList[size];
        for (int i = 0; i < size; i++) {
            InversionLinkedList.LinkList list = new InversionLinkedList.LinkList();
            for (int j = i; j < 20; j += i + 1) {
                list.addFirstNode(20 - j);
            }
            lists[i] = list;
        }
        System.out.println("========================");
        for (InversionLinkedList.LinkList list : lists) {
            list.displayAllNodes();
        }
        InversionLinkedList.LinkList linkList = mergeKLists(lists);
        System.out.println("========================");
        linkList.displayAllNodes();
    }

    private static InversionLinkedList.LinkList mergeKLists(InversionLinkedList.LinkList[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        InversionLinkedList.Node node;
        for (InversionLinkedList.LinkList list : lists) {
            node = list.first;
            while (node != null) {
                queue.offer(node.data);
                node = node.next;
            }
        }
        if (queue.isEmpty()) {
            return null;
        }

        node = null;
        InversionLinkedList.LinkList linkList = null;
        while (!queue.isEmpty()) {
            Integer data = queue.poll();
            if (node == null) {
                node = new InversionLinkedList.Node(data);
                linkList = new InversionLinkedList.LinkList(node);
            } else {
                node.next = new InversionLinkedList.Node(data);
                node = node.next;
            }
        }
        return linkList;
    }
}
