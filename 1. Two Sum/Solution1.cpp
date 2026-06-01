class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> ts;
        for(int i=0; i<nums.size(); i++){
            int current = nums[i];
            int needed = target-current;
            if(ts.find(needed)!=ts.end()){
                return {ts[needed], i};
            }
            ts[current] = i;
        }
        return {};
    }
};