package org.hipeoplea;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public final class UndirectedGraph {
    private final Map<Integer, LinkedHashSet<Integer>> adjacency = new LinkedHashMap<>();

    public void addVertex(int v) {
        adjacency.computeIfAbsent(v, key -> new LinkedHashSet<>());
    }

    public UndirectedGraph addEdge(int a, int b) {
        addVertex(a);
        addVertex(b);
        adjacency.get(a).add(b);
        adjacency.get(b).add(a);
        return this;
    }

    public Map<Integer, List<Integer>> toAdjacencyList() {
        Map<Integer, List<Integer>> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, LinkedHashSet<Integer>> entry : adjacency.entrySet()) {
            result.put(entry.getKey(), List.copyOf(entry.getValue()));
        }
        return Collections.unmodifiableMap(result);
    }
}
