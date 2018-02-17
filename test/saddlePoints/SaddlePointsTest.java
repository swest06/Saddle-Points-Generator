package saddlepoints;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import saddlePoints.SaddlePoints;

/**
 * @author Sean West ID:13132362
 *
 */
public class SaddlePointsTest {
    SaddlePoints sp = new SaddlePoints(); // create an instance variable
    int[][] randArray = new int[3][4];
    int[] array;
    int[][] with;
    int[][] without;

    @Before
    public void setUp() throws Exception {
        // If you use the same variables in multiple tests,
        //  assign values to them here


        array = {6, 8, 5};

        with = {{-9, 12, -6},
                { 7, 14, 5},
                {10, -8, 3},
                { 6, 17,-10}};

        without = {{ 1, -2, 3},
                {-6, 5, -4},
                { 7, -8, 9}};
    }

    /*@Test
    public void testSomeMethod() {
        // Put tests here
        // Put sp. in front of every call to a method in SaddlePoints
    }*/

    @test
    public void testHassaddlePoint(){
        assertTrue(sp.hasSaddlePoint(with));
        assertFalse(sp.hasSaddlePoint(without));
    }

    @test
    public void testLargest(){
        assertEquals(8, sp.largest(array));
    }

    @test
    public void testSmallest(){
        assertEquals(5, sp.smallest(array));
    }
    @test
    public void testSmallestValues(){
        int[] aryS = {-9, 5, -8, -10}
        assertArrayEquals(aryS, sp.smallestValues(with));
    }

    @test
    public void testLargestValues(){
        int[] aryL = {10, 17, 5}
        assertArrayEquals(aryL, sp.largestValues(with));
    }

    @test
    public void testCreateRandomArray(){
        assertArrayEquals(randArray, sp.createRandomArray(3, 4, 0, 0));
    }
}