package eecs2030.lab1;

import java.util.List;

/**
 * Short methods reviewing fundamental Java concepts covered in EECS1021,
 * EECS1022, and EECS1720
 * @author EECS2030 Winter 2016-17
 *
 */
public class Lab1 {
    
    private Lab1() {
        // empty by design
    }
    
    
    /**
     * Returns the minimum (most negative) value that an <code>int</code> can
     * represent.
     * 
     * @return the minimum (most negative) value that an int can represent
     */
    public static int minInt() {
        return Integer.MIN_VALUE;
    }

    /**
     * Returns the maximum (most positive) value that a <code>double</code> can
     * represent.
     * 
     * @return the maximum (most positive) value that a double can represent
     */
    public static double maxDouble()
    {
        return Double.MAX_VALUE;
    }

    /**
     * Removes the last two digits of a positive integer base 10 number that is
     * greater than <code>99</code>.
     * 
     * @param n
     *            a positive integer number greater than 99
     * @return the integer produced by removing the last two digits of n
     */
    public static int removeLastTwoDigits(int n)
    {
        return n/100;
    }

    /**
     * Returns the last two digits of a positive integer base 10 number that is
     * greater than <code>99</code>. If the last two digits start with a
     * <code>0</code> then only the last digit is returned.
     * 
     * @param n
     *            a positive integer number greater than 99
     * @return the last two digits of n
     */
    public static int lastTwoDigits(int n)
    {
        return n%100;
    }

    /**
     * Computes the age (in years) of a person using the following convoluted
     * algorithm:
     * 
     * <p>
     * <ul>
     * <li>start with the person's street <code>address</code>
     * <li>double it
     * <li>add 42 to the result from the previous step
     * <li>multiply the previous step by 50
     * <li>subtract the person's <code>birthYear</code> from the previous step
     * <li>subtract 50 from the previous step
     * <li>add the number of <code>birthdays</code> the person has had this year
     * to the previous step
     * <li>subtract 34 from the previous step
     * <li>the last two digits of the previous step is the age of the person
     * </ul>
     * 
     * <p>
     * This algorithm works only for people aged 1-99, and for addresses less
     * than approximately 20000000.
     * 
     * @param address
     *            the person's street address number
     * @param birthYear
     *            the person's birth year
     * @param birthdays
     *            the number of birthdays the person has had this year (either 0
     *            or 1)
     * @return the age of the person
     */
    public static int yourAge(int address, int birthYear, int birthdays) {
        return ((((((( address * 2 ) + 42 ) * 50 ) - birthYear ) - 50 ) + birthdays ) - 34 ) % 100 ;
    }

    /**
     * Compute the average of three values.
     * 
     * @param a
     *            a value
     * @param b
     *            a second value
     * @param c
     *            a third value
     * @return the average of the three values
     */
    public static double avg(int a, int b, int c)
    {
        return ((double)(a) + (double)(b) + (double)(c)) / 3;
    }

    /**
     * Returns the wind chill for air temperatures equal to or below 0 degrees
     * Celcius and wind velocities equal to or greater than 5 km/h.
     * 
     * <p>
     * Wind chill is an index that indicates how cold the weather feels to the
     * average person when there is some wind. For example, if the air
     * temperature is -5 degrees Celcius and the wind chill is -15 then it means
     * that it feels similar to a windless day where the temperature is -15
     * degrees Celcius.
     * 
     * @param airTemp
     *            the temperature in degrees Celcius (must be 0 degrees or less)
     * @param windSpeed
     *            the wind speed in km/h (must be 5 km/h or greater)
     * @return the wind chill index
     * @see <a href="http://climate.weather.gc.ca/glossary_e.html#w">
     *      Environment and Climate Change Canada wind chill definition</a>
     */
    public static double windChill(double airTemp, double windSpeed)
    {
        return (13.12)+ (0.6215 * airTemp) - (11.37 * Math.pow(windSpeed, 0.16)) + (0.3965 * airTemp * Math.pow(windSpeed, 0.16));
    }

    /**
     * Determine if an integer <code>x</code> is odd.
     * 
     * @param x
     *            a value
     * @return true if x is odd and false otherwise
     */
    public static boolean isOdd(int x)
    {
        return x % 2 == 1;
    }

    /**
     * Determine if the point <code>(x, y)</code> is strictly inside the circle
     * with center <code>(0, 0)</code> and having radius equal to
     * <code>1</code>. A point on the perimeter of the circle is considered
     * outside of the circle.
     * 
     * @param x
     *            the x-coordinate of the point
     * @param y
     *            the y-coordinate of the point
     * @return true if (x, y) is inside the unit circle, and false otherwise
     */
    public static boolean isInside(double x, double y)
    {
        return Math.sqrt(( Math.pow ( x , 2 ) ) + ( Math.pow( y, 2))) <  1.0;
    }

    /**
     * Determine if the point <code>(x, y)</code> is outside the given
     * <code>BoundingBox</code>. A point on the perimeter of the bounding box is
     * considered outside of the bounding box.
     * 
     * @param x
     *            the x-coordinate of the point
     * @param y
     *            the y-coordinate of the point
     * @param box
     *            a <code>BoundingBox</code>
     * @return true if (x, y) is outside the box, and false otherwise
     */
    public static boolean isOutside(int x, int y, BoundingBox box)
    {
        boolean first = x > box.getX();
        boolean second = x < ((long)(box.getX()) + box.getWidth());
        boolean third = y >  box.getY();
        boolean last = y < ((long)box.getY() + box.getHeight());
        /*System.out.println("x <= box.getX() is " + first);
        System.out.println("(x >= box.getX() + box.getWidth()) is " + second);
        System.out.println("(y <=  box.getY()) is " + third );
        System.out.println("y >= box.getY() + box.getHeight() is " + last);

        if(x == 2147483638 && y == 1) {
            System.out.println(first);
            System.out.println(box.getX() + box.getWidth());
            System.out.println(second);
            System.out.println(third);
            System.out.println(last);
        }*/
        return !(first && second && third && last);
    }

    public static void main(String[] args) {
        System.out.println(isOutside(2147483638, 1, new BoundingBox(2147483637, 0, 20,  10 )));
    }
    /**
     * Determine if the point <code>(x, y)</code> is inside the given
     * <code>BoundingBox</code>. A point on the perimeter of the bounding box is
     * considered outside of the bounding box.
     * 
     * @param x
     *            the x-coordinate of the point
     * @param y
     *            the y-coordinate of the point
     * @param box
     *            a BoundingBox
     * @return true if (x, y) is inside the box and false otherwise
     */
    public static boolean isInside(int x, int y, BoundingBox box)
    {
       return !isOutside(x,y,box);
    }

    /**
     * Returns a square <code>BoundingBox</code> with location
     * <code>(0, 0)</code> having the given <code>area</code>
     * 
     * <p>
     * Because the <code>BoundingBox</code> dimensions are integer values, it
     * may not be possible to produce a box with the exact area. In such cases,
     * the largest possible square box that does not exceed the desired area is
     * returned .
     * 
     * @param area
     *            the area of the BoundingBox
     * @return the largest possible square BoundingBox with location (0, 0)
     *         whose area does not exceed the given area
     * @throws IllegalArgumentException
     *             if area is less than zero
     */
    public static BoundingBox withArea(int area)
    {
        if( area < 0)
        {
            throw new IllegalArgumentException("Area is less than zero");
        }
        return new BoundingBox(0,0, (int)(Math.sqrt(area)), (int)(Math.sqrt(area)));
    }

    /**
     * Determine if a value <code>x</code> is strictly inside the given
     * <code>Range</code>. A value exactly at the minimum or maximum of the
     * range is considered outside of the range.
     * 
     * @param x
     *            a value
     * @param range
     *            a Range to check
     * @return the value 1 if x is strictly inside the given Range, and 0
     *         otherwise
     */
    public static int contains(double x, Range range)
    {
        if( x > range.getMinimum() && x < range.getMaximum())
            return 1;
        else
            return 0;
    }

    /**
     * Compares two <code>Range</code> instances by their widths.
     * 
     * @param r1
     *            a Range
     * @param r2
     *            a second Range
     * @return the value 0 if both Range instances are equal; -1 if r1 is
     *         narrower than r2; and 1 if r1 is wider than r2
     */
    public static int compareTo(Range r1, Range r2)
    {
        if(Math.abs(r1.getMaximum() - r1.getMinimum()) == Math.abs(r2.getMaximum() - r2.getMinimum()))
            return 0;
        else if(Math.abs(r1.getMaximum() - r1.getMinimum()) < Math.abs(r2.getMaximum() - r2.getMinimum()) )
            return -1;
        else
            return 1;
    }

    /**
     * Returns the course name as the string
     * <code>"Advanced Object Oriented Programming"</code>
     * 
     * @return the string "Advanced Object Oriented Programming"
     */
    public static String getCourseName()
    {
        return "Advanced Object Oriented Programming";
    }

    /**
     * Returns a string representation of a <code>Range</code> that is different
     * than the one returned by <code>Range.toString</code>.
     * 
     * <p>
     * The string contains the minimum and maximum values separated by a comma
     * and space all inside of a pair of parentheses. For example, the string
     * 
     * <p>
     * <code>(-3.14, 3.14)</code>
     * 
     * <p>
     * represents the range whose minimum value is <code>-3.14</code> and whose
     * maximum value is <code>3.14</code>
     * 
     * @param r
     *            a Range instance
     * @return a string representation of the range
     */
    public static String toString(Range r)
    {
        return "("+r.getMinimum()+", "+r.getMaximum()+")";
    }

    /**
     * Partially validates a string that is supposed to represent a time on a
     * 24-hour clock. Returns <code>true</code> if:
     * 
     * <ul>
     * <li>the string has length equal to <code>5</code>, AND
     * <li>the third character is a colon <code>:</code>
     * </ul>
     * 
     * <p>
     * and <code>false</code> otherwise.
     * 
     * @param s
     *            a string to validate
     * @return true if the string length is 5 and the
     *         third character is a colon, and false otherwise
     */
    public static boolean hasValidLengthAndSeparator(String s)
    {
        return s.length() == 5 && s.charAt(2) == ':';
    }

    /**
     * Sorts a list of two integers so that the elements are in ascending order.
     * The size of the list remains unchanged.
     * 
     * @param t
     *            a list
     * @throws IllegalArgumentException
     *             if the size of list is not equal to 2
     */
    public static void sort2(List<Integer> t)
    {
        if(t.size() != 2)
            throw new IllegalArgumentException("The size of list is not equal to 2");

        // get the first two elements
        int t0 = t.get(0);
        int t1 = t.get(1);

        if(t0 > t1)
        {
            int temp = 0;
            temp = t0;
            t.set(0, t1);
            t.set(1, temp);
        }
    }

    /**
     * Returns the sum of the elements in a list. The sum of an empty list is
     * <code>0</code>. The method does not modify the list.
     * 
     * @param t
     *            a list
     * @return the sum of the elements in a list
     */
    public static double sum(List<Double> t)
    {
        double sum = 0;
        for(Double d : t)
        {
            sum += d;
        }
        return sum;
    }

    /**
     * Replaces the elements of a list of angles in degrees with the equivalent
     * angles in radians. The size of the list remains unchanged.
     * 
     * @param t
     *            a list of angles in degrees
     */
    public static void toRadians(List<Double> t)
    {
        for(int i = 0; i < t.size(); i++)
        {
            //System.out.println(Math.toRadians(t.get(i)));
            double radian =  t.get(i) * 2 * Math.PI / 360;
            t.set(i, radian);

        }


    }
  
}
