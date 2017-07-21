package com.nixsolutions.collection;

import interfaces.task5.ArrayCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Anastasia Sokolan on 13.07.2017.
 */
public class ArrayCollectionTest {
    ArrayCollection<Integer> collection;
    Integer[] array = {1, 2, 1, -3, null, 7, 5, -11};
    @Before
    public void init() {
        collection = new ArrayCollectionImpl<Integer>();
    }
    @Test(expected = NullPointerException.class)
    public void testSetArrayNull() {
        collection.setArray(null);
    }
    @Test
    public void testClear() {
        collection.setArray(array);
        assertTrue("Collection must be filled",
                collection.getArray().length > 0);
        collection.clear();
        Object[] res = collection.getArray();
        assertTrue("Clear must make empty array", res != null);
        assertTrue("Collection hasn't been cleared", res.length == 0);
    }
    @Test
    public void testIsEmpty() {
        assertTrue("Size of empty collection must equal 0",
                collection.getArray().length == 0);
        collection.setArray(array);
        assertTrue("Wrong size of collection",
                collection.getArray().length == collection.size());
    }

    @Test
    public void testContainsNull() {
        collection.setArray(array);
        assertTrue("Null value is in collection", collection.contains(null));
    }
    @Test
    public void testContains() {
        collection.setArray(array);
        assertTrue("Collection contains value",
                collection.contains(array[0]));
        assertFalse("Collection contains value",
                collection.contains((array[0]) * 29));
    }

    @Test(expected = NullPointerException.class)
    public void testToArray() {
        Integer[] res = collection.toArray(null);
    }

    @Test
    public void testAddNull() {
        boolean res = collection.add(null);
        assertTrue("Null value is not added", res);
        assertTrue("Mustn't return null array",
                collection.getArray() != null);
        assertTrue("Size must equal 1",
                collection.getArray().length == 1);
        assertTrue("First element must be null",
                collection.getArray() [0] == null);
    }
    @Test
    public void testAdd() {
        for (Integer el : array) {
            assertTrue("Value wasn't added", collection.add(el));
        }
        Object[] res = collection.getArray();
        assertTrue("Array can't equal null", res != null);
        assertTrue("Wrong size of modified collection",
                res.length == array.length);
        for (int i = 0; i < array.length; i++) {
            assertTrue("Elements are different", array[i] == res[i]);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        collection.remove(null);
    }

    @Test
    public void testRemove() {
        collection.setArray(array);
        assertTrue("Remove method hasn't remove element",
                collection.remove(array[0]));
        assertFalse("Remove must not happened",
                collection.remove(1000));
        Object[] res = collection.getArray();
        assertTrue("Array can't be null after add", res != null);
        assertTrue("Size must change", (array.length - res.length) == 1);
        assertTrue("First el must equal second ", res[0] == array[1]);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllNull() {
        collection.setArray(array);
        collection.containsAll(null);
    }

    @Test
    public void testContainsAll() {
        collection.setArray(array);
        assertFalse("Collection doesn't have any elements of other one",
                collection.containsAll(Arrays.asList(new Integer[] {-1111})));
        assertTrue("Collection have all the elements of other one",
                collection.containsAll(Arrays.asList(array)));
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllNull() {
        collection.addAll(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAllThisCollection() {
        collection.addAll(collection);
    }

    @Test
    public void testAddAll() {
        collection.addAll(Arrays.asList(array));
        Object[] res = collection.getArray();
        assertTrue("Array can't equal null", res != null);
        assertTrue("Wrong size after addAll", res.length == array.length);
        for (int i = 0; i < array.length; i++) {
            assertTrue("Uncorrect addition of element", res[i] == array[i]);
        }
        collection.addAll(Arrays.asList(new Integer[] {1, 2}));
        assertTrue("Collection hasn't been added",
                collection.getArray().length == (array.length + 2));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllNull() {
        collection.remove(null);
    }

    @Test
    public void testRemoveAll() {
        collection.setArray(array);
        assertFalse("Collection must not be modified", collection.removeAll(
                Arrays.asList(new Integer[] {-1111, 777})));
        int index = array.length - 1;
        assertTrue("Collection must be modified",
                collection.removeAll(Arrays.asList(
                        new Integer[] {array[index], array[--index]})));
        Object[] res = collection.getArray();
        assertTrue("Array can't equal null", res != null);
        assertTrue("Wrong size after addAll",
                (array.length - res.length) == 2);
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllNull() {
        collection.retainAll(null);
    }

    @Test
    public void testRetainAll() {
        collection.setArray(new Integer[] {array[0], array[1], -111});
        assertFalse("Collection was not modified", collection.retainAll(
                Arrays.asList(new Integer[] {222, 333})));
        assertTrue("Collection was modified",
                collection.retainAll(Arrays.asList(array)));
        Object[] res = collection.getArray();
        assertTrue("Array can't equal null", res != null);
        assertTrue("Wrong size after addAll",
                res.length == 2);
        for (int i = 0; i < res.length; i++) {
            assertTrue("Wrong values of elements", res[i] == array[i]);
        }
    }
}