package eecs2030.lab1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Lab1Test {

    private static Random rng = new Random();

    @Test
    public void test01_minInt() {
        assertEquals(Integer.MIN_VALUE, Lab1.minInt());
    }

    @Test
    public void test02_maxDouble() {
        assertEquals(0, Double.compare(Double.MAX_VALUE, Lab1.maxDouble()));
    }

    @Test
    public void test03_removeLastTwoDigits() {
        final int[] EXPECTED = {1, 5, 9, 27, 345, 7821};
        for (Integer exp : EXPECTED) {
            for (int i = 0; i < 100; i++) {
                int n = i + 100 * exp;
                String error = String.format("Lab1.removeLastTwoDigits(%d) failed", n);
                assertEquals(error, exp.intValue(), Lab1.removeLastTwoDigits(n));
            }
        }
        assertEquals("Lab1.removeLastTwoDigits(2147483647) failed",
                21474836, Lab1.removeLastTwoDigits(Integer.MAX_VALUE));
    }

    @Test
    public void test04_lastTwoDigits() {
        final int[] FIRST_DIGITS = {1, 5, 9, 27, 345, 7821};
        for (Integer d : FIRST_DIGITS) {
            for (int exp = 0; exp < 100; exp++) {
                int n = 100 * d + exp;
                String error = String.format("Lab1.lastTwoDigits(%d) failed", n);
                assertEquals(error, exp, Lab1.lastTwoDigits(n));
            }
        }
        assertEquals("Lab1.lastTwoDigits(2147483647) failed",
                47, Lab1.lastTwoDigits(Integer.MAX_VALUE));
    }

    @Test
    public void test05_yourAge() {
        rng.setSeed(5);
        for (int birthYear = 2016; birthYear > 1917; birthYear--) {
            int address = rng.nextInt(10000);
            int exp = 2017 - birthYear - 1;
            String error = String.format("Lab1.yourAge(%d, %d, %d) failed",
                    address, birthYear, 0);
            assertEquals(error, exp, Lab1.yourAge(address, birthYear, 0));

            error = String.format("Lab1.yourAge(%d, %d, %d) failed",
                    address, birthYear, 1);
            assertEquals(error, exp + 1, Lab1.yourAge(address, birthYear, 1));
        }
    }

    @Test
    public void test06_avg() {
        rng.setSeed(6);
        for (int i = 0; i < 100; i++) {
            int a = rng.nextInt();
            int b = rng.nextInt();
            int c = rng.nextInt();
            double sum = 0.0 + a + b + c;
            String error = String.format("Lab1.avg(%d, %d, %d) failed",
                    a, b, c);
            assertEquals(error,
                    sum, 3.0 * Lab1.avg(a, b, c), Math.ulp(sum));
        }
    }

    @Test
    public void test07_windChill() {
        final double[] AIR_TEMP = {0.0, -5.0, -9.0, -12.0, -20.0};
        final double[] WIND_VEL = {5.0, 20.0, 25.0, 30.0, 40.0};
        final double[] WIND_CHILL = {
                -1.589424, -11.551404, -17.475652, -22.130007, -34.134662};
        for (int i = 0; i < AIR_TEMP.length; i++) {
            double airT = AIR_TEMP[i];
            double windV = WIND_VEL[i];
            double exp = WIND_CHILL[i];
            String error = String.format("Lab1.windChill(%f, %f) failed",
                    airT, windV);
            assertEquals(error, exp, Lab1.windChill(airT, windV), 1e-6);
        }
    }

    @Test
    public void test08_isOdd() {
        rng.setSeed(8);
        for (int i = 0; i < 100; i++) {
            int even = 2 * rng.nextInt(Integer.MAX_VALUE / 2);
            int odd = even + 1;
            String error =
                    String.format("Lab1.isOdd(%d) failed (returned true)", even);
            assertFalse(error, Lab1.isOdd(even));

            error = String.format("Lab1.isOdd(%d) failed (returned false)", odd);
            assertTrue(error, Lab1.isOdd(odd));
        }
    }

    @Test
    public void test09_isInsideCircle() {
        rng.setSeed(9);
        // random points inside the unit circle
        for (int i = 0; i < 100; i++) {
            double radius = rng.nextDouble();  // less than 1
            double radians = 2.0 * Math.PI * i / 100;
            double x = radius * Math.cos(radians);
            double y = radius * Math.sin(radians);
            String error =
                    String.format("Lab1.isInside(%f, %f) failed (returned false)", x, y);
            assertTrue(error, Lab1.isInside(x, y));
        }
        // random points outside the unit circle
        for (int i = 0; i < 100; i++) {
            double radius = 1.0 + rng.nextDouble() + Math.ulp(1.0);  // greater than 1
            double radians = 2.0 * Math.PI * i / 100;
            double x = radius * Math.cos(radians);
            double y = radius * Math.sin(radians);
            String error =
                    String.format("Lab1.isInside(%f, %f) failed (returned true)", x, y);
            assertFalse(error, Lab1.isInside(x, y));
        }
        // some points exactly on the unit circle
        final double[] X = {1.0, 0.0, -1.0,  0.0};
        final double[] Y = {0.0, 1.0,  0.0, -1.0};
        for (int i = 0; i < X.length; i++) {
            double x = X[i];
            double y = Y[i];
            String error =
                    String.format("Lab1.isInside(%f, %f) failed (returned true)", x, y);
            assertFalse(error, Lab1.isInside(x, y));
        }
    }

    @Test
    public void test10_isOutsideBoundingBox() {
        int x0 = -100;
        int y0 = -100;
        int width = 0;
        int height = 0;
        this.testOutsideBoundingBox(x0, y0, width, height, "isOutside");
        this.testInsideBoundingBox(x0, y0, width, height, "isOutside");

        width = 5;
        height = 10;
        this.testOutsideBoundingBox(x0, y0, width, height, "isOutside");
        this.testInsideBoundingBox(x0, y0, width, height, "isOutside");

        x0 = Integer.MAX_VALUE - 10;
        y0 = 0;
        width = 20;
        height = 10;
        this.testOutsideBoundingBox(x0, y0, width, height, "isOutside");
        this.testInsideBoundingBox(x0, y0, width, height, "isOutside");

        x0 = 0;
        y0 = Integer.MAX_VALUE - 20;
        width = 5;
        height = 30;
        this.testOutsideBoundingBox(x0, y0, width, height, "isOutside");
        this.testInsideBoundingBox(x0, y0, width, height, "isOutside");

        x0 = Integer.MAX_VALUE - 10;
        y0 = Integer.MAX_VALUE - 20;
        width = 15;
        height = 30;
        this.testOutsideBoundingBox(x0, y0, width, height, "isOutside");
        this.testInsideBoundingBox(x0, y0, width, height, "isOutside");

        x0 = Integer.MAX_VALUE;
        y0 = Integer.MAX_VALUE;
        width = 15;
        height = 30;
        this.testOutsideBoundingBox(x0, y0, width, height, "isOutside");
        this.testInsideBoundingBox(x0, y0, width, height, "isOutside");
    }

    private void testOutsideBoundingBox(int x0, int y0, int width, int height,
                                        String whichMethod) {
        BoundingBox b = new BoundingBox(x0, y0, width, height);

        // above the bounding box
        int xMin = (int) Math.max(x0 - 1L, Integer.MIN_VALUE);
        int xMax = (int) Math.min(x0 + (long) width + 1, Integer.MAX_VALUE);

        int yMin = (int) Math.max(y0 - 1L, Integer.MIN_VALUE);
        int yMax = y0;

        this.testRegionOutsideBoundingBox(xMin, yMin, xMax, yMax, b, whichMethod);

        // below the bounding box
        // there may not be a region below the box!
        if ((long) y0 + height <= Integer.MAX_VALUE) {
            yMin = (int) Math.min(y0 + (long) height, Integer.MAX_VALUE);
            yMax = (int) Math.min(yMin + 1L, Integer.MAX_VALUE);

            this.testRegionOutsideBoundingBox(xMin, yMin, xMax, yMax, b, whichMethod);
        }

        // left of the bounding box
        xMin = (int) Math.max(x0 - 1L, Integer.MIN_VALUE);
        xMax = x0;
        this.testRegionOutsideBoundingBox(xMin, yMin, xMax, yMax, b, whichMethod);

        // right of the bounding box
        // there may not be a region to the right of the box!
        if ((long) x0 + width <= Integer.MAX_VALUE) {
            xMin = (int) Math.min(x0 + (long) width, Integer.MAX_VALUE);
            xMax = (int) Math.min(xMin + 1L, Integer.MAX_VALUE);;
            this.testRegionOutsideBoundingBox(xMin, yMin, xMax, yMax, b, whichMethod);
        }
    }

    private void testRegionOutsideBoundingBox(int xMin, int yMin, int xMax, int yMax,
                                              BoundingBox b, String whichMethod) {
        for (long x = xMin; x <= xMax; x++) {
            for (long y = yMin; y <= yMax; y++) {

                if (whichMethod.equals("isOutside")) {
                    String error =
                            String.format("Lab1.isOutside(%d, %d, b) failed (returned false)\n" +
                                    "for box %s", x, y, b);
                    assertTrue(error, Lab1.isOutside((int) x, (int) y, b));
                }
                else {
                    String error =
                            String.format("Lab1.isInside(%d, %d, b) failed (returned true)\n" +
                                    "for box %s", x, y, b);
                    assertFalse(error, Lab1.isInside((int) x, (int) y, b));
                }
            }
        }
    }

    private void testInsideBoundingBox(int x0, int y0, int width, int height,
                                       String whichMethod) {
        BoundingBox b = new BoundingBox(x0, y0, width, height);

        if (x0 == Integer.MAX_VALUE || y0 == Integer.MAX_VALUE) {
            // nothing is inside the box
            return;
        }

        int xMin = (int) Math.min(Integer.MAX_VALUE, (long) x0 + 1);
        int yMin = (int) Math.min(Integer.MAX_VALUE, (long) y0 + 1);
        int xMax = (int) Math.min(Integer.MAX_VALUE, (long) x0 + width - 1);
        int yMax = (int) Math.min(Integer.MAX_VALUE, (long) y0 + height - 1);

        for (long x = xMin; x <= xMax; x++) {
            for (long y = yMin; y <= yMax; y++) {

                if (whichMethod.equals("isInside")) {
                    String error =
                            String.format("Lab1.isInside(%d, %d, b) failed (returned false)\n" +
                                    "for box %s", x, y, b);
                    assertTrue(error, Lab1.isInside((int) x, (int) y, b));
                }
                else {
                    String error =
                            String.format("Lab1.isOutside(%d, %d, b) failed (returned true)\n" +
                                    "for box %s", x, y, b);
                    assertFalse(error, Lab1.isOutside((int) x, (int) y, b));
                }
            }
        }
    }

    @Test
    public void test11_isInsideBoundingBox() {
        int x0 = -100;
        int y0 = -100;
        int width = 0;
        int height = 0;
        this.testOutsideBoundingBox(x0, y0, width, height, "isInside");
        this.testInsideBoundingBox(x0, y0, width, height, "isInside");

        width = 5;
        height = 10;
        this.testOutsideBoundingBox(x0, y0, width, height, "isInside");
        this.testInsideBoundingBox(x0, y0, width, height, "isInside");

        x0 = Integer.MAX_VALUE - 10;
        y0 = 0;
        width = 20;
        height = 10;
        this.testOutsideBoundingBox(x0, y0, width, height, "isInside");
        this.testInsideBoundingBox(x0, y0, width, height, "isInside");

        x0 = 0;
        y0 = Integer.MAX_VALUE - 20;
        width = 5;
        height = 30;
        this.testOutsideBoundingBox(x0, y0, width, height, "isInside");
        this.testInsideBoundingBox(x0, y0, width, height, "isInside");

        x0 = Integer.MAX_VALUE - 10;
        y0 = Integer.MAX_VALUE - 20;
        width = 15;
        height = 30;
        this.testOutsideBoundingBox(x0, y0, width, height, "isInside");
        this.testInsideBoundingBox(x0, y0, width, height, "isInside");

        x0 = Integer.MAX_VALUE;
        y0 = Integer.MAX_VALUE;
        width = 15;
        height = 30;
        this.testOutsideBoundingBox(x0, y0, width, height, "isInside");
        this.testInsideBoundingBox(x0, y0, width, height, "isInside");
    }

    @Test
    public void test12_withArea() {
        // test negative area
        try {
            Lab1.withArea(-1);
            fail("Lab1.withArea(negative value) failed to throw an exception");
        }
        catch (IllegalArgumentException x) {
            // do nothing
        }
        catch (Exception x) {
            fail("Lab1.withArea(negative value) threw the wrong kind of exception");
        }

        // integer lengths needed to equal area
        for (int length = 0; length < 100; length++) {
            int area = length * length;
            BoundingBox b = Lab1.withArea(area);
            this.testWithArea(length, b);
        }

        // mostly non-integer lengths needed to equal area
        for (int i = 0; i <= 8; i++) {
            int area = (int) Math.pow(10.0, i);
            int length = (int) Math.sqrt(area);
            BoundingBox b = Lab1.withArea(area);
            this.testWithArea(length, b);
        }
    }

    private void testWithArea(int length, BoundingBox b) {
        int area = length * length;

        // test location
        String err = String.format("Lab1.withArea(%d) failed\n", area);
        String error = err + "box location is not (0, 0)\n";
        assertEquals(error, 0, b.getX());
        assertEquals(error, 0, b.getY());

        // test width and height
        error = err + "box width is not " + length + '\n';
        assertEquals(error, length, b.getWidth());

        error = err + "box height is not " + length + '\n';
        assertEquals(error, length, b.getHeight());
    }

    @Test
    public void test13_contains() {
        rng.setSeed(13);
        for (int i = 0; i < 100; i++) {
            int min = rng.nextInt(100) - 50;
            int max = min + i;
            this.testOutsideRange(min, max);
            this.testInsideRange(min, max);
        }
    }

    private void testOutsideRange(int min, int max) {
        final double[] X = {Double.NEGATIVE_INFINITY,
                min - 100.0,
                min - 1.0,
                Math.nextAfter(min, min - 1.0),
                min,
                max,
                Math.nextAfter(max, max + 1.0),
                max + 1.0,
                max + 100.0,
                Double.POSITIVE_INFINITY
        };
        Range r = new Range(min, max);
        for (double x : X) {
            String error = String.format("Lab1.contains(%f, r) failed\n" +
                    "where r = %s\n", x, r);
            assertEquals(error, 0, Lab1.contains(x, r));
        }
    }

    private void testInsideRange(int min, int max) {
        if (min == max) {
            return;
        }
        double xStart = Math.nextAfter(min, min + 1.0);
        double xEnd = Math.nextAfter(max, max - 1.0);
        double step = (xEnd - xStart) / 10;
        double[] X = new double[10];
        X[0] = xStart;
        X[9] = xEnd;
        for (int i = 1; i < 9; i++) {
            X[i] = X[i - 1] + step;
        }
        Range r = new Range(min, max);
        for (double x : X) {
            String error = String.format("Lab1.contains(%f, r) failed\n" +
                    "where r = %s\n", x, r);
            assertEquals(error, 1, Lab1.contains(x, r));
        }
    }

    @Test
    public void test14_compareTo() {
        rng.setSeed(14);
        for (int i = 0; i < 100; i++) {
            int min = rng.nextInt(100) - 50;
            Range skinny = new Range(min, min + 1);
            min = rng.nextInt(100) - 50;
            Range skinny2 = new Range(min, min + 1);
            min = rng.nextInt(100) - 50;
            Range wide = new Range(min, min + 2);
            String error =
                    String.format("Lab1.compareTo(r1, r2) failed for r1 = %s, r2 = %s",
                            skinny, skinny);
            assertEquals(error, 0, Lab1.compareTo(skinny, skinny));

            error =
                    String.format("Lab1.compareTo(r1, r2) failed for r1 = %s, r2 = %s",
                            skinny, skinny2);
            assertEquals(error, 0, Lab1.compareTo(skinny, skinny2));

            error =
                    String.format("Lab1.compareTo(r1, r2) failed for r1 = %s, r2 = %s",
                            skinny, wide);
            assertEquals(error, -1, Lab1.compareTo(skinny, wide));

            error =
                    String.format("Lab1.compareTo(r1, r2) failed for r1 = %s, r2 = %s",
                            wide, skinny);
            assertEquals(error, 1, Lab1.compareTo(wide, skinny));
        }
    }

    @Test
    public void test15_getCourseName() {
        assertEquals("Lab1.getCourseName() failed",
                "Advanced Object Oriented Programming", Lab1.getCourseName());
    }

    @Test
    public void test16_toString() {
        rng.setSeed(16);
        for (int i = 0; i < 10; i++) {
            double min = (rng.nextDouble() - 0.5) * 1000;
            double max = min + rng.nextDouble() * 1000;
            Range r = new Range(min, max);
            String error =
                    String.format("Lab1.toString(r) failed for r = %s", r.toString());
            String exp = r.toString();
            exp = exp.replace('[', '(');
            exp = exp.replace(']', ')');
            assertEquals(error, exp, Lab1.toString(r));
        }
    }

    @Test
    public void test17_hasValidLengthAndSeparator() {
        final String[] VALID = {"01:30", "ab:cd", "29:99"};
        for (int i = 0; i < VALID.length; i++) {
            String s = VALID[i];
            String error =
                    String.format("Lab1.hasValidLengthAndSeparator(s) returned false for s = %s", s);
            assertTrue(error, Lab1.hasValidLengthAndSeparator(s));
        }
        final String[] NOT_VALID = {"", "1:30", "01,23", "29:9"};
        for (int i = 0; i < NOT_VALID.length; i++) {
            String s = NOT_VALID[i];
            String error =
                    String.format("Lab1.hasValidLengthAndSeparator(s) returned true for s = %s", s);
            assertFalse(error, Lab1.hasValidLengthAndSeparator(s));
        }
    }


    @Test
    public void test18_sum() {
        String error = String.format("Lab1.sum(t) failed with t = empty list");
        assertEquals(error, 0, Lab1.sum(new ArrayList<Double>()), Math.ulp(0));

        rng.setSeed(18);
        ArrayList<Double> t = new ArrayList<>();
        double sum = 0.0;
        for (int i = 0; i < 100; i++) {
            double elem = rng.nextDouble();
            sum += elem;
            t.add(elem);
            ArrayList<Double> u = new ArrayList<>(t);
            error = String.format("Lab1.sum(t) failed with t = %s", t.toString());
            assertEquals(error, sum, Lab1.sum(u), Math.ulp(sum));
            error = String.format("Lab1.sum(t) failed because the list was modified");
            assertEquals(error, t, u);
        }
    }

    @Test
    public void test19B_sort2() {
        ArrayList<Integer> t = new ArrayList<>();
        String error = "Lab1.sort2(t) failed to throw an IllegalArgumentException";
        try {
            Lab1.sort2(t);
            fail(error);
        }
        catch (IllegalArgumentException x) {
            // do nothing
        }
        catch (Exception x) {
            fail("Lab1.sort2(t) threw the wrong kind of exception");
        }

        t.add(1);
        try {
            Lab1.sort2(t);
            fail(error);
        }
        catch (IllegalArgumentException x) {
            // do nothing
        }
        catch (Exception x) {
            fail("Lab1.sort2(t) threw the wrong kind of exception" + x);
        }

        t.add(2);
        t.add(3);
        try {
            Lab1.sort2(t);
            fail(error);
        }
        catch (IllegalArgumentException x) {
            // do nothing
        }
        catch (Exception x) {
            fail("Lab1.sort2(t) threw the wrong kind of exception");
        }
    }

    @Test
    public void test19A_sort2() {
        ArrayList<Integer> t = new ArrayList<>();
        t.add(-1000);
        t.add(1000);

        ArrayList<Integer> u = new ArrayList<>(t);
        String error =
                String.format("Lab1.sort2(t) failed for t = %s", u.toString());
        Lab1.sort2(u);
        assertEquals(error, t, u);

        u.clear();
        u.add(t.get(1));
        u.add(t.get(0));
        error =
                String.format("Lab1.sort2(t) failed for t = %s", u.toString());
        Lab1.sort2(u);
        assertEquals(error, t, u);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test20_toRadians() {
        rng.setSeed(20);
        ArrayList<Double> deg = new ArrayList<>();
        ArrayList<Double> degCopy = new ArrayList<>();
        ArrayList<Double> rad = new ArrayList<>();

        String error =
                String.format("Lab1.toRadians(t) failed for t = %s", deg.toString());
        Lab1.toRadians(degCopy);
        assertEquals(error, rad, degCopy);

        for (int i = 0; i < 100; i++) {
            double angDeg = rng.nextInt(2880) - 1440;
            double angRad = Math.toRadians(angDeg);
            deg.add(angDeg);
            degCopy = (ArrayList<Double>) deg.clone();
            rad.add(angRad);
            error =
                    String.format("Lab1.toRadians(t) failed for t = %s", deg.toString());
            Lab1.toRadians(degCopy);
            double delta = 0.000000000001;
            double actual = degCopy.get(i);
            assertEquals(angRad,actual, delta);

        }
    }

}
