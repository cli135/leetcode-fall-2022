class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        # # len array
        # dp = [0 for i in range(len(nums))]
        # # what it ends on
        # ends_on = [0 for i in range(len(nums))]
        # for j in range(len(nums)):
        #     for i in range(j + 1):
        #         # check [i, j]
        # guard check
        if len(nums) == 0:
            return 0
        dp = [1 for i in range(len(nums))]
        # by default, base case
        dp[len(nums) - 1] = 1
        # recurrence
        for i in range(len(nums) - 2, -1, -1):
            for j in range(i + 1, len(nums)):
                if nums[i] < nums[j] and dp[i] <= 1 + dp[j]:
                    dp[i] = 1 + dp[j]
        return max(dp)
        # [5, 4, 3, 2, 1, 3, 0,  2, 1]
        # [1, 3, 6, 7, 9, 4, 10, 5, 6]
                
