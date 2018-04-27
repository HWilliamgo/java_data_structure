package DataStructure;

import java.util.EmptyStackException;

public class ArrayStack <T> implements Cloneable {
    private T[] data;
    private int cursor;

    public ArrayStack() {
        this(10);
    }
    public ArrayStack(int initialCapacity){
        if (initialCapacity<0){
            throw new IllegalArgumentException("数组初始化长度小于0："+initialCapacity);
        }
        cursor=0;
        data=data=(T[])new Object[initialCapacity];
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayStack<T> answer;
        try{
            answer= (ArrayStack<T>) super.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("该类没有实现Cloneable接口");
        }
        answer.data=this.data.clone();
        return answer;
    }
    public void ensureCapacity(int minimumCapacity){
        if(data.length<minimumCapacity){
            T[] biggerArray= (T[]) new Object();
            System.arraycopy(data,0,biggerArray,0,cursor);
            data=biggerArray;
        }
    }
    public int getCapacity(){
        return data.length;
    }
    public boolean isEmpty(){
        return cursor==0;
    }
    public T peek(){
        if (cursor==0){
            throw new EmptyStackException();
        }
        return data[cursor-1];
    }
    public T pop(){
        if (cursor==0){
            throw new EmptyStackException();
        }
        T answer=data[--cursor];
        data[cursor]=null;
        return answer;
    }
    public int push(T d){
        if (cursor==data.length){
            ensureCapacity(cursor*2+1);
        }
        data[cursor++]=d;
        return cursor;
    }
    public int size(){
        return cursor;
    }
    public void trimToSize(){
        if (data.length!=cursor){
            T[] trimArray=(T[])new Object[cursor];
            System.arraycopy(data,0,trimArray,0,cursor);
            data=trimArray;
        }
    }
}
