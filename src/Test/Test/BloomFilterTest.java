package Test;

import Main.BloomFilter;
import org.junit.jupiter.api.Test;


import java.util.HashSet;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class BloomFilterTest
{
    int capacity=100;
    int k=3;
    BloomFilter bloomFilter1=new BloomFilter(capacity,k);
    @Test
    void makeEmptyTest(){
        bloomFilter1.addElement(1);
        bloomFilter1.addElement(2);
        bloomFilter1.addElement(3);
        bloomFilter1.addElement(4);
        bloomFilter1.addElement(5);
        assertFalse(bloomFilter1.isEmpty());
        bloomFilter1.makeEmpty();
        assertTrue(bloomFilter1.isEmpty());
    }
    @Test
    void getSizeTest(){
        for (int i=0;i<20;i++){
            bloomFilter1.addElement(i);
        }
        assertEquals(20,bloomFilter1.getSize());
        bloomFilter1.addElement(50);
        assertEquals(21,bloomFilter1.getSize());
        bloomFilter1.makeEmpty();
        assertEquals(0,bloomFilter1.getSize());
    }
    @Test
    void addElementTest(){
        for (int i=10;i<20;i++){
            bloomFilter1.addElement(i);
        }
        assertTrue(bloomFilter1.check(10));
        assertTrue(bloomFilter1.check(15));
        assertFalse(bloomFilter1.check(50));
        assertFalse(bloomFilter1.check(3));

    }
    @Test
    void checkTest(){
        bloomFilter1.addElement(0);
        bloomFilter1.addElement(2);
        bloomFilter1.addElement(7);
        assertTrue(bloomFilter1.check(0));
        assertTrue(bloomFilter1.check(7));
        assertFalse(bloomFilter1.check(10));
        assertFalse(bloomFilter1.check(15));
    }
    @Test
    void containTest(){
        bloomFilter1.add(1);
        bloomFilter1.add(2);
        assertTrue(bloomFilter1.contains(1));
        assertFalse(bloomFilter1.contains(3));
    }
    @Test
    void toArrayTest(){
        bloomFilter1.add(5);
        bloomFilter1.add(10);
        bloomFilter1.add(15);
        bloomFilter1.add(20);
        Object[] a=new Object[4];
        a[0]=5;
        a[1]=10;
        a[2]=15;
        a[3]=20;
        assertArrayEquals(bloomFilter1.toArray(),a);
    }
    @Test
    void toArrayTest1(){
        bloomFilter1.add(1);
        bloomFilter1.add(3);
        bloomFilter1.add(5);
        bloomFilter1.add(7);
        Integer a[]=new Integer[]{5,6};
        Integer exp[]=new Integer[]{1,3,5,7};
        assertArrayEquals(bloomFilter1.toArray(a),exp);
    }
    @Test
    void addAll(){
        Set<Integer> set=new HashSet<>();
        set.add(1);
        set.add(10);
        set.add(15);
        assertTrue(bloomFilter1.addAll(set));
    }
    @Test
    void containsAllTest(){
        Set<Integer> set=new HashSet<>();
        set.add(2);
        set.add(4);
        set.add(6);
        bloomFilter1.add(2);
        bloomFilter1.add(4);
        bloomFilter1.add(6);
        assertTrue(bloomFilter1.containsAll(set));
        set.add(7);
        assertFalse(bloomFilter1.containsAll(set));
    }
    @Test
    void clearTest(){
        bloomFilter1.add(2);
        bloomFilter1.add(7);
        assertFalse(bloomFilter1.isEmpty());
        bloomFilter1.clear();
        assertTrue(bloomFilter1.isEmpty());
    }



}

