class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Check for empty inputs
        // if (strs==null || strs.length == 0){
        //     return new ArrayList<>();
        // }
        Map<String, List<String>> map = new HashMap<>();
        for(String s:strs){
            char[] c = s.toCharArray();    // convert string to character array
            Arrays.sort(c);  // Sort characters
            String key = new String(c);  // Create key from sorted characters

            // if key doesn't exist, create a new list
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s); //add word to its anagram group
        }
        return new ArrayList<>(map.values());
    }
}