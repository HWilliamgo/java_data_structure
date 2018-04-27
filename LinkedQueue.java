package DataStructure;

import java.util.NoSuchElementException;

public class LinkedQueue<T> {

    private class Node{
        private Node next;
        private T element;
        Node(Node next, T element) {
            this.next = next;
            this.element = element;
        }
    }

    private int size;
    private Node headPointer;//指向链表头结点的指针；
    private Node tailPointer;//指向链表尾结点的指针；

public LinkedQueue() {
    Node blankNode=new Node(null,null);//什么也不存储的头结点，用于初始化链表，并同时被头指针和尾指针引用。
    headPointer=blankNode;
    tailPointer=blankNode;
    size=0;
}

public void add(T element) {
    Node newNode=new Node(null,element);
    tailPointer.next=newNode;
    tailPointer=newNode;
    size++;
}

public T remove() {
    if (isEmpty()){
        throw new NoSuchElementException("队列为空！");
    }
    T answer;
    Node first=headPointer.next;//headPointer引用blankNode，就是是头结点，他的下一项才是真正的链表的第一个结点。
    answer=first.element;
    headPointer.next=first.next;
    first.next=null;
    size--;
    return answer;
}

    public boolean isEmpty() {
        return size==0;//return headPointer==tailPointer;
    }

    public int size(){
        return size;
    }

    public T peek(){
        if (isEmpty()){
            throw new NoSuchElementException("队列为空！");
        }
        return tailPointer.element;
    }

    @Override
    public String toString() {
        if (isEmpty()){
            return "[]";
        }
        StringBuilder sb=new StringBuilder("[");
        Node cursor=headPointer.next;//指向第一个有意义的结点。
        for (int i=0;i<size;i++){
            sb.append(cursor.element.toString());
            if (i!=size-1){
                sb.append(",");
            }
            cursor=cursor.next;
        }
        sb.append("]");
        return sb.toString();
    }
}


