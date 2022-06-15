package homework3;

public class Main {

    private static class Stack {
        private int maxSize;
        private int[] stack;
        private int head;

        public Stack(int size) {
            this.maxSize = size;
            this.stack = new int[this.maxSize];
            this.head = -1;
        }

        public boolean isEmpty() {
            return this.head == -1;
        }

        public boolean isFull() {
            return this.head == this.maxSize - 1;
        }

        public void push(int i) {
            if (isFull()) {
                this.maxSize *= 2;
                int[] newStack = new int[this.maxSize];
                System.arraycopy(this.stack, 0, newStack, 0, this.stack.length);
                this.stack = newStack;
            }
            this.stack[++this.head] = i;
        }

        public int pop() {
            if (isEmpty()) throw new RuntimeException("Stack is empty"); //ret -1
            return this.stack[this.head--];
        }

        public int peek() {
            return this.stack[this.head];
        }
    }

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

        public boolean isEmpty() {
            return (items == 0);
        }

        public boolean isFull() {
            return (items == maxSize);
        }

        public int size() {
            return items;
        }

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
            int temp = queue[head++];
            head %= maxSize;
            items--;
            return temp;
        }

        public int peek() {
            return queue[head];
        }
    }

    /**
     * двусторонняя очередь
     * insertToTail добавляет элемент в конец очереди
     * если массив переполнен увеличиваем его максимальный размер вдвое
     * <p>
     * insertToHead добавляет элемент в голову очереди
     * removeFromHead удаляет значение с головы очереди
     * removeFromTail удаляет элемент с конца очереди
     * peekFirst возвращает значение первого элемента
     * peekLast возвращает значение последнего элемента
     */
    private static class Deque {
        private int maxSize;
        private int[] deque;
        private int head;
        private int tail;
        private int items;

        public Deque(int s) {
            maxSize = s;
            deque = new int[maxSize];
            head = 0;
            tail = -1;
            items = 0;
        }

        public boolean isEmpty() {
            return (items == 0);
        }

        public boolean isFull() {
            return (items == maxSize);
        }

        public int size() {
            return items;
        }

        public void insertToTail(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail >= head) {
                    System.arraycopy(deque, 0, tmpArr, 0, deque.length);
                } else {
                    System.arraycopy(deque, 0, tmpArr, 0, tail + 1);
                    System.arraycopy(deque, head, tmpArr,
                            maxSize - (deque.length - head), deque.length - head);
                    head = maxSize - head - 1;
                }
            }
            if (tail == maxSize - 1) {
                tail = -1;
            }
            deque[++tail] = i;
            ++items;
        }

        public void insertToHead(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (head >= tail) {
                    System.arraycopy(deque, 0, tmpArr, 0, deque.length);
                } else {
                    System.arraycopy(deque, 0, tmpArr, 0, head + 1);
                    System.arraycopy(deque, tail, tmpArr,
                            maxSize - (deque.length - tail), deque.length - tail);
                    tail = maxSize - tail - 1;
                }
            }
            if (tail == maxSize - 1) {
                tail = -1;
            }
            deque[++head] = i;
            ++items;
        }

        public int removeFromHead() {
            int temp = deque[head++];
            head %= maxSize;
            items--;
            return temp;
        }

        public int removeFromTail() {
            int temp = deque[tail++];
            tail %= maxSize;
            items--;
            return temp;
        }

        public int peekFirst() {
            return deque[head];
        }

        public int peekLast() {
            return deque[tail];
        }
    }

    private static class PriorityQueue {
        private int maxSize;
        private int[] queue;
        private int head;
        private int tail;
        private int items;
        private int priority;


        public PriorityQueue(int s, int p) {
            maxSize = s;
            queue = new int[maxSize];
            head = 0;
            tail = -1;
            items = 0;
            priority = p;
        }

        public boolean isEmpty() {
            return (items == 0);
        }

        public boolean isFull() {
            return (items == maxSize);
        }

        public int size() {
            return items;
        }

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
            int temp = queue[head++];
            head %= maxSize;
            items--;
            return temp;
        }

        public int peek() {
            return queue[head];
        }
    }


    public static void main(String[] args) {

    }
}

// public static void check(String input) {...}

