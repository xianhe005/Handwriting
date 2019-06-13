package com.hxh.handwriting.leetcode;

import java.util.Arrays;

/**
 * Created by HXH at 2019/6/13
 * 排序
 */
public class Sort {

    public static void test() {
        int size = 10000;
        int[] array = new int[size];
        for (int i = size; i > 0; i--) {
            array[size - i] = i;
        }
        long cur = System.currentTimeMillis();
        int[] a = bubbleSort(array);
        sortLog(a, "冒泡排序", cur);

        cur = System.currentTimeMillis();
        a = selectionSort(array);
        sortLog(a, "选择排序", cur);

        cur = System.currentTimeMillis();
        a = insertSort(array);
        sortLog(a, "插入排序", cur);

        cur = System.currentTimeMillis();
        a = shellSort(array);
        sortLog(a, "希尔排序", cur);

        cur = System.currentTimeMillis();
        a = mergeSort(array);
        sortLog(a, "归并排序", cur);

        cur = System.currentTimeMillis();
        a = QuickSort.sort(array);
        sortLog(a, "快速排序", cur);
    }

    private static void sortLog(int[] a, String sortName, long cur) {
        System.out.print(sortName + (System.currentTimeMillis() - cur) + "ms:");
        for (int i : a) {
            System.out.print(i + ",");
        }
        System.out.println();
    }

    /**
     * 冒泡排序
     * <p>
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * <p>
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * <p>
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * <p>
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    private static int[] bubbleSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                    flag = false;
                }
                // 4 test
                /*System.out.print("jj:i=" + i + " j=" +j+ ":");
                for (int ii : arr) {
                    System.out.print(ii + ",");
                }
                System.out.println();*/
            }
            // 4 test
            /*System.out.print("ii:i=" +i + " flag:" + flag + ":");
            for (int ii : arr) {
                System.out.print(ii + ",");
            }
            System.out.println();*/
            if (flag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * <p>
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * <p>
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * <p>
     * 重复第二步，直到所有元素均排序完毕。
     */
    private static int[] selectionSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * <p/>
     * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     * <p/>
     * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
     */
    private static int[] insertSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }

    /**
     * 希尔排序
     * <p/>
     * 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
     * <p/>
     * 按增量序列个数 k，对序列进行 k 趟排序；
     * <p/>
     * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     */
    private static int[] shellSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3f);
        }
        return arr;
    }

    /**
     * 归并排序
     * <p/>
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
     * <p/>
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
     * <p/>
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
     * <p/>
     * 重复步骤 3 直到某一指针达到序列尾；
     * <p/>
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     */
    private static int[] mergeSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2f);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    /**
     * 快速排序
     * <p/>
     * 从数列中挑出一个元素，称为 “基准”（pivot）;
     * <p/>
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * <p/>
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     */
    private static class QuickSort {

        static int[] sort(int[] sourceArray) {
            // 对 arr 进行拷贝，不改变参数内容
            int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

            return quickSort(arr, 0, arr.length - 1);
        }

        private static int[] quickSort(int[] arr, int left, int right) {
            if (left < right) {
                int partitionIndex = partition(arr, left, right);
                quickSort(arr, left, partitionIndex - 1);
                quickSort(arr, partitionIndex + 1, right);
            }
            return arr;
        }

        private static int partition(int[] arr, int left, int right) {
            int index = left + 1;
            for (int i = index; i <= right; i++) {
                if (arr[i] < arr[left]) {
                    swap(arr, i, index);
                    index++;
                }
            }
            swap(arr, left, index - 1);
            return index - 1;
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }
}
