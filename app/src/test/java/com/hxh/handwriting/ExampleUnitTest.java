package com.hxh.handwriting;

import com.hxh.handwriting.leetcode.AddTwoNumbers;
import com.hxh.handwriting.leetcode.BalancedNodeTree;
import com.hxh.handwriting.leetcode.BracketsValid;
import com.hxh.handwriting.leetcode.InorderTraversal;
import com.hxh.handwriting.leetcode.InversionLinkedList;
import com.hxh.handwriting.leetcode.IsPalindrome;
import com.hxh.handwriting.leetcode.IsPalindromeStr;
import com.hxh.handwriting.leetcode.IsSymmetric;
import com.hxh.handwriting.leetcode.LengthOfLongestSubstring;
import com.hxh.handwriting.leetcode.LevelOrder;
import com.hxh.handwriting.leetcode.LevelOrderBottom;
import com.hxh.handwriting.leetcode.MaxProfit;
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
import com.hxh.handwriting.leetcode.SortColor;
import com.hxh.handwriting.leetcode.SwapPairs;
import com.hxh.handwriting.leetcode.ThreeSum;
import com.hxh.handwriting.leetcode.TwoSum;
import com.hxh.handwriting.leetcode.YangHuiTriangle;
import com.hxh.handwriting.leetcode.YangHuiTriangle2;
import com.hxh.handwriting.leetcode.ZigzagLevelOrder;

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
    public void sortColor() {
        SortColor.test();
    }

    @Test
    public void inorderTraversal() {
        InorderTraversal.test();
    }

    @Test
    public void isSymmetric() {
        IsSymmetric.test();
    }

    @Test
    public void levelOrder() {
        LevelOrder.test();
    }

    @Test
    public void zigzagLevelOrder() {
        ZigzagLevelOrder.test();
    }

    @Test
    public void levelOrderBottom() {
        LevelOrderBottom.test();
    }

    @Test
    public void yangHuiTriangle() {
        YangHuiTriangle.test();
    }

    @Test
    public void yangHuiTriangle2() {
        YangHuiTriangle2.test();
    }

    @Test
    public void balancedNodeTree() {
        BalancedNodeTree.test();
    }

    @Test
    public void maxProfit() {
        MaxProfit.test();
    }

    @Test
    public void isPalindromeStr() {
        IsPalindromeStr.test();
    }

    @Test
    public void sortTest() {
        Sort.test();
    }
}