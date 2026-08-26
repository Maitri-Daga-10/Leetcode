class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String answer = "";
        int minLen = Integer.MAX_VALUE;
        for (int left = 0; left < n; left++){
            int ones = 0;
            for (int right = left; right < n; right++){
                if (s.charAt(right) == '1'){
                    ones++;
                }
                if (ones == k){
                    String curr = s.substring(left, right + 1);
                    if (curr.length() < minLen){
                        minLen = curr.length();
                        answer = curr;
                    } 
                    else if (curr.length() == minLen &&
                               curr.compareTo(answer) < 0){
                        answer = curr;
                    }
                    break;
                }
            }
        }
        return answer;
    }
}
