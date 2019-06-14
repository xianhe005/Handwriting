package com.hxh.handwriting;

import com.hxh.handwriting.leetcode.InversionLinkedList;
import com.hxh.handwriting.leetcode.MergeKList;
import com.hxh.handwriting.leetcode.MergeTwoSortedList;
import com.hxh.handwriting.leetcode.ParityLinkedList;
import com.hxh.handwriting.leetcode.PartitionLinkedList;
import com.hxh.handwriting.leetcode.RemoveLinkedListVal;
import com.hxh.handwriting.leetcode.RemoveNthFromEnd;
import com.hxh.handwriting.leetcode.ReverseBetween;
import com.hxh.handwriting.leetcode.Sort;
import com.hxh.handwriting.leetcode.SwapPairs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void inversionListTest() {
        InversionLinkedList.test();
    }

    @Test
    public void removeLinkedListValTest() {
        RemoveLinkedListVal.test();
    }

    @Test
    public void parityLinkedListTest() {
        ParityLinkedList.test();
    }

    @Test
    public void mergeTwoSortedList() {
        MergeTwoSortedList.test();
    }

    @Test
    public void mergeKList() {
        MergeKList.test();
    }

    @Test
    public void removeNthFromEnd() {
        RemoveNthFromEnd.test();
    }

    @Test
    public void swapPairs() {
        SwapPairs.test();
    }

    @Test
    public void partitionLinkedList() {
        PartitionLinkedList.test();
    }

    @Test
    public void reverseBetween() {
        ReverseBetween.test();
    }

    @Test
    public void sortTest() {
        Sort.test();
    }
}