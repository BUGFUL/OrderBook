package com.orderbook.model;

import java.util.stream.Stream;

public enum BuySell {
    BUY, SELL;

    public static BuySell getByName(String name) {
        return Stream.of(values())
                .filter(e -> e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
