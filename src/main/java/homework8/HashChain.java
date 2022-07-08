package homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashChain {
    static class Link {
        private final int iData;
        public Link next;

        public Link(int it)
        {
            iData = it;
        }

        public int getKey() {
            return iData;
        }

        public void displayLink()
        {
            System.out.print(iData + " ");
        }
    }

    static class SortedList {
        private Link first;

        public SortedList()
        {
            first = null;
        }

        public void insert(Link theLink)
        {
            int key = theLink.getKey();
            Link previous = null;
            Link current = first;

            while (current != null && key > current.getKey()) {
                previous = current;
                current = current.next;
            }
            if (previous == null)
                first = theLink;
            else
                previous.next = theLink;
            theLink.next = current;
        }

        public void delete(int key)
        {
            Link previous = null;
            Link current = first;
            while (current != null && key != current.getKey()) {
                previous = current;
                current = current.next;
            }
            if (previous == null)
                first = first.next;
            else
                previous.next = current.next;
        }

        public Link find(int key)
        {
            Link current = first;

            while (current != null && current.getKey() <= key) {
                if (current.getKey() == key)
                    return current;
                current = current.next;
            }
            return null;
        }

        public void displayList() {
            System.out.print("List (first-->last): ");
            Link current = first;
            while (current != null)
            {
                current.displayLink();
                current = current.next;
            }
            System.out.println("");
        }
    }

    static class HashTable {
        private final SortedList[] hashArray;
        private final int arraySize;

        public HashTable(int size)
        {
            arraySize = size;
            hashArray = new SortedList[arraySize];
            for (int j = 0; j < arraySize; j++)
                hashArray[j] = new SortedList();
        }

        public void displayTable() {
            for (int j = 0; j < arraySize; j++)
            {
                System.out.print(j + ". ");
                hashArray[j].displayList();
            }
        }

        public int hashFunc(int key)
        {
            return key % arraySize;
        }

        public void insert(Link theLink)
        {
            int key = theLink.getKey();
            int hashVal = hashFunc(key);
            hashArray[hashVal].insert(theLink);
        }

        public void delete(int key)
        {
            int hashVal = hashFunc(key);
            hashArray[hashVal].delete(key);
        }

        public Link find(int key)
        {
            int hashVal = hashFunc(key);
            return hashArray[hashVal].find(key);
        }
    }

    static class HashChainApp {
        public static void main(String[] args) throws IOException {
            int aKey;
            Link aDataItem;
            int size, n, keysPerCell = 100;
            System.out.print("Enter size of hash table: ");
            size = getInt();
            System.out.print("Enter initial number of items: ");
            n = getInt();
            HashTable theHashTable = new HashTable(size);
            for (int j = 0; j < n; j++) // Вставка данных
            {
                aKey = (int) (java.lang.Math.random() *
                        keysPerCell * size);
                aDataItem = new Link(aKey);
                theHashTable.insert(aDataItem);
            }

                        theHashTable.displayTable();
                        aKey = getInt();
                        aDataItem = new Link(aKey);
                        theHashTable.insert(aDataItem);
                        theHashTable.delete(aKey);
                        aDataItem = theHashTable.find(aKey);

        }

        public static String getString() throws IOException {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            return br.readLine();
        }

        public static char getChar() throws IOException {
            String s = getString();
            return s.charAt(0);
        }

        public static int getInt() throws IOException {
            String s = getString();
            return Integer.parseInt(s);
        }
    }
}
