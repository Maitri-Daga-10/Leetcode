class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> remove = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (ch == '('){
                stack.push(i);
            }
            else if (ch == ')'){
                if (!stack.isEmpty()){
                    stack.pop();
                } 
                else{
                    remove.add(i);
                }
            }
        }
        while (!stack.isEmpty()){
            remove.add(stack.pop());
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (!remove.contains(i)){
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}
