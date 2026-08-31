class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;      
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;
        int position = 1;
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null && curr.next != null){
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)){
                if (first == -1){
                    first = position;
                } 
                else{
                    int distance = position - prevCritical;
                    minDistance = Math.min(minDistance, distance);
                    maxDistance = position - first;
                }
                prevCritical = position;
            }
            prev = curr;
            curr = curr.next;
            position++;
        }
        if (maxDistance == -1){
            return new int[]{-1, -1};
        }
        return new int[]{minDistance, maxDistance};
    }
}
