class LinkedListNode<T> {
    private T data;
    private LinkedListNode<T> next;
    private LinkedListNode<T> previous;

    public LinkedListNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public T getData() {
        return data;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public LinkedListNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedListNode<T> previous) {
        this.previous = previous;
    }
}

public class LinkedList<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public LinkedListNode<T> getHead() {
        return head;
    }

    public LinkedListNode<T> getTail() {
        return tail;
    }

    public void insertHead(T data) {
        LinkedListNode<T> newHead = new LinkedListNode<T>(data);
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
        LinkedListNode<T> newTail = new LinkedListNode<T>(data);
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

    private void printRecursion(LinkedListNode<T> head) {
        if (head == null) {
            System.out.println();
            return;
        }

        System.out.print(head.getData() + " ");

        printRecursion(head.getNext());
    }

    public void reverse() {
        head = reverseRecusive(head);
    }

    private LinkedListNode<T> reverseRecusive(LinkedListNode<T> head) {
        if (head == null || head.getNext() == null)
            return head;
        LinkedListNode<T> secondHead = head.getNext();
        head.setNext(null);
        LinkedListNode<T> newHead = reverseRecusive(secondHead);
        secondHead.setNext(head);

        return newHead;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.insertHead(1);
        list.insertTail(2);
        list.insertTail(3);
        list.insertTail(4);

        list.print();

        list.reverse();

        list.print();
    }
}
