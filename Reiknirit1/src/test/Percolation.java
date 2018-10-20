package test;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int size;                       //size of grid
    private int counter;                    //number of open sites
    private boolean[][] grid;               //the grid itself
    private int top;                        //virtual top, "connected" to all sites in row 0
    private int bottom;                     //virtual bottom
    private WeightedQuickUnionUF wqf;       //instance of the WeightedQuickUnionUf class
    private WeightedQuickUnionUF wqf2;      //another instance of the class to prevent backwash

    private int xyTo1d(int x, int y) {      //creates a unique number for a site

        return (x * size + y);
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public Percolation(int N) {                 //create N-by-N, with all sites initially blocked

        if(N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        size = N;
        counter = 0;
        top = (N * N);
        bottom = (N * N) + 1;
        grid = new boolean[N][N];

        wqf = new WeightedQuickUnionUF((N * N) + 2);        //+2 for the virtual top and bottom
        wqf2 = new WeightedQuickUnionUF(N * N + 1);         //here we exclude the virtual bottom

    }


    public void open(int row, int col) {        //open the site (row, col) if it is not open already

        validate(row, col);

        if (!isOpen(row, col)) {
            grid[row][col] = true;
            counter++;

            if (size > 1) {
                if (row == 0) {                                //if site is in row 0 we immediately connect it to the virtual top
                    wqf.union(xyTo1d(row, col), top);
                    wqf2.union(xyTo1d(row, col), top);
                    if (isOpen(row + 1, col)) {          //if the site below it is open, connect those two
                        wqf.union(xyTo1d(row, col), xyTo1d(row + 1, col));
                        wqf2.union(xyTo1d(row, col), xyTo1d(row + 1, col));
                    }
                }
                else if (row == size - 1) {
                    wqf.union(xyTo1d(row, col), bottom);      //if site is in the bottom row we immediately connect it to the virtual bottom
                    if (isOpen(row - 1, col)) {          //if the site above it is open, connect those two
                        wqf.union(xyTo1d(row, col), xyTo1d(row - 1, col));
                        wqf2.union(xyTo1d(row, col), xyTo1d(row - 1, col));
                    }
                }
                else {
                    if (row > 0 && isOpen(row - 1, col)) {      //if the site is not in row 0, we check if the site above it is open
                        wqf.union(xyTo1d(row, col), xyTo1d(row - 1, col));      //and connect the two if that's the case
                        wqf2.union(xyTo1d(row, col), xyTo1d(row - 1, col));
                    }
                    if (row < size - 1 && isOpen(row + 1, col)) {   //if the site is not in the bottom row, we check if the site below it
                        wqf.union(xyTo1d(row, col), xyTo1d(row + 1, col));  //is open, and connect the two if that's the case
                        wqf2.union(xyTo1d(row, col), xyTo1d(row + 1, col));
                    }
                }
                if (col == 0 && isOpen(row, col + 1)) {                 //if the site is in column 0 we check if the site to the right
                    wqf.union(xyTo1d(row, col), xyTo1d(row, col + 1));      //is open, and connect the two if that's true
                    wqf2.union(xyTo1d(row, col), xyTo1d(row, col + 1));
                }
                else if (col == size - 1 && isOpen(row, col - 1)) {     //if the site is in the rightmost column we check if the site
                    wqf.union(xyTo1d(row, col), xyTo1d(row, col - 1));      //to the left is open and connect the two if that's true
                    wqf2.union(xyTo1d(row, col), xyTo1d(row, col - 1));
                }
                else {
                    if (col > 0 && isOpen(row, col - 1)) {                  //if the site is not in column 0 we check if the site to the left
                        wqf.union(xyTo1d(row, col), xyTo1d(row, col - 1));      //is open, and connect the two if that's true
                        wqf2.union(xyTo1d(row, col), xyTo1d(row, col - 1));
                    }
                    if (col < size - 1 && isOpen(row, col + 1)) {      //if the site is not in the rightmost column we check if the site
                        wqf.union(xyTo1d(row, col), xyTo1d(row, col + 1));      //to the right is open, and connect the two if that's true
                        wqf2.union(xyTo1d(row, col), xyTo1d(row, col + 1));
                    }
                }

            }
            else {                                                          //if the array is of size 1 the site connects to
                wqf.union(0, top);                                      //both top and bottom
                wqf2.union(0, top);
                wqf.union(0, bottom);
            }
        }
    }

    public boolean isOpen(int row, int col) {   //is the site (row, col) open?

        validate(row, col);

        return grid[row][col];
    }

    public boolean isFull(int row, int col) {   //is the site (row, col) full?

        validate(row, col);

        return wqf2.connected(top, xyTo1d(row, col));
    }

    public int numberOfOpenSites() {            //number of open sites
        return counter;
    }

    public boolean percolates() {               //does the system percolate?
        return wqf.connected(bottom, top);
    }

    public static void main(String[] args) {    //unit testing (required)

        Percolation p = new Percolation(5);

        System.out.println("Opening (1, 2)");
        p.open(1, 2);
        System.out.println("Opening (2, 3)");
        p.open(2, 3);
        System.out.println("Opening (4, 0)");
        p.open(4, 0);
        System.out.println("Is (0, 2) open? " + p.isOpen(0, 2));
        System.out.println("Opening (0, 2)");
        p.open(0, 2);
        System.out.println("Is (2, 3) full? " + p.isFull(2, 3));
        System.out.println("Opening (1, 3)");
        p.open(1, 3);
        System.out.println("Is (2, 3) full? " + p.isFull(2, 3));
        System.out.println("Number of open sites: " + p.numberOfOpenSites());
        System.out.println("Opening (3, 2)");
        p.open(3, 2);
        System.out.println("Opening (4, 2)");
        p.open(4, 2);
        System.out.println("Does it percolate? " + p.percolates());
        System.out.println("Opening (3, 3)");
        p.open(3, 3);
        System.out.println("Does it percolate? " + p.percolates());
        System.out.println("Is (4, 0) full? " + p.isFull(4, 0));
    }
}
