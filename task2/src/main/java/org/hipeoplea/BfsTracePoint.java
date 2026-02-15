package org.hipeoplea;

public enum BfsTracePoint {
    START,
    INIT_QUEUE,
    DEQUEUE_VERTEX,
    CHECK_NEIGHBOR,
    ENQUEUE_NEIGHBOR,
    SKIP_VISITED_NEIGHBOR,
    FINISH
}
