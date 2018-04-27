package DataStructure;
import java.util.NoSuchElementException;

public class MyLoopQueue<T> {
    private T[] array;

    private int front;

    private int len;

    private int rear;

    @SuppressWarnings("unchecked")
    public MyLoopQueue(int capacity) {
        array = (T[]) new Object[capacity];
        len = capacity;
        front = 0;
        rear = 0;
    }
    public MyLoopQueue(){
        this(10);
    }

//    @SuppressWarnings("unchecked")
//    private void ensureCapacity(int capacity) {
//        T[] newArray = (T[]) new Object[capacity];
//
//        System.arraycopy(array, 0, newArray, 0, len);
//        array = newArray;
//        //以上，array指向了新建的对象，并且成功完成赋值。
//        len = capacity;
//    }



    public void add(T element) {
        if (isFull()) {
            throw new NoSuchElementException("队列已满！");
        }
        array[rear++] = element;
        rear = rear == len ? 0 : rear;  //不能在add(element)里面直接动态扩容的原因：
                                        //问题出在这一句，如果添加到最后一个下标，rear将==0。但是下一次调用add(element)时，
                                        // 会将element放入0的位置，并覆盖之前的所有数据。
    }

    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列已空！");
        }
        T answer = array[front];
        array[front++]=null;

        front = front == len ? 0 : front;
        return answer;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public boolean isFull() {
        return size()==len;
    }

    public int size() {
        if (rear == front) {
            return array[front] == null ? 0 : len;
        } else if (rear > front) {
            return rear - front;
        } else {
            return rear + len - front;
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列为空！");
        }
        return array[front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (isEmpty()) {
            sb.append("]");
            return sb.toString();
        }

        for (int i = 0; i < size(); i++) {
            T a = array[((front + i) % len)];
            sb.append(a.toString());
            if (i!=size()-1){
                sb.append(" , ");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
