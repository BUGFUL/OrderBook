package com.algoorders.orderbook.model;

import com.algoorders.orderbook.util.BuySellConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "trade")
@JacksonXmlRootElement(localName = "Order")
public class Order {

    private static final long serialVersionUID = 21L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @Column(name = "basecurrency")
    @JacksonXmlProperty
    private String baseCurrency;

    @Column(name = "quotecurrency")
    @JacksonXmlProperty
    private String quoteCurrency;

    @JacksonXmlProperty
    private BigDecimal notional;

    @JacksonXmlProperty
    private Float rate;

    @Column(name = "buysell")
    @Convert(converter = BuySellConverter.class)
    @JacksonXmlProperty
    private BuySell buySell;

    @Column(name = "enteredby")
    @JacksonXmlProperty
    private String enteredBy;

    @Column(name = "tradedate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JacksonXmlProperty
    private LocalDate tradeDate;

    public Order(){};

    //for testing purpose
    public Order(String baseCurrency, String quoteCurrency, BigDecimal notional, Float rate, BuySell buySell, String enteredBy, LocalDate tradeDate) {
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.notional = notional;
        this.rate = rate;
        this.buySell = buySell;
        this.enteredBy = enteredBy;
        this.tradeDate = tradeDate;
    }

    public Order(Long id, String baseCurrency, String quoteCurrency, BigDecimal notional, Float rate, BuySell buySell, String enteredBy, LocalDate tradeDate) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.notional = notional;
        this.rate = rate;
        this.buySell = buySell;
        this.enteredBy = enteredBy;
        this.tradeDate = tradeDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public BigDecimal getNotional() {
        return notional;
    }

    public Float getRate() {
        return rate;
    }

    public BuySell getBuySell() {
        return buySell;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public void setNotional(BigDecimal notional) {
        this.notional = notional;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public void setBuySell(BuySell buySell) {
        this.buySell = buySell;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (getBaseCurrency() != null ? !getBaseCurrency().equals(order.getBaseCurrency()) : order.getBaseCurrency() != null)
            return false;
        if (getQuoteCurrency() != null ? !getQuoteCurrency().equals(order.getQuoteCurrency()) : order.getQuoteCurrency() != null)
            return false;
        if (getNotional() != null ? !getNotional().equals(order.getNotional()) : order.getNotional() != null)
            return false;
        if (getRate() != null ? !getRate().equals(order.getRate()) : order.getRate() != null) return false;
        if (getBuySell() != order.getBuySell()) return false;
        if (getEnteredBy() != null ? !getEnteredBy().equals(order.getEnteredBy()) : order.getEnteredBy() != null)
            return false;
        return getTradeDate() != null ? getTradeDate().equals(order.getTradeDate()) : order.getTradeDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getBaseCurrency().hashCode();
        result = 31 * result + getQuoteCurrency().hashCode();
        result = 31 * result + getNotional().hashCode();
        result = 31 * result + getRate().hashCode();
        result = 31 * result + getBuySell().hashCode();
        result = 31 * result + getEnteredBy().hashCode();
        result = 31 * result + getTradeDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", quoteCurrency='" + quoteCurrency + '\'' +
                ", notional=" + notional +
                ", rate=" + rate +
                ", buySell=" + buySell +
                ", enteredBy='" + enteredBy + '\'' +
                ", tradeDate=" + tradeDate +
                '}';
    }
}
