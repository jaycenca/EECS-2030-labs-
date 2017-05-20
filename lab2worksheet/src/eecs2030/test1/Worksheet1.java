package eecs2030.test1;

public class Worksheet1 {

    public static void main(String[] args) {
        boolean q1 = isOutside(1, 2, 4);
        System.out.println("isOutside(1,2, 4) is " + q1);
    }

    //Question 1 of the worksheet
    //part a

    /**
     * Returns true if value is strictly less than min or strictly greater than max, and false otherwise
     *
     * @param min   - a minimum value
     * @param max   - a maximum value
     * @param value - a value to check
     * @return true if value is strictly less than min or strictly greater than max, and false otherwise
     * @pre. max is greater than or equal to min
     */
    public static boolean isOutside(int min, int max, int value) {
        return value > max && value < min;
    }

    //part b

    /**
     * Given a list containing exactly 2 integers, negates the values
     *
     */
}