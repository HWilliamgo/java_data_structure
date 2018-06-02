package DataStructureAndAlgor;

import java.nio.BufferUnderflowException;

/**
 * 由数组实现的堆，小根堆。
 * <p>
 * 堆的定义：
 * 1. 堆中某个节点的值总是不大于或不小于其父节点的值（将根节点最大的堆叫做最大堆或大根堆，根节点最小的堆叫做最小堆或小根堆）
 * 2. 堆总是一棵完全二叉树
 * <p>
 * 当堆用数组实现：
 * 1. 位置0不放元素，从位置1开始放。
 * 2. 数组任一位置i上的元素，左子树在位置2i，右子树在位置(2i+1)，父结点在[i/2]上。
 *
 * @param <T>
 */
/*
    回顾的时候要画出这个模型，图上的数字代表数组下标，0下标为null。
                1
             /      \
            2         3
          /  \       / \
         4     5    6    7
        / \    / \
       8  9   10 11
 */
public class BinaryHeap<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private T[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity + 1];
    }

    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (T item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    public void insert(T x) {
        //通过上滤（percolate up），将新元素在堆中上滤直到找出正确位置。时间复杂度：O(logN)
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }
        int hole = ++currentSize;
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2) {/*(hole/2)表示父结点的位置*/
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    public T findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return array[1];//array[0]是不放元素的，array[1]才是root
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        T minItem = findMin();
        array[1] = array[currentSize--];//直接将最后一个元素覆盖第一个元素
        //TODO :是否需要array[currentSize+1]=null;?
        percolateDown(1);//覆盖之后，开始“下滤”
        return minItem;
    }

    private boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    private void percolateDown(int hole) {//   O(logN)
        int child;
        T tmp = array[hole];//要"下滤"的元素。

        for (; hole * 2 <= currentSize; hole = child) {//hole*2<=currentSize是保证左子树下标不越界;
            child = hole * 2;                      //hole=child是下滤的直接操作。
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) {//TODO：可以测试一下第一个判断条件的必要性
                child++;//如果右子树<左子树，下标换到右子树去。
            }
            if (array[child].compareTo(tmp) < 0) {//如果孩子结点小于父结点
                array[hole] = array[child];//将孩子结点"上移"，hole"下滤"。
            } else {
                break;
            }
        }
        array[hole] = tmp;

    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {//除以二是因为后面的都是完全二叉树叶子结点，不需要下滤。
            percolateDown(i);
        }
    }

    private void enlargeArray(int newSize) {
        T[] old = array;
        array = (T[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }

}

