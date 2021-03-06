package S1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
	
	public int size;
	public int exp;
	public int count;
	public double[] threshold;
	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T)
	{

		if(N <= 0) 
		{
			throw new java.lang.IllegalArgumentException();
		}
		if(T <= 0) 
		{
			throw new java.lang.IllegalArgumentException();
		}
		
		size = N;
		exp = T;
		count = 0;

		
		threshold = new double[exp];
		
		Stopwatch time = new Stopwatch();
		for(int i = 0; i < T; i++)
		{
			Percolation percolation = new Percolation(N);

			while(!percolation.percolates())
			{
				int row = StdRandom.uniform(N);
				int col = StdRandom.uniform(N);

				percolation.open(row,col);
			}
			threshold[i] = percolation.numberOfOpenSites()/(Math.pow(size,2));
		}
		System.out.println("time:             = " + time.elapsedTime() + "\n");
		
		
				
	}
	
	// sample mean of percolation threshold
	public double mean() 
	{
		return StdStats.mean(threshold);
	}
	
	// sample standard deviation of percolation threshold
	public double stddev()
	{
		return StdStats.stddev(threshold);
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLow()
	{
		return (mean() - (1.96 * stddev()) / Math.sqrt(exp));
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHigh()
	{
		return (mean() + (1.96 * stddev()) / Math.sqrt(exp));
	}
	
	public static void main(String[] args) {
		
		PercolationStats newstat = new PercolationStats(50, 800);
		
		System.out.println("Example values after creating PercolationStats(" + newstat.size  + ", " + newstat.exp + ")");
		System.out.println("mean()            = " + newstat.mean());
		System.out.println("stddev()          = " + newstat.stddev());
		System.out.println("confidenceLow()   = " + newstat.confidenceLow());
		System.out.println("confidenceHigh()  = " + newstat.confidenceHigh());
	}
}
