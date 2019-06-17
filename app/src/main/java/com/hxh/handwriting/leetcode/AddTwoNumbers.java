package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 2 号问题：两数相加
 */
public class AddTwoNumbers {

    public static void test() {
        InversionLinkedList.LinkList linkList1 = new InversionLinkedList.LinkList();
        linkList1.addFirstNode(3);
        linkList1.addFirstNode(4);
        linkList1.addFirstNode(2);
        InversionLinkedList.LinkList linkList2 = new InversionLinkedList.LinkList();
        linkList2.addFirstNode(4);
        linkList2.addFirstNode(6);
        linkList2.addFirstNode(5);
        linkList1.displayAllNodes();
        linkList2.displayAllNodes();
        InversionLinkedList.LinkList linkList = addTwoNumbers(linkList1, linkList2);
        if (linkList != null) {
            linkList.displayAllNodes();
        } else {
            System.out.println("linkList is null");
        }
        System.out.println("=====================================");
        linkList1.addFirstNode(8);
        linkList1.displayAllNodes();
        linkList2.displayAllNodes();
        linkList = addTwoNumbers(linkList1, linkList2);
        if (linkList != null) {
            linkList.displayAllNodes();
        } else {
            System.out.println("linkList is null");
        }
        System.out.println("=====================================");
        linkList2.addFirstNode(2);
        linkList1.displayAllNodes();
        linkList2.displayAllNodes();
        linkList = addTwoNumbers(linkList1, linkList2);
        if (linkList != null) {
            linkList.displayAllNodes();
        } else {
            System.out.println("linkList is null");
        }
        System.out.println("=====================================");
        linkList1.add(4, 6);
        linkList1.displayAllNodes();
        linkList2.displayAllNodes();
        linkList = addTwoNumbers(linkList1, linkList2);
        if (linkList != null) {
            linkList.displayAllNodes();
        } else {
            System.out.println("linkList is null");
        }
        System.out.println("=====================================");
        linkList2.addFirstNode(2);
        linkList1.displayAllNodes();
        linkList2.displayAllNodes();
        linkList = addTwoNumbers(linkList1, linkList2);
        if (linkList != null) {
            linkList.displayAllNodes();
        } else {
            System.out.println("linkList is null");
        }
    }

    private static InversionLinkedList.LinkList addTwoNumbers(InversionLinkedList.LinkList linkList1, InversionLinkedList.LinkList linkList2) {
        if (linkList1.first == null && linkList2.first == null) {
            return null;
        }
        InversionLinkedList.Node n1 = linkList1.first;
        InversionLinkedList.Node n2 = linkList2.first;
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList();
        int carry = 0;
        while (n1 != null && n2 != null) {
            int data = n1.data + n2.data + carry;
            int v;
            if (data > 9) {
                carry = 1;
                v = data - 10;
            } else {
                carry = 0;
                v = data;
            }
            linkList.addFirstNode(v);
            n1 = n1.next;
            n2 = n2.next;
        }

        if (n1 != null) {
            while (n1 != null) {
                int v;
                if (carry > 0) {
                    int data = n1.data + carry;
                    if (data > 9) {
                        carry = 1;
                        v = data - 10;
                    } else {
                        carry = 0;
                        v = data;
                    }
                } else {
                    v = n1.data;
                }
                linkList.addFirstNode(v);
                n1 = n1.next;
            }
        } else if (n2 != null) {
            while (n2 != null) {
                int v;
                if (carry > 0) {
                    int data = n2.data + carry;
                    if (data > 9) {
                        carry = 1;
                        v = data - 10;
                    } else {
                        carry = 0;
                        v = data;
                    }
                } else {
                    v = n2.data;
                }
                linkList.addFirstNode(v);
                n2 = n2.next;
            }
        } else {
            if (carry > 0) {
                linkList.addFirstNode(1);
            }
        }
        return linkList;
    }
}
