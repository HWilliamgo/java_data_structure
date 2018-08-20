package DataStructure;

/**
 * 单链表
 *
 * @param <T> 存储的数据
 */
public class SingleLinkedList<T extends Comparable<T>> {

    private class Node {
        T data;
        Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;

    private int size;

    public SingleLinkedList() {
        Node node = new Node(null, null);
        head = node;
        tail = node;

        size = 0;
    }

    public void addLast(T data) {
        Node node = new Node(data, null);
        tail.next = node;
        tail = node;

        size++;
    }

    public void addFirst(T data) {
        head.next = new Node(data, head.next);
        size++;
    }

    /**
     * 0表示第一个结点
     *
     * @param index 下标
     */
    public void addBefore(int index, T data) {
        if (index < 0 || index > size - 1) {
            return;
        }

        Node cursor = head;
        Node preCursor = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                preCursor = cursor;
            }
            cursor = cursor.next;
        }
        preCursor.next = new Node(data, cursor);
        size++;
    }

    public void addAfter(int index, T data) {
        if (index < 0 || index > size - 1) {
            return;
        }

        Node cursor = head;
        for (int i = 0; i <= index; i++) {
            cursor = cursor.next;
        }
        cursor.next = new Node(data, cursor.next);
        size++;
    }

    public void removeFirst() {
        if (size == 0) {
            return;
        }
        head.next = head.next.next;
        size--;
    }

    public void remove(int index) {
        Node cursor = head;
        Node preCursor = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                preCursor = cursor;
            }
            cursor = cursor.next;
        }
        preCursor.next = cursor.next;
        cursor.next = null;
        size--;

    }

    public void set(int index, T data) {
        Node cursor = head;
        if (index < 0 || index > size - 1) {
            return;
        }
        for (int i = 0; i <= index; i++) {
            cursor = cursor.next;
        }
        cursor.data = data;
    }

    public T get(int index) {
        Node cursor = head;
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i <= index; i++) {
            cursor = cursor.next;
        }
        return cursor.data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        Node cursor = head;
        for (int i = 0; i < size; i++) {
            cursor = cursor.next;
            builder.append(i).append("=").append(cursor.data.toString());
            if (i != size - 1) {
                builder.append(",");
            }
        }
        builder.append("}");

        return builder.toString();
    }
}
