package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/12
 * LeetCode 第 203 号问题：移除链表元素
 */
public class RemoveLinkedListVal {
    public static void test() {
        InversionLinkedList.LinkList linkList = new InversionLinkedList.LinkList();
        for (int i = 0; i < 10; i++) {
            linkList.addFirstNode(10 - i);
        }
        linkList.addFirstNode(6);
        linkList.addFirstNode(6);
        linkList.displayAllNodes();
        System.out.println("========================");
        removeList(linkList, 6);
        linkList.displayAllNodes();
    }

    private static void removeList(InversionLinkedList.LinkList linkList, int val) {
        InversionLinkedList.Node node = linkList.first;
        InversionLinkedList.Node b;
        //排除表头值相等
        //先不去管当前的表头的 val是否相等；
        while (node.next != null) {
            if (node.next.data == val) {  //b就是相等的结点
                b = node.next;
                //判断b的next是否为空
                if (b.next == null) {   //这里为什么不写b.next=Null;
                    //进行到这里就要跳出循环了
                    // 现在我们的b没有进行任何赋值操。
                    //b再这里不属于链表的
                    //node.属于链表
                    node.next = null;
                    break;
                }
                //把b的下一个结点给a的下一个结点。
                //node.next 结点存储的当前val相等的结点的位置
                // node.next = b.next; 就是把当前 b.next的地址给A.next，
                //原本存在A.next里面的结点，指向的位置发生了改变，到了b.next的位置
                node.next = b.next;
            } else {    //就是循环链表
                node = node.next;
            }
        }
        //最后再考虑当前的表头的val是否相等
        //如果相当，就把表头的next结点的地址给head,
        if (linkList.first.data == val) {
            linkList.first = linkList.first.next;
        }
    }
}
