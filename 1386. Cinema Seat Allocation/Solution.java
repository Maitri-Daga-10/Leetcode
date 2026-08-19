class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] seat : reservedSeats){
            int row = seat[0];
            int col = seat[1];
            map.put(row, map.getOrDefault(row, 0) | (1 << (col - 1)));
        }
        long answer = (long) (n - map.size()) * 2;
        for (int mask : map.values()){
            int left = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4);
            int right = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8);
            int middle = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6);
            boolean canLeft = (mask & left) == 0;
            boolean canRight = (mask & right) == 0;
            boolean canMiddle = (mask & middle) == 0;
            if (canLeft && canRight){
                answer += 2;
            } 
            else if (canLeft || canRight || canMiddle){
                answer += 1;
            }
        }
        return (int) answer;
    }
}
