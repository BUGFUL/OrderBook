package com.orderbook.util;

import com.orderbook.model.Status;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status attribute) {
        return attribute.name();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        return Status.getByName(dbData);
    }
}