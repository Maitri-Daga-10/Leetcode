# 1. Two Sum

## Problem

Given an array `nums` and a target value, return the indices of the two numbers whose sum equals the target.

---

# Core Idea

For every number, calculate:

```text
needed = target - current
```
Instead of searching the entire array for `needed`, store previously seen numbers in a HashMap.
If `needed` already exists in the HashMap, we have found our answer.

---

# Why HashMap?

HashMap stores:

```text
Key   = Number
Value = Index
```

Example:

```text
2 -> 0
7 -> 1
11 -> 2
```

This allows us to check if a number exists in approximately O(1) time.

---

# Why NOT ArrayList?

Suppose we want to check:

```text
Does 7 exist?
```
ArrayList scans elements one by one:

```text
2
7
11
15
```
Lookup Time:

```text
O(n)
```

If this lookup is done for every element:

```text
O(n) × O(n)
= O(n²)
```

Too slow.

HashMap lookup:

```text
O(1)
```

Therefore:

```text
Total Time = O(n)
```

---

# Why Key = Number and Value = Index?

We are searching for:

```text
needed = target - current
```

We need to quickly check whether a NUMBER exists.

Therefore:

```text
Key = Number
Value = Index
```

NOT:

```text
Key = Index
Value = Number
```

because we search using the number, not the index.

---

# Algorithm

1. Create an empty HashMap.
2. Traverse the array.
3. For each element:

   * Calculate:

```text
needed = target - nums[i]
```

* Check if `needed` exists in HashMap.
* If yes:

  * Return stored index and current index.
* Else:

  * Store current number and its index.

---

# Dry Run

### Input

```text
nums = [2,7,11,15]
target = 9
```

### Start

```text
HashMap = {}
```

### i = 0

```text
current = 2
needed = 7
```

7 not found.

Store:

```text
2 -> 0
```

HashMap:

```text
{2 : 0}
```

---

### i = 1

```text
current = 7
needed = 2
```

2 found in HashMap.

```text
2 -> 0
```

Return:

```text
[0,1]
```

---

# Important Observation

Always:

```text
Check first
Then store
```

### Why?

Example:

```text
nums = [3,3]
target = 6
```

First 3:

```text
need = 3
```

Not found.

Store:

```text
3 -> 0
```

Second 3:

```text
need = 3
```

Found.

Return:

```text
[0,1]
```

If you store before checking, you may accidentally use the same element twice.

---

# Code

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {

        // key = number
        // value = index
        unordered_map<int, int> ts;

        for (int i = 0; i < nums.size(); i++) {

            int current = nums[i];

            int needed = target - current;

            // Check if needed number already exists
            if (ts.find(needed) != ts.end()) {
                return {ts[needed], i};
            }

            // Store current number and index
            ts[current] = i;
        }

        return {};
    }
};
```

---

# Complexity Analysis

### Time Complexity

```text
O(n)
```

* Traverse array once
* HashMap lookup ≈ O(1)

---

### Space Complexity

```text
O(n)
```

* In worst case all elements are stored in HashMap

---

# Pattern Learned

```text
For each element:
    Find what is needed
    Check if it was seen before
    If yes -> answer found
    Else store current element
```

This pattern appears in many problems involving HashMap lookups.

---

# Important Concept

Instead of asking:

```text
Which number should I add?
```

Ask:

```text
What number do I need?
```

Then search for that number efficiently.

---

# Memory Trigger

```text
Two Sum = HashMap + Complement
```

where

```text
Complement = target - current
```

---

# Common Mistakes

Using nested loops → O(n²)
Using ArrayList for lookup
Using:
```text
Key = Index
Value = Number
```
Storing before checking
Returning values instead of indices
Using the same element twice

---

# Revision in 10 Seconds

```text
needed = target - current

If needed exists in HashMap:
    return answer

Else:
    store current and index

HashMap:
Key = Number
Value = Index

Time = O(n)
Space = O(n)
```
