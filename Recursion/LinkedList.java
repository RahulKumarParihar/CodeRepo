class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> previous;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void insertHead(T data) {
        Node<T> newHead = new Node<T>(data);
        if (head == null) {
            head = newHead;
            tail = newHead;
        } else {
            head.setPrevious(newHead);
            newHead.setNext(head);
            head = newHead;
        }
    }

    public void insertTail(T data) {
        Node<T> newTail = new Node<T>(data);
        if (tail == null) {
            head = newTail;
            tail = newTail;
        } else {
            newTail.setPrevious(tail);
            tail.setNext(newTail);
            tail = newTail;
        }
    }

    public void print() {
        printRecursion(head);
    }

    private void printRecursion(Node<T> head) {
        if (head == null)
            return;

        System.out.print(head.getData() + " ");

        printRecursion(head.getNext());
    }

    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.insertHead(1);
        list.insertTail(2);
        list.insertTail(3);
        list.insertTail(4);

        list.print();
    }
}



