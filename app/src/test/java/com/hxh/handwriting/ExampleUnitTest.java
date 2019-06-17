package com.hxh.handwriting;

import com.hxh.handwriting.leetcode.AddTwoNumbers;
import com.hxh.handwriting.leetcode.BracketsValid;
import com.hxh.handwriting.leetcode.InversionLinkedList;
import com.hxh.handwriting.leetcode.IsPalindrome;
import com.hxh.handwriting.leetcode.LengthOfLongestSubstring;
import com.hxh.handwriting.leetcode.MergeKList;
import com.hxh.handwriting.leetcode.MergeTwoSortedList;
import com.hxh.handwriting.leetcode.ParityLinkedList;
import com.hxh.handwriting.leetcode.PartitionLinkedList;
import com.hxh.handwriting.leetcode.PlusOne;
import com.hxh.handwriting.leetcode.RemoveDuplicates;
import com.hxh.handwriting.leetcode.RemoveLinkedListVal;
import com.hxh.handwriting.leetcode.RemoveNthFromEnd;
import com.hxh.handwriting.leetcode.ReverseBetween;
import com.hxh.handwriting.leetcode.Sort;
import com.hxh.handwriting.leetcode.SwapPairs;
import com.hxh.handwriting.leetcode.ThreeSum;
import com.hxh.handwriting.leetcode.TwoSum;

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
    public void twoSum() {
        TwoSum.test();
    }

    @Test
    public void addTwoNumbers() {
        AddTwoNumbers.test();
    }

    @Test
    public void lengthOfLongestSubstring() {
        LengthOfLongestSubstring.test();
    }

    @Test
    public void isPalindrome() {
        IsPalindrome.test();
    }

    @Test
    public void threeSum() {
        ThreeSum.test();
    }

    @Test
    public void bracketsValid() {
        BracketsValid.test();
    }

    @Test
    public void removeDuplicates() {
        RemoveDuplicates.test();
    }


    @Test
    public void plusOne() {
        PlusOne.test();
    }

    @Test
    public void sortTest() {
        Sort.test();
    }
}