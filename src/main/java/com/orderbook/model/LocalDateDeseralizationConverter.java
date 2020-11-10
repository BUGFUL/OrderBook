package com.orderbook.model;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LocalDateDeseralizationConverter extends StdConverter<String, LocalDate> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    @Override
    public LocalDate convert(String value) {
        return LocalDate.parse(value, DATE_FORMATTER);
    }
}
