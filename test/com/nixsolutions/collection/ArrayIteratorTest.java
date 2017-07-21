package com.nixsolutions.collection;

import com.nixsolutions.collection.ArrayCollectionImpl;
import interfaces.task5.ArrayCollection;
import junit.framework.TestCase;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Anastasia Sokolan on 13.07.2017.
 */
public class ArrayIteratorTest extends TestCase {
    ArrayCollection<Integer> collection;
    Iterator<Integer> iter;
    Integer[] array = {1, 2, -3, 7, 5, -11};
    public void setUp() throws Exception {
        collection = new ArrayCollectionImpl<Integer>();
        collection.setArray(array);
        iter = collection.iterator();
    }
    public void testHasNext() {
        while (iter.hasNext()) {
            Integer temp = iter.next();
        }
        assertFalse("No any element. But iterator returns true.",
                iter.hasNext());
    }
    public void testNext() {
        assertTrue("Wrong value of element", array[0].equals(iter.next()));
    }
    public void testNextException() {
        Iterator<Integer> iterator = (new
                ArrayCollectionImpl<Integer>()).iterator();
        try {
            iterator.next();
            fail("The test should have failed");
        } catch (NoSuchElementException e) {
            assertTrue("NoSuchElementException must be caught " +
                    "for empty collection", true);
        }
    }
    public void testRemove() {
        Integer val = iter.next();
        iter.remove();
        Iterator<Integer> checkIter = collection.iterator();
        assertTrue("First element must be removed",
                (!val.equals(checkIter.next())));
    }
    public void testRemoveException() {
        Iterator<Integer> iterator = (new
                ArrayCollectionImpl<Integer>()).iterator();
        try {
            iterator.remove();
            fail("The test should have failed");
        } catch (IllegalStateException e) {
            assertTrue("IllegalStateException must be caught " +
                    "for empty collection", true);
        }
    }
}