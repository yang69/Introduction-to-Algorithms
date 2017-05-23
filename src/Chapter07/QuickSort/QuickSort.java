package Chapter07.QuickSort;

/**
 * Created by Yang on 2017/5/23.
 ************************************************************************************************
 * 快速排序
 *
 * 最坏情况时间复杂度为O(n^2)，平均性能非常好，期望运行时间复杂度是O(nlgn)。
 ************************************************************************************************
 */
public class QuickSort {
    public void sort(int[] a) {
        sort(a, 0, a.length-1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int j = partition(a, lo, hi);
            sort(a, lo, j-1);
            sort(a, j+1, hi);
        }
    }

    private int partition(int[] a, int lo, int hi) {
        // 随机选择一个元素作为主元(pivot element)，使得算法对于所有的输入都能获得较好的期望性能
        int pivot = lo + (int)((hi-lo)*Math.random());
        swap(a, lo, pivot); // 将主元放到a[lo]
        int i = lo; // 从左向右扫描，指向小于主元a[lo]的元素
        int j = hi + 1; // 从右向左扫描，指向大于主元a[lo]的元素
        while (true) {
            while (a[++i] < a[lo])
                if (i == hi) break;
            while (a[--j] > a[lo])
                if (j == lo) break;
            if (i >= j) break;

            swap(a, i, j);
        }

        swap(a, lo, j); // 将主元放到a[j]
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        int[] array = new int[]{5,8,9,4,1,6,3,5,2,2,4};
        quickSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        array = new int[]{5,4};
        quickSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        array = new int[]{5};
        quickSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        array = new int[]{};
        quickSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
