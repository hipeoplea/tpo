package org.hipeoplea;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BfsTraversalTest {

    @Test
    void shouldFollowReferenceTraceForTreeGraph() {
        UndirectedGraph graph = new UndirectedGraph()
                .addEdge(1, 2)
                .addEdge(1, 3)
                .addEdge(2, 4)
                .addEdge(3, 5);

        BfsResult result = BfsTraversal.traverse(graph.toAdjacencyList(), 1);

        assertEquals(List.of(1, 2, 3, 4, 5), result.visitOrder());

        List<BfsTraceEvent> expectedTrace = List.of(
                event(BfsTracePoint.START, 1, null),
                event(BfsTracePoint.INIT_QUEUE, 1, null),

                event(BfsTracePoint.DEQUEUE_VERTEX, 1, null),
                event(BfsTracePoint.CHECK_NEIGHBOR, 1, 2),
                event(BfsTracePoint.ENQUEUE_NEIGHBOR, 1, 2),
                event(BfsTracePoint.CHECK_NEIGHBOR, 1, 3),
                event(BfsTracePoint.ENQUEUE_NEIGHBOR, 1, 3),

                event(BfsTracePoint.DEQUEUE_VERTEX, 2, null),
                event(BfsTracePoint.CHECK_NEIGHBOR, 2, 1),
                event(BfsTracePoint.SKIP_VISITED_NEIGHBOR, 2, 1),
                event(BfsTracePoint.CHECK_NEIGHBOR, 2, 4),
                event(BfsTracePoint.ENQUEUE_NEIGHBOR, 2, 4),

                event(BfsTracePoint.DEQUEUE_VERTEX, 3, null),
                event(BfsTracePoint.CHECK_NEIGHBOR, 3, 1),
                event(BfsTracePoint.SKIP_VISITED_NEIGHBOR, 3, 1),
                event(BfsTracePoint.CHECK_NEIGHBOR, 3, 5),
                event(BfsTracePoint.ENQUEUE_NEIGHBOR, 3, 5),

                event(BfsTracePoint.DEQUEUE_VERTEX, 4, null),
                event(BfsTracePoint.CHECK_NEIGHBOR, 4, 2),
                event(BfsTracePoint.SKIP_VISITED_NEIGHBOR, 4, 2),

                event(BfsTracePoint.DEQUEUE_VERTEX, 5, null),
                event(BfsTracePoint.CHECK_NEIGHBOR, 5, 3),
                event(BfsTracePoint.SKIP_VISITED_NEIGHBOR, 5, 3),

                event(BfsTracePoint.FINISH, null, null)
        );

        assertEquals(expectedTrace, result.trace());
    }

    @Test
    void shouldFollowReferenceTraceForCycleGraph() {
        UndirectedGraph graph = new UndirectedGraph()
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(3, 1);

        BfsResult result = BfsTraversal.traverse(graph.toAdjacencyList(), 1);

        assertEquals(List.of(1, 2, 3), result.visitOrder());

        List<BfsTraceEvent> expectedTrace = List.of(
                event(BfsTracePoint.START, 1, null),
                event(BfsTracePoint.INIT_QUEUE, 1, null),

                event(BfsTracePoint.DEQUEUE_VERTEX, 1, null),
                event(BfsTracePoint.CHECK_NEIGHBOR, 1, 2),
                event(BfsTracePoint.ENQUEUE_NEIGHBOR, 1, 2),
                event(BfsTracePoint.CHECK_NEIGHBOR, 1, 3),
                event(BfsTracePoint.ENQUEUE_NEIGHBOR, 1, 3),

                event(BfsTracePoint.DEQUEUE_VERTEX, 2, null),
                event(BfsTracePoint.CHECK_NEIGHBOR, 2, 1),
                event(BfsTracePoint.SKIP_VISITED_NEIGHBOR, 2, 1),
                event(BfsTracePoint.CHECK_NEIGHBOR, 2, 3),
                event(BfsTracePoint.SKIP_VISITED_NEIGHBOR, 2, 3),

                event(BfsTracePoint.DEQUEUE_VERTEX, 3, null),
                event(BfsTracePoint.CHECK_NEIGHBOR, 3, 2),
                event(BfsTracePoint.SKIP_VISITED_NEIGHBOR, 3, 2),
                event(BfsTracePoint.CHECK_NEIGHBOR, 3, 1),
                event(BfsTracePoint.SKIP_VISITED_NEIGHBOR, 3, 1),

                event(BfsTracePoint.FINISH, null, null)
        );

        assertEquals(expectedTrace, result.trace());
    }

    @Test
    void shouldVisitOnlyConnectedComponentFromStartVertex() {
        UndirectedGraph graph = new UndirectedGraph()
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(10, 11);

        BfsResult result = BfsTraversal.traverse(graph.toAdjacencyList(), 1);

        assertEquals(List.of(1, 2, 3), result.visitOrder());
    }

    @Test
    void shouldThrowWhenStartVertexMissing() {
        UndirectedGraph graph = new UndirectedGraph()
                .addEdge(1, 2);

        assertThrows(IllegalArgumentException.class,
                () -> BfsTraversal.traverse(graph.toAdjacencyList(), 42));
    }

    private static BfsTraceEvent event(BfsTracePoint point, Integer current, Integer neighbor) {
        return new BfsTraceEvent(point, current, neighbor);
    }
}
