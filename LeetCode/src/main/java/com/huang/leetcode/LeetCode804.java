package com.huang.leetcode;

/**
 * @author : I325805
 * @description:
 */
public class LeetCode804 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {

        if(grid.length < 1) {
            return 0;
        }

        int[] rows = new int[grid.length];
        int[] cols = new int[grid[0].length];

        for(int i = 0; i<grid.length; i++) {
            rows[i] = grid[i][0];
            for(int j=0; j<grid[i].length; j++) {
                rows[i] = Math.max(rows[i], grid[i][j] );
            }
        }

        for(int i=0; i<grid[0].length; i++) {
            cols[i] = grid[0][i];
            for(int j=0; j<grid.length; j++) {
                cols[i] = Math.max(cols[i], grid[j][i]);
            }
            }

        int result = 0;

        for(int i=0; i<grid.length;i++) {
            for(int j=0;j<grid[0].length; j++){
                result += Math.min(rows[i], cols[j]) - grid[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LeetCode804 solution = new LeetCode804();
        int[][] grid = {{59,88,44},{3,18,38},{21,26,51}};
        solution.maxIncreaseKeepingSkyline(grid);
    }
}
