package homework4;


/**
 * двусвязный список
 */
public class DoublyLinkedList {
    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    private boolean isEmpty() {
        return head == null;
    }

    private void addFirst(Object data) {
        Node temp = new Node(data);

        if (isEmpty()) {
            tail = temp;
        } else {
            head.prev = temp;
        }
        temp.next = head;
        head = temp;
    }

    public void addLast(Object data) {
        Node temp = new Node(data);
        if (isEmpty()) {
            head = temp;
        } else {
            tail.next = temp;
        }
        temp.prev = tail;
        tail = temp;
    }

    public void addByIndex(Object data, int idx) {
        Node current = head;
        int c = 0;
        while (current != null && c != idx) {
            current = current.next;
            c++;
        }
        Node temp = new Node(data);
        current.next = temp;
        temp.prev = current.prev;
        current.prev = temp;
        temp.next = current;
    }

    public void removeFirst() {
        Node temp = head;

        if (head.next == null) {
            tail = null;
        } else {
            head.next.prev = null;
        }
        head = head.next;
    }

    public void removeLast() {

        if (head.next == null)
            head = null;
        else
            tail.prev.next = null;
        tail = tail.prev;
    }

    public void print() {
        Node temp = head;

        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static class Node {
        public Object data;
        public Node next;
        public Node prev;

        public Node(Object data) {
            this.data = data;
        }
    }

    /**
     * класс итератора на двусвязном списке
     */
   public static class DoublyLinkedListInterator {
        private Node current;
        private Node prev;
        private Node next;
        private final DoublyLinkedList list;

        public DoublyLinkedListInterator(DoublyLinkedList list) {
            this.list = list;
        }

        public boolean hasNext() {
            return (current.next == null);
        }

        public DoublyLinkedList next() {
            prev = current;
            current = current.next;
            return list;
        }
    }

    /**
     * Очередь использующая двусвязный список
     * пока не понимаю как сделать
     */
    private static class Queue {
        private int maxSize;
        private int[] queue;
        private int head;
        private int tail;
        private int items;

        public Queue(int s) {
            maxSize = s;
            queue = new int[maxSize];
            head = 0;
            tail = -1;
            items = 0;
        }

        public boolean isEmpty() { return (items == 0); }
        public boolean isFull() { return (items == maxSize); }
        public int size() { return items; }

        public void insert(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail >= head) {
                    System.arraycopy(queue, 0, tmpArr, 0, queue.length);
                } else {
                    System.arraycopy(queue, 0, tmpArr, 0, tail + 1);
                    System.arraycopy(queue, head, tmpArr,
                            maxSize - (queue.length - head), queue.length - head);
                    head = maxSize - head - 1;
                }
            }
            if (tail == maxSize - 1) {
                tail = -1;
            }
            queue[++tail] = i;
            ++items;
        }

        public int remove() {
            int temp =  queue[head++];
            head %= maxSize;
            items--;
            return temp;
        }

        public int peek(){
            return queue[head];
        }
    }


    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        doublyLinkedList.addFirst(1);
        doublyLinkedList.addFirst(2);
        doublyLinkedList.addLast(3);
        doublyLinkedList.print();
    }


}
