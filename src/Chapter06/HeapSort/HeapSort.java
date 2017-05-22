package Chapter06.HeapSort;

/**
 * Created by Yang on 2017/5/22.
 ************************************************************************************************
 * 堆排序
 *
 * 二叉堆是一个数组，它可以看成一个近似的完全二叉树。树上的每一个结点对应数组中的一个元素。除了最底
 * 层外，该树是完全充满的，而且是从左向右填充。表示堆的数组A包括两个属性：A.length 给出数组元素的个
 * 数，A.heapSize 表示有多少个堆元素存储在该数组中。也就是说，虽然A[1...A.length]可能都存有数据，但
 * 只有A[1...A.heapSize]中存放的是堆的有效元素。树的根结点是A[1]，这样给定一个结点的下标，很容易可
 * 以计算得到它的父结点、左子结点和右子结点的下标：
 * parent(i) = ⌊i/2⌋， left(i) = 2*i, right(i) = 2*i + 1;
 ************************************************************************************************
 */
public class HeapSort {
    /**
     * 下标为i的结点的父结点的下标。
     * PS：下标从0开始
     * @param i
     * @return
     */
    private int parent(int i) {
        return ((i+1) >> 1) - 1;
    }

    /**
     * 下标为i的结点的左子结点的下标。
     * PS：下标从0开始
     * @param i
     * @return
     */
    private int left(int i) {
        return ((i+1) << 1) - 1;
    }

    /**
     * 下标为i的结点的右子结点的下标。
     * PS：下标从0开始
     * @param i
     * @return
     */
    private int right(int i) {
        return ((i+1) << 1);
    }

    private void maxHeapify(int[] A, int heapSize, int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l < heapSize && A[l] > A[largest]) {
            largest = l;
        }
        if (r < heapSize && A[r] > A[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(A, i, largest); // 交换A[i]与A[largest]
            maxHeapify(A, heapSize, largest);
        }
    }

    /**
     * 把数组A转化为最大堆
     * @param A
     */
    private void buildMaxHeap(int[] A) {
        int heapSize = A.length;
        for (int i = A.length >> 1; i >= 0; i--) {
            maxHeapify(A, heapSize, i);
        }
    }

    /**
     * 先将A建成最大堆，然后将根结点交换到A的最后，这样最大值就交换到了正确的位置。
     * 然后将堆的大小减1，将剩下的堆重建为最大堆，并将此时的最大堆交换到堆尾，重复
     * 此过程，知道堆的大小降为2。
     * @param A
     */
    public void heapSort(int[] A) {
        buildMaxHeap(A);
        for (int i = A.length-1; i > 0; i--) {
            swap(A, i, 0); // 将最大值交换到末尾
            maxHeapify(A, i, 0); //此时，只有A[0]可能不满足最大堆，将A[0]下沉到适当位置即可保证A[0...i-1]是最大堆。
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();

        int[] array = new int[]{5,8,9,4,1,6,3,5,2,2,4};
        heapSort.heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        array = new int[]{5,4};
        heapSort.heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        array = new int[]{5};
        heapSort.heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        array = new int[]{};
        heapSort.heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
