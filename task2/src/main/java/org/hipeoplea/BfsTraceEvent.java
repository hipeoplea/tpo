package org.hipeoplea;

import java.util.Objects;

public final class BfsTraceEvent {
    private final BfsTracePoint point;
    private final Integer currentVertex;
    private final Integer neighborVertex;

    public BfsTraceEvent(BfsTracePoint point, Integer currentVertex, Integer neighborVertex) {
        this.point = Objects.requireNonNull(point);
        this.currentVertex = currentVertex;
        this.neighborVertex = neighborVertex;
    }

    public BfsTracePoint getPoint() {
        return point;
    }

    public Integer getCurrentVertex() {
        return currentVertex;
    }

    public Integer getNeighborVertex() {
        return neighborVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BfsTraceEvent that)) {
            return false;
        }
        return point == that.point
                && Objects.equals(currentVertex, that.currentVertex)
                && Objects.equals(neighborVertex, that.neighborVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, currentVertex, neighborVertex);
    }

    @Override
    public String toString() {
        return "BfsTraceEvent{"
                + "point=" + point
                + ", currentVertex=" + currentVertex
                + ", neighborVertex=" + neighborVertex
                + '}';
    }
}
