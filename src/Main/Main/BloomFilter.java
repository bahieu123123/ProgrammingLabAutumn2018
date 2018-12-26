package Main;

import java.util.*;

public class BloomFilter implements Set {
    byte[] set;
    private int keySize, setSize, size;
    List listElm = new ArrayList();


    /* Constructor */
    public BloomFilter(int capacity, int k) {
        setSize = capacity;
        set = new byte[setSize];
        keySize = k;
        size = 0;

    }

    /* Function to clear bloom set */
    public void makeEmpty() {
        set = new byte[setSize];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    /* Function to check is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return check(o);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        for (int i = 0; i < size; i++) {
            a[i] = listElm.get(i);
        }
        return a;
    }

    @Override
    public boolean add(Object o) {
        if (!check(o)) {
            this.addElement(o);
            return true;
        } else return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        Object[] elementData = toArray();
        if (a.length < size) {
            return (Object[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /* Function to get size of objects added */
    public int getSize() {
        return size;
    }

    /* Function to get hash */
    int getHash(int i) {
        return Math.abs(Integer.hashCode(i)) % (set.length - 1);
    }

    /* Function to add an object */
    public void addElement(Object obj) {
        int[] tmpset = getSetArray(obj);
        for (int i : tmpset) {
            set[i] = 1;
        }
        listElm.add(obj);
        size++;
    }

    @Override
    public boolean remove(Object o) {
        if (check(o)) {
            listElm.remove(o);
            return true;
        } else return false;
    }

    @Override
    public boolean addAll(Collection c) {
        boolean check = false;
        for (Object i : c) {
            if (this.add(i)) check = true;
        }
        return check;
    }

    @Override
    public void clear() {
        listElm = null;
        size = 0;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean check = false;
        for (Object i : c) {
            if (this.contains(i)) {
                this.remove(i);
                check = true;
            }
        }
        return check;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean check = false;
        for (Object i : listElm) {
            if (c.contains(i)) continue;
            else {
                this.remove(i);
                check = true;
            }
        }
        return check;
    }

    @Override
    public boolean containsAll(Collection c) {
        for(Object i : c){
            if(!contains(i))return false;
        }
        return true;
    }

    /* Function to check is an object is present */
    public boolean check(Object obj) {
        int[] tmpset = getSetArray(obj);
        for (int i : tmpset) {
            if (set[i] != 1)
                return false;
        }
        return true;
    }

    /* Function to get set array for an object */
    int[] getSetArray(Object obj) {
        int[] tmpset = new int[keySize];
        tmpset[0] = obj.hashCode();
        for (int i = 1; i < keySize; i++)
            tmpset[i] = (getHash(tmpset[i - 1]));
        return tmpset;
    }

}