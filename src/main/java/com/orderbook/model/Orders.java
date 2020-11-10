package com.orderbook.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement
public class Orders implements Serializable {

    private static final long serialVersionUID = 22L;

    @JacksonXmlProperty(localName = "Order")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Order> orders = new ArrayList<>();

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
