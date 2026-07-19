class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++){
            int lcm = 1;
            for (int j = i; j < n; j++){
                if (k % nums[j] != 0)
                    break;
                lcm = lcm(lcm, nums[j]);
                if (lcm == k)
                    ans++;
                if (lcm > k)
                    break;
            }
        }
        return ans;
    }
    private int gcd(int a, int b){
        while (b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    private int lcm(int a, int b){
        return a / gcd(a, b) * b;
    }
}
