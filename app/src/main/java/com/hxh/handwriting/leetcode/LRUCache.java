package com.hxh.handwriting.leetcode;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HXH at 2019/6/20
 * LeetCode 第 146 号问题：LRU缓存机制
 */
public class LRUCache {

    public static void test() {

        // 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。
        // 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
        // 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
        // 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。
        // 当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

        int val;
        LRUCache cache = new LRUCache(2);
        cache.set(1, 1);
        cache.set(2, 2);
        val = cache.get(1);// 返回  1
        System.out.println(val);
        cache.set(3, 3);    // 该操作会使得密钥 2 作废
        val = cache.get(2);       // 返回 -1 (未找到)
        System.out.println(val);
        cache.set(4, 4);    // 该操作会使得密钥 1 作废
        val = cache.get(1);       // 返回 -1 (未找到)
        System.out.println(val);
        val = cache.get(3);       // 返回  3
        System.out.println(val);
        val = cache.get(4);       // 返回  4
        System.out.println(val);

        System.out.println("=========================================");
        cache = new LRUCache(1);
        cache.set(1, 1);
        cache.set(2, 2);
        val = cache.get(1);// 返回  -1
        System.out.println(val);
        cache.set(3, 3);
        val = cache.get(2);       // 返回 -1 (未找到)
        System.out.println(val);
        cache.set(4, 4);
        val = cache.get(1);       // 返回 -1 (未找到)
        System.out.println(val);
        val = cache.get(3);       // 返回 -1 (未找到)
        System.out.println(val);
        val = cache.get(4);       // 返回  4
        System.out.println(val);
    }

    private DoubleLinkedListNode head;
    private DoubleLinkedListNode end;
    private int capacity;
    private int len;
    @SuppressLint("UseSparseArrays")
    private Map<Integer, DoubleLinkedListNode> map = new HashMap<>();

    private LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must > 1");
        }
        this.capacity = capacity;
    }

    private int get(int key) {
        if (map.containsKey(key)) {
            DoubleLinkedListNode node = map.get(key);
            assert node != null;
            removeNode(node);
            setHead(node);
            return node.val;
        } else {
            return -1;
        }
    }

    private void set(int key, int value) {
        DoubleLinkedListNode node = map.get(key);
        if (node != null) {
            removeNode(node);
            node.val = value;
            setHead(node);
        } else {
            node = new DoubleLinkedListNode(key, value);
            map.put(key, node);
            if (len < capacity) {
                len++;
            } else {
                map.remove(end.key);
                removeNode(end);
            }
            setHead(node);
        }
    }

    private void setHead(DoubleLinkedListNode node) {
        node.next = head;
        node.pre = null;
        if (head != null) {
            head.pre = node;
        }
        head = node;
        if (end == null) {
            end = node;
        }
    }

    private void removeNode(DoubleLinkedListNode node) {
        DoubleLinkedListNode pre = node.pre;
        DoubleLinkedListNode next = node.next;
        if (pre != null) {
            pre.next = next;
        } else {
            head = next;
        }
        if (next != null) {
            next.pre = pre;
        } else {
            end = pre;
        }
    }


    public static class DoubleLinkedListNode {
        DoubleLinkedListNode pre;
        DoubleLinkedListNode next;
        int key;
        private int val;

        DoubleLinkedListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
