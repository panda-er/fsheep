package com.minip.tx.config;

import java.util.List;

public class RedisProperties {
    List<String> nodes;

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}
