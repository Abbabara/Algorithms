package s3;


/****************************************************************************
 *  Compilation:  javac PointSET.java
 *  Execution:    
 *  Dependencies:
 *  Author:
 *  Date:
 *
 *  Data structure for maintaining a set of 2-D points, 
 *    including rectangle and nearest-neighbor queries
 *
 *************************************************************************/

import java.util.Arrays;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class PointSET {
    // construct an empty set of points
	
	private SET<Point2D> set;
	
    public PointSET() {
    	set = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
    	set.add(p);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    // draw all of the points to standard draw
    public void draw() {
    	
    }
   
    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
    	
    	SET<Point2D> tempset = new SET<Point2D>();	
    	// for every point in the set
		for(Point2D point : set) {
			// if the rectangle contains the point, we add it to the tempset
			if(rect.contains(point)) {
				tempset.add(point);
			}
		}
    	return tempset;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
    	//if the set is empty, we return null.
        if(isEmpty()) {
        	return null;
        }
        else {
        	//set the distance to infinity.
        	double nearestDistance = Double.POSITIVE_INFINITY; 
        	Point2D temp = null;
        	//for every point in set
       		for(Point2D point : set) {
       			//if the distance from p is smaller than the distance already found, that is the new nearestdistance.
       			if(p.distanceSquaredTo(point) <= nearestDistance) {
       				temp = point;
       				nearestDistance = p.distanceSquaredTo(point);
       			}
    		}
        	return temp;   	
        }
    }

    public static void main(String[] args) {
        In in = new In();
        Out out = new Out();

        PointSET set = new PointSET();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }
    }
}
