
class Solution {
    private static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        long[] sum = new long[4 * n];
        long[] sumSq = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long answer = 0;

        for (int j = 0; j < n; j++) {
            int left = last[nums[j]] + 1;

            update(1, 0, n - 1, left, j, sum, sumSq, lazy);

            answer = (answer + sumSq[1]) % MOD;
            last[nums[j]] = j;
        }

        return (int) answer;
    }

    private void apply(int node, int length, long delta,
                       long[] sum, long[] sumSq, long[] lazy) {
        long oldSum = sum[node];
        long d = delta % MOD;
        long len = length % MOD;

        long increase = (2L * d % MOD * oldSum) % MOD;
        increase = (increase + d * d % MOD * len % MOD) % MOD;

        sumSq[node] = (sumSq[node] + increase) % MOD;
        sum[node] = (oldSum + d * len) % MOD;
        lazy[node] = (lazy[node] + d) % MOD;
    }

    private void pushDown(int node, int start, int end,
                          long[] sum, long[] sumSq, long[] lazy) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        long delta = lazy[node];

        apply(node * 2, mid - start + 1, delta,
              sum, sumSq, lazy);

        apply(node * 2 + 1, end - mid, delta,
              sum, sumSq, lazy);

        lazy[node] = 0;
    }

    private void update(int node, int start, int end,
                        int left, int right,
                        long[] sum, long[] sumSq, long[] lazy) {
        if (right < start || end < left) {
            return;
        }

        if (left <= start && end <= right) {
            apply(node, end - start + 1, 1,
                  sum, sumSq, lazy);
            return;
        }

        pushDown(node, start, end, sum, sumSq, lazy);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right,
               sum, sumSq, lazy);

        update(node * 2 + 1, mid + 1, end, left, right,
               sum, sumSq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }
}
