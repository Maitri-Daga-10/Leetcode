# 203. Remove Linked List Elements

## Problem

Given the head of a linked list and a value `val`, remove all nodes whose value equals `val`.

---

# Core Idea

We do not remove a node directly.
Instead, we change the link:
```text
current → node_to_delete → next_node
```

becomes:
```text
current → next_node
```

using:
```cpp
current->next = current->next->next;
```

---
# Why Dummy Node?
Problem:
```text
6 → 1 → 2
```

If head itself needs to be deleted, we must update head.
To avoid special handling, create:

```text
dummy → head
```
Now every node has a previous node.
---

# Why Check current->next ?
Deletion requires access to the previous node.
To remove:

```text
2 → 6 → 3
```

we modify:
```cpp
2->next
```

Therefore we stand at:
```cpp
current
```
and inspect:

```cpp
current->next
```

---

# Algorithm

1. Create dummy node.
2. Connect dummy to head.
3. Set current = dummy.
4. While current->next exists:

   * If current->next->val == val:

     * Skip the node.
   * Else:

     * Move current forward.
5. Return dummy.next.

---

# Important Observation

### If node is deleted

```cpp
current->next = current->next->next;
```

DO NOT move current.

Reason:

```text
1 → 6 → 6 → 3
```

After deleting first 6:

```text
1 → 6 → 3
```

Need to check second 6 as well.

---

### If node is NOT deleted

Move:

```cpp
current = current->next;
```

---

# Dry Run

Input:

```text
1 → 2 → 6 → 3 → 4 → 5 → 6
val = 6
```

Create:

```text
dummy → 1 → 2 → 6 → 3 → 4 → 5 → 6
```

Remove first 6:

```text
dummy → 1 → 2 → 3 → 4 → 5 → 6
```

Remove second 6:

```text
dummy → 1 → 2 → 3 → 4 → 5
```

Return:

```text
1 → 2 → 3 → 4 → 5
```

---

# Code

```cpp
class Solution {
public:
    ListNode* removeElements(ListNode* head, int val) {

        ListNode dummy(0);
        dummy.next = head;

        ListNode* current = &dummy;

        while (current->next != nullptr) {

            if (current->next->val == val) {
                current->next = current->next->next;
            }
            else {
                current = current->next;
            }
        }

        return dummy.next;
    }
};
```

---

# Complexity

### Time

```text
O(n)
```

Each node visited once.

### Space

```text
O(1)
```

Only pointers used.

---

# Linked List Concepts Learned

### Dummy Node

```cpp
ListNode dummy(0);
dummy.next = head;
```

Used to handle head deletion easily.

---

### Pointer vs Object

Object:

```cpp
ListNode dummy(0);
```

Access:

```cpp
dummy.next
```

Pointer:

```cpp
ListNode* current = &dummy;
```

Access:

```cpp
current->next
```

---

### Address Operator

```cpp
&dummy
```

means:

```text
address of dummy
```

---

# Common Mistakes
Using:
```cpp
ListNode* current = dummy;
```
Correct:
```cpp
ListNode* current = &dummy;
```
---
Using:
```cpp
current++;
```
Linked lists do not use index-based movement.
Correct:
```cpp
current = current->next;
```
---
Moving current after deletion
May skip consecutive nodes.

---
Forgetting dummy node
Creates extra head-handling cases.

---

# Memory Trigger

```text
Linked List Deletion
=
Dummy Node
+
Check current->next
+
Skip node using:

current->next = current->next->next
```
