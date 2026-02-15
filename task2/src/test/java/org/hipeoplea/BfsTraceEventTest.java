package org.hipeoplea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BfsTraceEventTest {

    @Test
    void shouldReturnValuesFromGetters() {
        BfsTraceEvent event = new BfsTraceEvent(BfsTracePoint.CHECK_NEIGHBOR, 2, 5);

        assertEquals(BfsTracePoint.CHECK_NEIGHBOR, event.getPoint());
        assertEquals(2, event.getCurrentVertex());
        assertEquals(5, event.getNeighborVertex());
    }

    @Test
    void shouldHaveSameHashCodeForEqualObjects() {
        BfsTraceEvent left = new BfsTraceEvent(BfsTracePoint.ENQUEUE_NEIGHBOR, 3, 4);
        BfsTraceEvent right = new BfsTraceEvent(BfsTracePoint.ENQUEUE_NEIGHBOR, 3, 4);

        assertEquals(left, right);
        assertEquals(left.hashCode(), right.hashCode());
    }

    @Test
    void shouldHaveReadableToString() {
        BfsTraceEvent event = new BfsTraceEvent(BfsTracePoint.DEQUEUE_VERTEX, 7, null);

        String text = event.toString();

        assertTrue(text.contains("DEQUEUE_VERTEX"));
        assertTrue(text.contains("currentVertex=7"));
        assertTrue(text.contains("neighborVertex=null"));
    }

    @Test
    void shouldNotBeEqualWhenDifferentFields() {
        BfsTraceEvent first = new BfsTraceEvent(BfsTracePoint.CHECK_NEIGHBOR, 1, 2);
        BfsTraceEvent second = new BfsTraceEvent(BfsTracePoint.CHECK_NEIGHBOR, 1, 3);

        assertNotEquals(first, second);
        assertNotEquals(null, first);
        assertNotEquals("other type", first);
    }
}
