import java.util.ArrayList;
import java.util.List;

class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        backtrack(nums, 0, list);
        return list;
    }
    public void backtrack(int[] nums, int start_idx, List<List<Integer>> list) {
        for (int i = start_idx; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> subset1 = new ArrayList<>();
            subset1.add(num);
            backtrack(nums, start_idx + 1, list);

            list.add(subset1);
            List<Integer> subset2 = new ArrayList<>();
            // shallow copy but with Integer so we should be fine
            subset2.addAll(subset1);

            subset2.remove(subset2.size() - 1);
            list.add(subset2);
        }
    }
    public static void main(String[] args) {
        SubsetsII obj = new SubsetsII();
        int[] nums = {1, 2, 2};
        List<List<Integer>> list = obj.subsetsWithDup(nums);
        for (List<Integer> row : list) {
            for (Integer num : row) {
                System.out.print(num + ",");
            }
            System.out.println();
        }
    }
}