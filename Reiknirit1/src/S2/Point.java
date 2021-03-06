package S2;

import java.util.Comparator;
import edu.princeton.cs.algs4.*;

/*************************************************************************
 * Compilation: javac Point.java Execution: Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 * @author Magnus M. Halldorsson, email: mmh@ru.is
 *************************************************************************/


public class Point implements Comparable<Point> {

    public final int x, y;

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    // construct the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private class SlopeOrder implements Comparator<Point> {

        public int compare(Point thisP, Point thatP) {

            double thisSlope = slopeTo(thisP);
            double thatSlope = slopeTo(thatP);

            if(thatSlope > thisSlope) {
                return -1;
            }
            else if(thatSlope < thisSlope) {
                return 1;
            }
            return 0;
        }
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        // TODO: Implement this

        double x = that.x - this.x;
        double y = that.y - this.y;

        if (y == 0) {
            return 0;
        }
        else if (x == 0) {
            return Double.POSITIVE_INFINITY;
        }
        else if (x == 0 && y == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        return (y / x);
    }

    /**
     * Is this point lexicographically smaller than that one? comparing
     * y-coordinates and breaking ties by x-coordinates
     */
    public int compareTo(Point that) {
        // TODO: Implement this

        if (that.y > this.y) {
            return -1;
        }
        else if (that.y < this.y) {
            return 1;
        }
        else if (that.x > this.x) {
            return -1;
        }
        else if (that.x < this.x) {
            return 1;
        }
        else {
            return 0;
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /*
         * Do not modify
         */
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
       
        Point[] points = new Point[n];
        
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        out.printf("Testing slopeTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].slopeTo(points[i - 1]));
        }
        out.printf("Testing compareTo method...\n");
        
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].compareTo(points[i - 1]));
        }
        
        out.printf("Testing SLOPE_ORDER comparator...\n");
        for (int i = 2; i < points.length; i++) {
            out.println(points[i].SLOPE_ORDER.compare(points[i - 1],
                    points[i - 2]));
        }
    }
}
