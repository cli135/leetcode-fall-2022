import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = {{'1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'}, {'0','0','0','0','0'}};
        char[][] grid1 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        int result = solution.numIslands(grid1);
        System.out.println("The final result is:");
        System.out.println(result);
    }
    public int numIslands(char[][] grid) {
        // bfs outwards and blot out with 0s
        // leaving only one 1 as a representative
        // of that island
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int amt = breadth_first_search(grid, i , j, true);
                count += amt;
                System.out.println(amt);
            }
        }
        return count;
    }
    private int breadth_first_search(char[][] grid, int i, int j, boolean root) {

        // lesson learned: ASCII 0 and 1 are not the same as '0' and '1'
        // know the TYPE of the double array you are using, int vs char
        if (grid[i][j] == '0') {
            return 0; // explicit base case is good
            // rather than letting it go through vacuous truth
            // through a while loop, while more elegant
            // is prone to bugs because nobody (especially you)
            // are not perfect
        }

        boolean rootIsLand = false;
        if (grid[i][j] == '1') {
            // we remember land
            rootIsLand = true;
            // know the difference between incrementing by one per '1'
        }


        // starting at root grid[i][j], do a bfs outwards
        int count = 0; // sure, but are you going to do 1 max,
        // or are you accumulating more than 1?
        // here we're only returning 1 or 0 for the number
        // of connected components we found by bfs
        // bfs is a very general structure that can be implemented
        // multiple ways

        // notice m x n corresponds to i, j
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        // explored is like not sure the meaning of it,
        // but we already marked explored by doing 0s to return
        // basically to ensure that we aren't adding things
        // to the queue forever in a snake infinite loop cycle
        // the queue has to become empty at some point,
        // and we achieve this by having 0 be a dead end point
        Set<Integer> explored = new HashSet<Integer>();

        // encoding of i, j to an integer based on row major indexing
        // n is row length == num of columns, so we're good
        queue.add(i * n + j);
        explored.add(i * n + j);

        // the core of bfs -- our queue is just all the stuff
        // we need to explore at some point
        while (!queue.isEmpty()) {

            // we look at the next node in the queue / line
            Integer node = queue.remove();

            // try to avoid look before you leap
            // logic unless absolutely unavoidable
            // decode
            int a = node / n;
            int b = node % n;

            // our key base case in bfs -- the dead end
            // that prevents infinite loop queue adding
            // '0' means that we've explored it already
            // or it's not even worth exploring in the first place
            // remember it's character '0' not integer 0
            if (grid[a][b] == '0') {
                continue; // no need to look here further
            }

            // otherwise, it must be land
            // count++; // no we don't want this actually
            // because we only return 0 or 1 based
            // on the number of connected components we found
            // not actually the number of units those islands
            // were made up of
            // they all get turned to 0 anyways

            if (a > 0) {
                // has an up, let's check it out
                // if it's sea then we'll just continue;
                // and *not add any of its neighbors,
                // thereby ending the recursive nature
                // of breadth first search and providing
                // a dead-end base case to the queue adding*
                queue.add((a - 1) * n + b);
            }
            if (a < m - 1) {
                // has a down, let's check it out later
                // if it's sea we'll stop the recursion there
                queue.add((a + 1) * n + b);
            }
            if (b > 0) {
                // has a left
                // let's check it out later,
                // if land we'll recursively explore out,
                // if sea we'll end the queue adding there
                // for that branch
                queue.add(a * n + b - 1);
            }
            if (b < n - 1) {
                // has a right
                // let's go check it out later
                // maybe it's land, great we'll repeat the process
                // if water, we'll just end the process and move on elsewhere
                // to whatever is next in our queue
                // (maybe another node's up, etc.)

                // but this is not look before you leap logic,
                // this check will happen in that particular iteration
                // when we dequeue the neighbor node from the queue
                // and the key thing is, we check for '0' *before*
                // we add it's neighbors, just like we check for
                // a base case to kick us out early and stop recursion,
                // *before* we dive back into the deep stairwell that is
                // recursion and recurrence relations
                queue.add(a * n + b + 1);
            }

            // mark the spot we just explored as water now
            // ohh, this is the way we keep track of explored nodes
            // because '0' is our signal to stop searching at this point
            // because '0' is a dead end and is a way for the queue
            // to stop getting added to forever
            grid[a][b] = '0'; // we will correct for the root only later
        }
        // well, now the whole land is flooded with water '0's
        // but we do want one singular representative that this land existed
        // i mean we could just flood it all, too, i guess
        // it wouldn't matter because we aren't coming back to it or
        // it's vicinity anytime soon
        if (rootIsLand) {
            // yep we can indeed comment the below line out
            // grid[i][j] = '1'; // back to land we go
            // it's adjacent should be all water now, regardless of
            // what it was before (as a way of making it the equivalence
            // class member)
        }
        return 1;
    }
}