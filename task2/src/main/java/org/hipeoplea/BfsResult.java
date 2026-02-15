package org.hipeoplea;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record BfsResult(List<Integer> visitOrder, List<BfsTraceEvent> trace) {
    public BfsResult(List<Integer> visitOrder, List<BfsTraceEvent> trace) {
        this.visitOrder = Collections.unmodifiableList(Objects.requireNonNull(visitOrder));
        this.trace = Collections.unmodifiableList(Objects.requireNonNull(trace));
    }
}
