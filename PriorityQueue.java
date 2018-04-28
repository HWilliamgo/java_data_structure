package DataStructure;

import java.util.NoSuchElementException;

/**
 * 这是一个用数组实现的优先队列。
 *
 * 该思路参考《数据结构Java语言描述》的page249。
 *
 * 入队时通过指定优先级参数添加到优先队列，出队时，不同优先级之间优先级较高的先出队，
 * 相同优先级，先入先出。
 *
 * 内部维护了一个队列数组，靠队列数组来简单地实现不同优先级队列的读写
 * 当初始化时指定一个最高优先级，然后用for循环一次性把数组中的对象都new出来。
 * add方法额外传入一个int表示优先级，然后利用这个int来访问数组中的元素，也便访问到对应优先级的队列了
 * remove同理。
 * @param <T>
 */
public class PriorityQueue<T> {

    private int highestPriority;//优先级从0到highestPriority
    private MyLoopQueue[] queueArray;//MyLoopQueue是用数组实现的，不可自动扩容的循环队列。
    private int currentMaxPriority;//当前最高的优先级。

    public PriorityQueue(int highestPriority,int capacityOfEachPriority){
        queueArray=new MyLoopQueue[highestPriority+1];
        this.highestPriority=highestPriority;
        currentMaxPriority=0;
        for (int i=0;i<=highestPriority;i++){
            queueArray[i]=new MyLoopQueue(capacityOfEachPriority);
        }
    }

    public void add(T element, int priority) {
        if (priority>highestPriority||priority<0){
            try {
                throw new Exception("输入的优先级不合法，最大优先级为："+highestPriority+"最小优先级为：0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (priority>currentMaxPriority){
            currentMaxPriority=priority;
        }
        MyLoopQueue q=queueArray[priority];
        q.add(element);
    }

    public T remove() {
        MyLoopQueue q=queueArray[currentMaxPriority];
        while (q.isEmpty() && currentMaxPriority>0){
            q=queueArray[--currentMaxPriority];
        }
        T answer= (T) q.remove();

        return answer;
    }

    public T peek() {
        return (T) queueArray[currentMaxPriority].peek();
    }
}  
