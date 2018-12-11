package Test;

import Main.BloomFilter;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class BloomFilterTest
{
    int capacity=100;
    int k=3;
    BloomFilter bloomFilter1=new BloomFilter(capacity,k);
    @Test
    public void makeEmptyTest(){
        bloomFilter1.add(1);
        bloomFilter1.add(2);
        bloomFilter1.add(3);
        bloomFilter1.add(4);
        bloomFilter1.add(5);
        assertFalse(bloomFilter1.isEmpty());
        bloomFilter1.makeEmpty();
        assertTrue(bloomFilter1.isEmpty());
    }
    @Test
    public void getSizeTest(){
        for (int i=0;i<20;i++){
            bloomFilter1.add(i);
        }
        assertEquals(20,bloomFilter1.getSize());
        bloomFilter1.add(50);
        assertEquals(21,bloomFilter1.getSize());
        bloomFilter1.makeEmpty();
        assertEquals(0,bloomFilter1.getSize());
    }
    @Test
    public void addTest(){
        for (int i=10;i<20;i++){
            bloomFilter1.add(i);
        }
        assertTrue(bloomFilter1.check(10));
        assertTrue(bloomFilter1.check(15));
        assertFalse(bloomFilter1.check(50));
        assertFalse(bloomFilter1.check(3));

    }
    @Test
    public void checkTest(){
        bloomFilter1.add(0);
        bloomFilter1.add(2);
        bloomFilter1.add(7);
        assertTrue(bloomFilter1.check(0));
        assertTrue(bloomFilter1.check(7));
        assertFalse(bloomFilter1.check(10));
        assertFalse(bloomFilter1.check(15));
    }


}

