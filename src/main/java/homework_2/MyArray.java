package homework_2;

public class MyArray {

    private int[] arr;
    private int capacity;

    //new int[5];
    public MyArray(int size) {
        this.capacity = 0;
        this.arr = new int[size];
    }

    // = {1,2,3,4,5};
    public MyArray(int[] init) {
        this.capacity = init.length;
        this.arr = init;
    }

    void display() {
        for (int i = 0; i < this.capacity; ++i) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    public int get(int idx) {
        return arr[idx];
    }

    public void set(int value, int idx) {
        arr[idx] = value;
    }

    boolean delete(int value) {
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i] == value) {
                System.arraycopy(this.arr, i + 1, this.arr, i, this.capacity - i - 1);
                --capacity;
                return true;
            }
        }
        return false;
    }

    /**
     * проходимся по массиву и удаляем все значения = value
     *
     * @param value значение которое нужн удалить из массива
     * @return возвращаем true если внесли изменение и false в противном случае
     */
    public boolean deleteAll(int value) {
        boolean success = false;
        for (int i = 0; i < capacity; i++) {
            if (arr[i] == value) {
                delete(i--);
                success = true;
            }
        }
        return success;
    }

    /**
     * Присваиваем значение емкости 0
     */
    public void deleteAll() {
        capacity = 0;
    }

    /**
     * вставляем значение по индексу
     * значения последующие перемещаем на следующую ячейку
     * если емкость меньше нового массива увеличиваем емкость
     *
     * @param idx   индекс на который нужно вставить значение
     * @param value значение которое нужно вставить
     */
    void insert(int idx, int value) {
        int[] temp = arr;
        if(capacity>=arr.length) {
            arr = new int [capacity * 2];
            System.arraycopy(temp, 0, arr, 0, idx);
        }
        System.arraycopy(temp, idx, arr, idx+1, capacity);
        arr[idx] = value;
        capacity++;
    }


    void append(int value) {
        if (this.capacity == this.arr.length) {
            int[] old = this.arr;
            this.arr = new int[old.length * 2];
            System.arraycopy(old, 0, arr, 0, old.length);
        }
        this.arr[this.capacity++] = value;
    }

    public boolean isInArray(int value) { // O(n)
        for (int i = 0; i < this.capacity; i++)
            if (this.arr[i] == value)
                return true;
        return false;
    }

    //O(log(N))
    public boolean hasValue(int value) {
        int low = 0;
        int high = this.capacity - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (value == this.arr[mid]) {
                return true;
            } else {
                if (value < this.arr[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return false;
    }

    private void swap(int a, int b) {
        int tmp = this.arr[a];
        this.arr[a] = this.arr[b];
        this.arr[b] = tmp;
    }

    public void sortBubble() {
        int count = 0;
        for (int iter = 0; iter < capacity; iter++){
            for (int idx = 0; idx < capacity - 1; idx++) {
                if (this.arr[idx] > this.arr[idx + 1]){
                    swap(idx, idx + 1);
                }
                count++;
            }

        }
        System.out.println(count);
    }

    /**
     *после каждого прохода цикла внутренний цикл уменьшаем на значение iter
     * тем самым не трогая уже отсортированную часть
     */
    public void sortBubbleExceptAlreadySorted() {
        int count = 0;
        for (int iter = 0; iter < capacity; iter++){
            for (int idx = 0; idx < capacity - iter - 1; idx++) {
                if (this.arr[idx] > this.arr[idx + 1]){
                    swap(idx, idx + 1);
                }
                count++;
            }
        }
        System.out.println(count);
    }

    public void sortSelect() {
        int count = 0;
        for (int idx = 0; idx < capacity; idx++) {
            int curr = idx;
            for (int srch = idx + 1; srch < capacity; srch++){
                count++;
                if (this.arr[srch] < this.arr[curr])
                    curr = srch;
           }
            if (curr != idx)
                swap(idx, curr);
        }
        System.out.println(count);
    }

    public void sortInsert() {
        int count = 0;
        for (int curr = 1; curr < capacity; curr++) {
            int temp = this.arr[curr];
            int move = curr;
            while (move > 0 && this.arr[move - 1] >= temp) {
                this.arr[move] = this.arr[move - 1];
                move--;
            }
            this.arr[move] = temp;
            count++;
        }
        System.out.println(count);
    }
}
