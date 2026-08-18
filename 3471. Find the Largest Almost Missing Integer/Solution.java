class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51];
        for (int start = 0; start <= n - k; start++){
            boolean[] seen = new boolean[51];
            for (int i = start; i < start + k; i++){
                int val = nums[i];
                if (!seen[val]){
                    count[val]++;
                    seen[val] = true;
                }
            }
        }
        int ans = -1;
        for (int val = 0; val <= 50; val++){
            if (count[val] == 1){
                ans = val;
            }
        }
        return ans;
    }
}
