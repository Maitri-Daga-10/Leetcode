class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] freq = new int[100001];
        int max = 0;
        for (int i = 0; i < n; i++){
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            max = Math.max(max, diff);
        }
        long total = 0;
        for (int i = 1; i <= max; i++){
            total += (long) i * i * freq[i];
        }
        if (total == 0 || k == 0){
            return total;
        }
        if (k >= total){
            long sumDiff = 0;
            for (int i = 1; i <= max; i++){
                sumDiff += (long) i * freq[i];
            }
            if (k >= sumDiff) return 0;
        }
        for (int d = max; d > 0 && k > 0; d--){
            if (freq[d] == 0) continue;
            long take = Math.min(k, freq[d]);
            freq[d] -= (int) take;
            freq[d - 1] += (int) take;
            k -= take;
        }
        long ans = 0;
        for (int i = 1; i <= max; i++){
            ans += (long) i * i * freq[i];
        }
        return ans;
    }
}
