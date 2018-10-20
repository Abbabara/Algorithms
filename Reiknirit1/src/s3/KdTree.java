package s3;


import java.util.ArrayList;

/*************************************************************************
 *************************************************************************/

import java.util.Arrays;
import java.util.Collections;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class KdTree {
    private Node root;  
    private int size;

    private class Node {
        private Point2D p;
        private RectHV  rect;
        private Node left, right;


        public Node(Point2D p, RectHV rect) {
            this.p = p;
        	RectHV r = rect;
        	
            if (r == null) {
                r = new RectHV(0, 0, 1, 1);
            }
            
            this.rect = r;
        }
    }
    	
	
	// construct an empty set of points
    public KdTree() {
    	root = null;
    	size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        if(size == 0) {
        	return true;
        }
        return false;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
    	if(contains(p) == true) {
    		return;
    	}
    	// if there is no node yet in the tree we put the point in the root
    	else if(size == 0) {
    		// we go to the function insertvertical, because we always make first a vertical line.
    		root = insert(p, null, root, 'v');
    	}
    	else {
    		// we go to the function insertvertical, because we always make first a vertical line. 
    		//in this case we go to the root, because it exists already
    		root = insert(p, root.rect, root, 'v');
    	}
    	size++;
    };
    
    private Node insert(Point2D p, RectHV rect, Node node, char i ) {
  
    	RectHV tempRect;
    	
    	//if we have found and empty node, we return it to put in the point.
    	if(node == null) {
    		return new Node(p, rect);
    	}
    	
    	//if the line is vertical, or 'v' we perform this if statement
    	else if(i == 'v') {
    		// compare the x coordinates to the dot, and the dot in this node.
        	int temp = Point2D.X_ORDER.compare(node.p, p);
        	
        	//if the point is left to the point in the node.
        	if(temp > 0) {
        		//if the node is null, we change the tempRect.
        		if(node.left == null) {
        			tempRect = new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax());
        		}
        		//if the node is not null, we change the temRect to the rect in the left node
        		else {
        			tempRect = node.left.rect;
        		}
        		//using recursion and put the left node in. NOTE that the char has been changed to 'h', so next time 
        		//we go threw the other statement.
        		node.left = insert(p, tempRect, node.left, 'h');
        	}
        	//if the point is right or equal to the point in the node.
        	else if(temp <= 0) {
        		//if the node is null, we change the tempRect.
        		if(node.right == null) {
        			tempRect = new RectHV(node.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
        		}
        		//if the node is not null, we change the temRect to the rect in the right node
        		else {
        			tempRect = node.right.rect;
        		}
        		//using recursion and put the left node in. NOTE that the char has been changed to 'h', so next time 
        		//we go threw the other statement.
        		node.right = insert(p, tempRect, node.right, 'h');
        	}
    	}
    	//if the line is horizontal or 'h' we perform this if statement
    	else if(i == 'h') {
    		// compare the y coordinates to the dot, and the dot in this node.
        	int temp = Point2D.Y_ORDER.compare(node.p, p);      	
        	
        	//if the point is below to the point in the node.
    		if(temp > 0) {
    			//if the node is null, we change the tempRect.
        		if(node.left == null) {
        			tempRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y());
        		}
        		//if the node is not null, we change the temRect to the rect in the left node
        		else {
        			tempRect = node.left.rect;
        		}
        		//using recursion and put the left node in. NOTE that the char has been changed to 'v', so next time 
        		//we go threw the other statement.
        		node.left = insert(p, tempRect, node.left, 'v');
    		}
    		//if the point is right or equal to the point in the node.
    		else if(temp <= 0) {
    			//if the node is null, we change the tempRect.
    			if(node.right == null) {
    				tempRect = new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax());	
    			}
    			//if the node is not null, we change the temRect to the rect in the right node
    			else {
    				tempRect = node.right.rect;
    			}
        		//using recursion and put the right node in. NOTE that the char has been changed to 'v', so next time 
        		//we go threw the other statement.
    			node.right = insert(p, tempRect, node.right, 'v');
    		}	
    	}
    	return node;
    }
    
    // does the set contain the point p?
    public boolean contains(Point2D p) {
    	//go to the help contain function
    	return contains(root, p, 'v');
    }
    
    //help function for contains
    private boolean contains(Node node, Point2D p, char c) {
    	//if the node is null, the tree does not contain the point p
    	if (node == null) {
        	return false;
        }
    	
    	//if the point is the same as the point in the node, we have found that the tree contains the point.
        else if (node.p.equals(p)) {
        	return true;
        }
    	int temp;
    	// if the line is vertical
        if (c == 'v')   {
        	//comparing the point to the point in the node, to find the position of it.
        	temp = Point2D.X_ORDER.compare(node.p, p);	
        	
        	//if the point is left to the node point
        	if (temp > 0) {
        		//note the 'h'!
            	return contains(node.left, p, 'h');
            }
        	//if the point is right to the node point
            else {
            	//note the 'h'!
            	return contains(node.right, p, 'h');
            }
        }
        //if the line is horizontal
        else {
        	//comparing the point to the point in the node, to find the position of it.
        	temp = Point2D.Y_ORDER.compare(node.p, p);
        	//if the point is below to the node point
        	if (temp > 0) {
        		//note the 'v'!
            	return contains(node.left, p, 'v');
            }
        	//if the point is above to the node point
            else {
            	//note the 'v'!
            	return contains(node.right, p, 'v');
            }
        }
    }

    // draw all of the points to standard draw
    public void draw() {
 
    }
    

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
    	//make a new set to store the points that are inside the rectangle
        SET<Point2D> list = new SET<>();
        
        // To do this we use recursion. if the tree is not empty we go into the recursion function with the root. 
        //	After that the recursion function goes threw every node in the tree and checks if the rectangle contains any points
        if (!isEmpty()) {
            range(list, rect, root);
        }
        //returning list of the points.
        return list;
    }
    
    private void range(SET<Point2D> list, RectHV rect, Node node){
    	if(node == null) {
    		return;
    	}
    	//if the  rectangle contains the point, it adds to the list
		if(rect.contains(node.p)) {
			
    		list.add(node.p);
    	}
		if(node.left != null && rect.intersects(node.left.rect)) {
	    	//go threw the function with the left node
			range(list, rect, node.left);
		}
		if(node.right != null && rect.intersects(node.right.rect)) {
			//go threw the function with the right node
			range(list, rect, node.right);
		}
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
    	//if the tree is empty, return null!
        if(size == 0) {
        	return null;
        }
        //setting the nearest point to null
        Point2D nearestPoint = null;
        
        //now call the recursion function to find the nearest point
        nearestPoint = nearest(nearestPoint, root, p);
        
        return nearestPoint;
    }
    
    private Point2D nearest(Point2D nearestPoint, Node node, Point2D p) {
    	//if the node is empty, then we return the existing nearest point.
    	if (node == null) {
    		return nearestPoint;
    	}
    	// if we have found a point, but the nearest point is still empty, this is the current nearest point.
    	else {
    		if(nearestPoint == null) {
    			nearestPoint = node.p;
    		}
    		
    		double tempFromNearest = nearestPoint.distanceSquaredTo(p);
    		double tempFromPoint = node.p.distanceSquaredTo(p);
    		
    		// if the distance from nearest node is bigger or equal to the distance from the node-rectangle
    		if(tempFromNearest >= node.rect.distanceSquaredTo(p)) {
    			
    			//if the distance from point is smaller than the nearest point, this is the new nearest point.
        		if(tempFromNearest > tempFromPoint) {
        			nearestPoint = node.p;
        		}
        		
        		//to find the iteration we have to check which comes first.
        		//if the right node is not null and the right node contains the point p
        		if(node.right != null && node.right.rect.contains(p)) {
        			nearestPoint = nearest(nearestPoint, node.right, p);
        			nearestPoint = nearest(nearestPoint, node.left, p);
        		}
        		else {
        			nearestPoint = nearest(nearestPoint, node.left, p);
        			nearestPoint = nearest(nearestPoint, node.right, p);
        		}
    		}
    	}
    	return nearestPoint;
    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
   
    
    //Containes main function

    public static void main(String[] args) {
    	In in = new In("100k.txt");
    	Out out = new Out();    
    	int N = in.readInt();

    	KdTree tree = new KdTree();
    	
    	
    	out.printf("Inserting %d points into tree\n", N);
    	Stopwatch timer = new Stopwatch();
    	
    	Point2D points[] = new Point2D[N];
    	for (int i = 0; i < N; i++) {
    		Point2D p = new Point2D(in.readDouble(), in.readDouble());
    		points[i] = p;
    	}
    	
    	timer = new Stopwatch();
       	for (int i = 0; i < N; i++) {
    		tree.insert(points[i]);
    	}
    	double time1 = timer.elapsedTime();
    	StdOut.printf("(%.2f seconds)\n",time1);
    	out.printf("tree.size(): %d\n", tree.size());

        tree.draw();
    }
}
