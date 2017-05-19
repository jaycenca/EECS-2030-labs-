package eecs2030.lab1;

/**
 * Created by Jay C on 5/15/2017.
 */
public class SpiroUtil
{
    public static final double BIG_WHEEL_RADIUS = 1.0;

    /**
     * Throws an IllegalArgumentException if any of the following are true:
         wheelRadius < 0.0
         wheelRadius > SpiroUtil.BIG_WHEEL_RADIUS
         pencilRadius < 0.0
         pencilRadius > wheelRadius
         Parameters:
         wheelRadius - radius of the small Spirograph wheel
         pencilRadius - radius of the pencil location on the small Spirograph wheel
         degrees - the angle in degrees between the x-axis and the pencil location
         Returns:
         the x-coordinate of the pencil
         Throws:
         java.lang.IllegalArgumentException - if any of the conditions listed above are true
     * @param wheelRadius
     * @param pencilRadius
     * @param degrees
     * @return the x-coordinate of the pencil location for a Spirograph.
     */
    public static double hypoX(double wheelRadius, double pencilRadius, double degrees)
    {
        if (wheelRadius < 0.0) {
            throw new IllegalArgumentException("wheel radius is negative");
        }
        if (wheelRadius > SpiroUtil.BIG_WHEEL_RADIUS) {
            throw new IllegalArgumentException("wheel radius is greater than SpiroUtil.BIG_WHEEL_RADIUS");
        }
        if (pencilRadius < 0.0) {
            throw new IllegalArgumentException("pencil radius is negative");
        }
        if (pencilRadius > wheelRadius) {
            throw new IllegalArgumentException("pencil radius is greater than wheel radius");
        }

        double diff = BIG_WHEEL_RADIUS - wheelRadius;

        return (diff * Math.cos(Math.toRadians(degrees))) + (pencilRadius * (Math.cos( Math.toRadians(degrees * (diff / wheelRadius)))));
    }

    public static double hypoY(double wheelRadius, double pencilRadius, double degrees)
    {
        if (wheelRadius < 0.0) {
            throw new IllegalArgumentException("wheel radius is negative");
        }
        if (wheelRadius > SpiroUtil.BIG_WHEEL_RADIUS) {
            throw new IllegalArgumentException("wheel radius is greater than SpiroUtil.BIG_WHEEL_RADIUS");
        }
        if (pencilRadius < 0.0) {
            throw new IllegalArgumentException("pencil radius is negative");
        }
        if (pencilRadius > wheelRadius) {
            throw new IllegalArgumentException("pencil radius is greater than wheel radius");
        }
        return ((BIG_WHEEL_RADIUS - wheelRadius) * Math.sin(Math.toRadians(degrees))) - (pencilRadius * Math.sin(((BIG_WHEEL_RADIUS - wheelRadius)/ (wheelRadius) * Math.toRadians(degrees))));
    }


    public static Point2 hypo(double wheelRadius, double pencilRadius, double degrees){

        return new Point2(hypoX(wheelRadius, pencilRadius,degrees), hypoY(wheelRadius, pencilRadius, degrees));
    }
}
