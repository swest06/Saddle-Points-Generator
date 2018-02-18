package saddlePoints;

import java.util.Arrays;
import java.util.Random;


/**
 * Creates a number of random arrays, and checks each array to see
 * if it contains a saddle point. Prints the arrays and the results.
 *
 * @author PUT YOUR NAME HERE
 */
public class SaddlePoints {
    Random rand = new Random();
    int minValue = -30;
    int maxValue = 31;

    /**
     * Creates arrays various sizes (including some 2x2 arrays and some larger),
     * fills them with random values, and prints each array and information about
     * it. Keeps generating arrays until it has printed at least one with and
     * one without a saddle point.
     */
    void run() {
        boolean finished = false;
        boolean saddlePoint = false;
        boolean noSaddlePoint = false;

        while (!finished){
            int numberOfRows = rand.nextInt(9 - 2) + 2;
            int numberOfColumns = rand.nextInt(9 - 2) + 2;

            int[][] array = createRandomArray(numberOfColumns, numberOfRows, minValue, maxValue);
            printArray(array);
            printArrayInfo(array);
            if (hasSaddlePoint(array)){
                saddlePoint = true;
            }else{
                noSaddlePoint = true;
            }
            if(saddlePoint && noSaddlePoint){
                finished = true;
            }

        }
        System.out.println("Goodbye.");
    }

    /**
     * Prints the array.
     *
     * @param array The array to be printed.
     */
    void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));

        }

    }

    /**
     * Prints whether the given array has a saddle point, and if so, where it is (row and column)
     * and what its value is. (If there are multiple saddle points, just prints the first.)
     *
     * @param array The array to be checked.
     */
    void printArrayInfo(int[][] array) {
        int row = saddlePointRow(array);
        int col = saddlePointColumn(array);

        if (hasSaddlePoint(array)){
            System.out.println("This array has a saddle point.");
            System.out.println("The location of this saddle point is (" + row + "," + col + ").");
            System.out.println("The value at this saddle point is " + array[row][col] + ".");
        }else{
            System.out.println("This array does not have a saddle point.");
        }

    }

    /**
     * Creates and returns an array of the given size and fills it with random
     * values in the specified range.
     *
     * @param numberOfRows    The number of rows desired.
     * @param numberOfColumns The number of columns desired.
     * @param minValue        The smallest number allowable in the array.
     * @param maxValue        The largest number allowable in the array.
     * @return
     */
    int[][] createRandomArray(int numberOfRows, int numberOfColumns, int minValue, int maxValue) {
        int r =  numberOfRows;
        int c =  numberOfColumns;
        int [][] array = new int[r][c];
        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            for (int e = 0; e < array[i].length; e++) {
                //e = random between range of minValue and maxValue
                if (minValue != maxValue){
                    array[i][e] = rand.nextInt(maxValue - minValue) + minValue;
                }
                else{
                    array[i][e] = minValue;
                }
            }

        }

        return array;
    }
    /**
     * Finds the largest value in an array of integers.
     *
     * @param array The array to be searched.
     * @return The largest value in the array.
     */
    int largest(int[] array) {
        int max = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }

        }

        return max;
    }

    /**
     * Finds the smallest value in an array of integers.
     *
     * @param array The array to be searched.
     * @return The smallest value in the array.
     */
    int smallest(int[] array) {
        int min = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min){
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Returns an array containing the largest values in each column of the given array.
     *
     * @param array The array to be searched.
     * @return An array of the largest values in each column.
     */
    int[] largestValues(int[][] array) {
        //Create new simple array to hold largest values in column
        int[] array2 = new int[array[0].length];

        //Iteration. j = row, i = column
        for (int i = 0; i < array[0].length; i++) {
            int large = array[0][i];
            for (int j = 0; j < array.length; j++) {
                if (array[j][i] >= large){
                    large = array[j][i];
                }

            }
            array2[i] = large;

        }
        return array2;
    }

    /**
     * Returns an array containing the smallest values in each row of the given array.
     *
     * @param array The array to be searched.
     * @return An array of the smallest values in each row.
     */
    int[] smallestValues(int[][] array) {
        //Declare and initialise new array
        int[] array2 = new int[array.length];

        int x = 0;
        for (int i = 0; i < array.length; i++){
            x = smallest(array[i]);
            array2[i] = x;
        }
        return array2;
    }

    /**
     * Returns true if the given array has a saddle point, and false if it does not.
     *
     * @param array The array to be checked.
     * @return True if the array has a saddle point, else false.
     */
    boolean hasSaddlePoint(int[][] array) {
        boolean result;
        int[] largeCol = largestValues(array);
        int[] smallRow = smallestValues(array);
        int x = smallest(largeCol);
        int y = largest(smallRow);

        if (x == y){
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    /**
     * Given an array that is known to have a saddle point, returns the number of a
     * row containing a saddle point. If more than one row contains a saddle point,
     * the first such row will be returned.
     *
     * @param array An array containing one or more saddle points.
     * @return The lowest-numbered row containing a saddle point.
     */
    int saddlePointRow(int[][] array) {
        int row = 0;
        int x = 0;

        //finds smallest row value and saves location
        for (int i = 0; i < array.length; i++) {
            x = smallest(array[i]);
            int ra = i;
            int rb = 0;

            //finds largest column value to verify location
            for (int j = 0; j < array[0].length ; j++) {
                int large = array[0][j];
                for (int k = 0; k < array.length; k++) {
                    if (array[k][j] >= large){
                        large = array[k][j];
                        rb = k;
                    }
                }
                //Checks if the smallest row value matches the largest column value
                if (large == x && rb == ra){
                    row = ra;
                }
            }

        }
        return row;
    }

    /**
     * Given an array that is known to have a saddle point, returns the number of a
     * column containing a saddle point. If more than one column contains a saddle point,
     * the first such column will be returned.
     *
     * @param array An array containing one or more saddle points.
     * @return The lowest-numbered column containing a saddle point.
     */
    int saddlePointColumn(int[][] array) {
        int x = 0;
        int col = 0;

        //finds smallest row value
        for (int i = 0; i < array.length; i++) {
            x = smallest(array[i]);
            int rx = i;
            int ry = 0;

            //finds largest column value to verify and save location
            for (int j = 0; j < array[0].length ; j++) {
                int large = array[0][j];
                for (int k = 0; k < array.length; k++) {
                    if (array[k][j] >= large){
                        large = array[k][j];
                        ry = k;
                    }
                }
                //Checks largest column value matches the smallest row value
                if (large == x && ry == rx){
                    col = j;
                }
            }
        }
        return col;
    }
}