package org.hipeoplea;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class BfsTraversal {
    private BfsTraversal() {
    }

    public static BfsResult traverse(Map<Integer, List<Integer>> graph, int startVertex) {
        if (graph == null || !graph.containsKey(startVertex)) {
            throw new IllegalArgumentException("start vertex must exist in graph");
        }

        List<BfsTraceEvent> trace = new ArrayList<>();
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();

        trace.add(new BfsTraceEvent(BfsTracePoint.START, startVertex, null));
        visited.add(startVertex);
        queue.add(startVertex);
        trace.add(new BfsTraceEvent(BfsTracePoint.INIT_QUEUE, startVertex, null));

        while (!queue.isEmpty()) {
            int current = queue.remove();
            trace.add(new BfsTraceEvent(BfsTracePoint.DEQUEUE_VERTEX, current, null));
            order.add(current);

            List<Integer> neighbors = graph.getOrDefault(current, List.of());
            for (Integer neighbor : neighbors) {
                trace.add(new BfsTraceEvent(BfsTracePoint.CHECK_NEIGHBOR, current, neighbor));
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    trace.add(new BfsTraceEvent(BfsTracePoint.ENQUEUE_NEIGHBOR, current, neighbor));
                } else {
                    trace.add(new BfsTraceEvent(BfsTracePoint.SKIP_VISITED_NEIGHBOR, current, neighbor));
                }
            }
        }

        trace.add(new BfsTraceEvent(BfsTracePoint.FINISH, null, null));
        return new BfsResult(order, trace);
    }
}
