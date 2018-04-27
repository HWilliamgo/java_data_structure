package DataStructure;

public class LinkedStack<T> implements Cloneable{
    class Node{
        private Node next;
        private T data;

        public Node(Node next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private int size;

    public LinkedStack() {
        head=new Node(null,null);
        size=0;
    }

    private Node head;

    public void push(T data){
        head.next= new Node(head.next,data);
        size++;
    }
    public T peek(){
        return head.next.data;
    }
    public T pop(){
        T answer=head.next.data;
        head.next=head.next.next;
        size--;
        return answer;
    }
    public int size(){
        return this.size;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //初始化新的LinkedStack
        LinkedStack<T> answer= (LinkedStack<T>) super.clone();
        answer.head=new Node(null,null);
        //创建游标，一个一个遍历 ，并push。
        Node cursor=head.next;
        for (int i=0;i<size;i++){
            answer.push(cursor.data);
            cursor=cursor.next;
        }
        return answer;
    }
}
