package com.hxh.handwriting.leetcode;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HXH at 2019/6/20
 * LeetCode 第 138 号问题：复制带随机指针的链表
 */
public class CopyRandomListNode {

    public static void test() {
        RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.next.next.next = new RandomListNode(4);
        head.next.next.next.extra = head;
        head.next.next.extra = head.next.next.next;
        head.next.extra = head.next;

        System.out.println(head);
        RandomListNode newHead = copyRandomList3(head);
        System.out.println(newHead);

        System.out.println(newHead == head);
        System.out.println(head.toString().equals(newHead.toString()));

        System.out.println("========================================");
        while (head != null) {
            System.out.println(newHead == head);
            newHead = newHead.next;
            head = head.next;
        }
    }

    public static class RandomListNode {
        private RandomListNode next;
        private RandomListNode extra;
        public int data;

        RandomListNode(int data) {
            this.data = data;
        }

        //显示此节点
        @NonNull
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            RandomListNode node = this;
            while (node != null) {
                sb.append("data:")
                        .append(node.data)
                        .append(" next:")
                        .append(node.next != null ? node.next.data : "null")
                        .append(" extra:")
                        .append(node.extra != null ? node.extra.data : "null")
                        .append("\n");
                node = node.next;
            }
            return sb.toString();
        }
    }

    private static RandomListNode copyRandomList3(RandomListNode head) {
        RandomListNode cur = head;
        Map<RandomListNode, RandomListNode> map = new LinkedHashMap<>();
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.data));
            cur = cur.next;
        }
        cur = head;

        while (cur != null) {
            RandomListNode node = map.get(cur);
            assert node != null;
            node.next = map.get(cur.next);
            node.extra = map.get(cur.extra);
            cur = cur.next;
        }
        return map.get(head);
    }
}
