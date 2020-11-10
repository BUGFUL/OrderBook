package com.algoorders.orderbook.util;

import com.algoorders.orderbook.model.BuySell;

import javax.persistence.AttributeConverter;

public class BuySellConverter implements AttributeConverter<BuySell, String> {

    @Override
    public String convertToDatabaseColumn(BuySell attribute) {
        return attribute.name();
    }

    @Override
    public BuySell convertToEntityAttribute(String dbData) {
        return BuySell.getByName(dbData);
    }
}
