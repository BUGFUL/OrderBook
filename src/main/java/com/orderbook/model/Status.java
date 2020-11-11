package com.orderbook.model;

import java.util.stream.Stream;

public enum Status {
    OPEN,
    AMEND,
    CANCELLED;

    public static Status getByName(String name) {
        return Stream.of(values())
                .filter(e -> e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
