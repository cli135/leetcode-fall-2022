import java.util.Arrays;

class WordSearch {
    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
//        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
//        String word = "ABCESEEEFS";
//        System.out.println(ws.exist(board, word));
        char[][] board2 = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        String word2 = "ABCEFSADEESE";
        System.out.println(ws.exist(board2, word2));

    }
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        boolean result = false;
        // for all possible starting positions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = result || existStartingAt(board, word, new boolean[n][m], i, j);
            }
        }
        // return true if any one of them works
        return result;
    }
    private boolean existStartingAt(char[][] board, String word, boolean[][] alreadyUsed, int i, int j) {
        // base case true, made it to the end
        if (word.length() == 0) {
            return true; // vacuous truth
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false; // bounds checking
        }
        // can't reuse spots in the board
        if (alreadyUsed[i][j]) {
            return false;
        }
        // comparing first char with the spot we're at
        char firstChar = word.charAt(0);
        if (firstChar == board[i][j]) {
            // recurse on all four paths
            alreadyUsed[i][j] = true;
            boolean[][] savePoint = twoDimensionalArrayDeepCopyPrimitivesOnly(alreadyUsed);
            boolean left = existStartingAt(board, word.substring(1), alreadyUsed, i, j - 1);
//            oh you have to do deep copies here you can't do shallow copies
//            since you are doing shallow copies now
//            since we need to reset hard at each direction recursion call
            // alreadyUsed = twoDimensionalArrayDeepCopyPrimitivesOnly(savePoint);
            boolean right = existStartingAt(board, word.substring(1), alreadyUsed, i, j + 1);
            // alreadyUsed = twoDimensionalArrayDeepCopyPrimitivesOnly(savePoint);
            boolean up = existStartingAt(board, word.substring(1), alreadyUsed, i - 1, j);
            // alreadyUsed = twoDimensionalArrayDeepCopyPrimitivesOnly(savePoint);
            boolean down = existStartingAt(board, word.substring(1), alreadyUsed, i + 1, j);
            // alreadyUsed = twoDimensionalArrayDeepCopyPrimitivesOnly(savePoint);
            alreadyUsed[i][j] = false;
            return left || right || up || down;
        }
        else {
            return false; // characters don't match at this point
        }
    }
    public boolean[][] twoDimensionalArrayDeepCopyPrimitivesOnly(boolean[][] board) {
        boolean[][] result = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }
}