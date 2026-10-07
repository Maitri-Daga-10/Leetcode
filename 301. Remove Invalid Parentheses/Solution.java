class Solution{
    private Set<String> result = new HashSet<>();
    public List<String> removeInvalidParentheses(String s){
        int leftRemove = 0;
        int rightRemove = 0;
        for (char c : s.toCharArray()){
            if (c == '('){
                leftRemove++;
            } 
            else if (c == ')'){
                if (leftRemove > 0){
                    leftRemove--;
                } 
                else{
                    rightRemove++;
                }
            }
        }
        dfs(s, 0, leftRemove, rightRemove, 0, new StringBuilder());
        return new ArrayList<>(result);
    }
    private void dfs(String s, int index,
                     int leftRemove, int rightRemove,
                     int balance, StringBuilder current){
        if (index == s.length()){
            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0){
                result.add(current.toString());
            }
            return;
        }
        char c = s.charAt(index);
        if (c == '(' && leftRemove > 0){
            dfs(s, index + 1,
                leftRemove - 1, rightRemove,
                balance, current);
        }
        if (c == ')' && rightRemove > 0){
            dfs(s, index + 1,
                leftRemove, rightRemove - 1,
                balance, current);
        }
        if (c != '(' && c != ')'){
            current.append(c);
            dfs(s, index + 1,
                leftRemove, rightRemove,
                balance, current);
            current.deleteCharAt(current.length() - 1);
        }
        else if (c == '('){
            current.append(c);
            dfs(s, index + 1,
                leftRemove, rightRemove,
                balance + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
        else{
            if (balance > 0){
                current.append(c);
                dfs(s, index + 1,
                    leftRemove, rightRemove,
                    balance - 1, current);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }
}
