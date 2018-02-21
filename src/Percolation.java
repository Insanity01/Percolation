import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//A program that estimates the Percolation Threshold using the Monte Carlo Method

//public class Percolation {
//    public Percolation(int n)                // create n-by-n grid, with all sites blocked
//    public    void open(int row, int col)    // open site (row, col) if it is not open already
//    public boolean isOpen(int row, int col)  // is site (row, col) open?

//    public boolean isFull(int row, int col)  // is site (row, col) full?
//    public     int numberOfOpenSites()       // number of open sites
//    public boolean percolates()              // does the system percolate?
//
//    public static void main(String[] args)   // test client (optional)
//}


public class Percolation {

    // 0 represents a blocked site
    // 1 represents an open site

    private WeightedQuickUnionUF gridUnion;
    private int[][] grid; // a grid object that represents
    private int gridSize; //represents the width and height of the grid
    private int openSites = 0;


    public static void main(String[] args) {
        try {
            Percolation p = new Percolation(20);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    public Percolation(int n) throws IllegalArgumentException {

        if ((n <= 0)) {
            throw new IllegalArgumentException("Illegal sized grid");
        }

        gridSize = n;
        grid = new int[n][n];
        gridUnion = new WeightedQuickUnionUF(n * n); //400 potential nodes
    }

    public void open(int row, int col) {
        if (!isOpen(row - 1, col - 1)) {
            grid[row - 1][col - 1] = 1;
            openSites++;
        }
    }

    public boolean isOpen(int row, int col) {
        System.out.println(grid[row][col]);
        return grid[row - 1][col - 1] != 0;
    }

    public boolean isFull(int row, int col) {
        return true;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return true;
    }

    private void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            System.out.println();
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
        }

        gridUnion.toString();
    }
}


