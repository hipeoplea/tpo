# Task 2: BFS for undirected graph

Characteristic points inside algorithm:
- `START` - start of traversal.
- `INIT_QUEUE` - start vertex marked visited and enqueued.
- `DEQUEUE_VERTEX` - vertex removed from queue.
- `CHECK_NEIGHBOR` - next adjacent vertex considered.
- `ENQUEUE_NEIGHBOR` - neighbor first seen, marked visited and enqueued.
- `SKIP_VISITED_NEIGHBOR` - neighbor already visited.
- `FINISH` - queue is empty, traversal finished.

Reference datasets used in tests:
1. Tree graph: edges `(1-2), (1-3), (2-4), (3-5)`, start `1`.
2. Cycle graph: edges `(1-2), (2-3), (3-1)`, start `1`.
3. Disconnected graph: edges `(1-2), (2-3), (10-11)`, start `1`.
4. Invalid start vertex: start `42` absent from graph.

For datasets 1 and 2 tests compare the full actual trace with the reference trace.
