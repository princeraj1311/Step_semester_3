#CATEGORY B - Answers

## Part B - Quiz

1. C
2. B
3. D
4. C
5. C
6. D
7. C
8. C
9. C
10. C

## Part C - Concept Questions

### 1

At the head, a new node only needs to store the old head in its `next` reference and then replace the head reference. This takes constant time, O(1), and does not depend on list length. With only a head reference, tail insertion requires traversing every node to find the last node, so it is O(n). Maintaining a tail reference allows the last node to be reached directly and linked to the new node in O(1).

### 2

Deleting a node requires updating the predecessor's `next` reference so it skips the deleted node. A singly linked list has no backward reference, so the predecessor must be found by traversing from the head. In a doubly linked list, the node has `prev` and `next` references. Given a direct reference to the node, both neighbors can be relinked directly, so deletion is O(1).

### 3

Arrays store elements contiguously, allowing the CPU to load neighboring elements in cache lines and prefetch subsequent values effectively. Linked-list nodes are usually allocated at separate, unrelated memory locations. Traversing it therefore follows a sequence of pointers and causes irregular memory access, more cache misses, and additional memory indirection, even though the asymptotic traversal count is O(n).

### 4

Relinking two sorted linked lists reuses their existing nodes and changes only `next` references. Apart from constant temporary pointers, no O(n) or O(m+n) auxiliary structure is needed, so the additional space is O(1). Merging sorted arrays normally allocates a new array and copies elements into it, requiring O(m+n) additional space.

### 5

The `next` pointer identifies the immediately following node and moves the traversal cursor forward. A standard singly linked list ends at a node whose `next` is `null`. The loop `while (current != null)` is reliable because every node except the tail contains another node in `next`, while the tail must use `null` to indicate that no successor exists.
