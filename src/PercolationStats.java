import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private double[] results;
    private final int trials, size;

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        Stopwatch stopwatch = new Stopwatch();
        System.out.println("Running Monte Carlo Simulation..." + size + " x " + size + " grid, " + trials + " trials");
        PercolationStats percolationTest = new PercolationStats(size, trials);
        System.out.println();
        System.out.println("Time taken is " + stopwatch.elapsedTime() + " seconds");
        System.out.println("Percolation threshold is " + percolationTest.mean());
        System.out.println("Stddev is " + percolationTest.stddev());
        System.out.println("95% confidence interval is " + percolationTest.confidenceLo() + ", " + percolationTest.confidenceHi());
    }

    public PercolationStats(int size, int trials) {// perform trials independent experiments on an n-by-n grid
        checkInputs(size, trials);
        this.trials = trials;
        this.size = size;
        results = new double[trials];
        start();
    }

    private void start() {
        for (int i = 0; i < trials; i++) {
            results[i] = runPercolationTest(size);
        }
    }

    private double runPercolationTest(int size) {
        Percolation percolationObj = new Percolation(size);
        while (!percolationObj.percolates()) {
            int randomRow = random(size);
            int randomCol = random(size);
            percolationObj.open(randomRow, randomCol);
        }
        return (double) percolationObj.numberOfOpenSites() / (double) (size * size);
    }

    private void checkInputs(int size, int trials) {
        //java.lang.IllegalArgumentException if either n ≤ 0 or trials ≤ 0.
        if (size <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private int random(int size) {
        return StdRandom.uniform(1, size + 1);
    }

    public double mean() { // sample mean of percolation threshold
        return StdStats.mean(results);

    }

    public double stddev() {// sample standard deviation of percolation threshold
        return StdStats.stddev(results);

    }

    private double confidence() {
        return (1.96 * stddev() / Math.sqrt(results.length));

    }

    public double confidenceLo() {// low  endpoint of 95% confidence interval

        return mean() - confidence();

    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return mean() + confidence();
    }

}
