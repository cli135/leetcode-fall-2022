class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int last = 1;
        int offset = 0; // representing iteration
        while (last < n * n) {
            // top
            for (int j = offset; j < n - 1 - offset; j++) {
                matrix[offset][j] = last;
                last++;
            }
            // right
            for (int i = offset; i < n - 1 - offset; i++) {
                matrix[i][n - 1 - offset] = last;
                last++;
            }
            // bottom
            for (int j = n - 1 - offset; j >= offset + 1; j--) {
                matrix[n - 1 - offset][j] = last;
                last++;
            }
            // left
            for (int i = n - 1 - offset; i >= 1 + offset; i--) {
                matrix[i][offset] = last;
                last++;
            }
            // after four rounds around outside,
            // offset increases
            // pushing the walls further inwards
            offset++;
        }

        // then just fill in the final middle spot
        // only for odd matrices
        // because even ones won't have a middle spot
        if (n % 2 == 1) {
            matrix[n/2][n/2] = n * n;

        }
        return matrix;

    }
}