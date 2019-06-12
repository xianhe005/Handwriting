package com.hxh.handwriting;

import com.hxh.handwriting.leetcode.InversionLinkedList;
import com.hxh.handwriting.leetcode.ParityLinkedList;
import com.hxh.handwriting.leetcode.RemoveLinkedListVal;

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
}