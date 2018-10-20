package test;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {


    private int experiments;        //number of experiments (T)
    private int size;               //size of array (N)
    private double[] results;       //array of percolation thresholds (fractions of open sites)

    public PercolationStats(int N, int T) {     //perform T independent experiments on an N-by-N grid

        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        size = N;
        experiments = T;
        results = new double[experiments];
        Percolation percolation = new Percolation(size);

        int row = 0;
        int col = 0;

        Stopwatch sw = new Stopwatch();

        for(int i = 0; i < experiments; i++) {      //perform T experiments

            while (!percolation.percolates()) {

                row = StdRandom.uniform(size);      //we keep opening random sites while it hasn't percolated
                col = StdRandom.uniform(size);

                percolation.open(row, col);

            }

            results[i] = (double) percolation.numberOfOpenSites() / (size * size);
            //add the percolation threshold (the fraction) to the array
        }
        System.out.println(sw.elapsedTime());

    }

    public double mean() {                      //sample mean of percolation threshold
        return StdStats.mean(results);
    }

    public double stddev() {                    //sample standard deviation of percolation threshold
        return StdStats.stddev(results);
    }

    public double confidenceLow() {             //low endpoint of 95% confidence interval
        return mean() - ((1.96*stddev()) / Math.sqrt(experiments));
    }

    public double confidenceHigh() {            //high endpoint of 95% confidence interval
        return mean() + ((1.96*stddev()) / Math.sqrt(experiments));
    }

    public static void main(String[] args) {

        PercolationStats ps = new PercolationStats(200, 100);
        System.out.println("mean \t\t\t\t = " + ps.mean());
        System.out.println("standard deviation \t = " + ps.stddev());
        System.out.println("confidence low \t\t = " + ps.confidenceLow());
        System.out.println("confidence high \t = " + ps.confidenceHigh());

    }
}
