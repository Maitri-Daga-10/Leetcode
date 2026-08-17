class Solution {
    int[][] dp;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        // Prefix sum
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[l][r] = maximum score from l to r
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return solve(0, n - 1, prefix);
    }

    private int solve(int l, int r, int[] prefix) {

        // Only one stone
        if (l == r) {
            return 0;
        }

        // Already calculated
        if (dp[l][r] != -1) {
            return dp[l][r];
        }

        int ans = 0;

        // Try every possible split
        for (int k = l; k < r; k++) {

            int leftSum = prefix[k + 1] - prefix[l];
            int rightSum = prefix[r + 1] - prefix[k + 1];

            if (leftSum < rightSum) {

                // Keep left part
                ans = Math.max(
                    ans,
                    leftSum + solve(l, k, prefix)
                );

            } else if (leftSum > rightSum) {

                // Keep right part
                ans = Math.max(
                    ans,
                    rightSum + solve(k + 1, r, prefix)
                );

            } else {

                // Both sides have equal sum
                ans = Math.max(
                    ans,
                    Math.max(
                        leftSum + solve(l, k, prefix),
                        rightSum + solve(k + 1, r, prefix)
                    )
                );
            }
        }

        return dp[l][r] = ans;
    }
}
