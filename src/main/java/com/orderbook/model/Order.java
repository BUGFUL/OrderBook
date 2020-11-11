package com.orderbook.model;

import com.orderbook.util.BuySellConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.orderbook.util.StatusConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "trade")
@JacksonXmlRootElement(localName = "Order")
public class Order {

    private static final long serialVersionUID = 21L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @Column(name = "correlationid")
    @JacksonXmlProperty
    private UUID tradeId;

    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    @JacksonXmlProperty
    private Status status;

    @Column(name = "version")
    @JacksonXmlProperty
    private Long version;

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

    public Order() {};

    public Order(Long id, UUID tradeId, Status status, Long version, String baseCurrency, String quoteCurrency, BigDecimal notional, Float rate, BuySell buySell, String enteredBy, LocalDate tradeDate) {
        this.id = id;
        this.tradeId = tradeId;
        this.status = status;
        this.version = version;
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

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getTradeId() {
        return tradeId;
    }

    public void setTradeId(UUID tradeId) {
        this.tradeId = tradeId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public BigDecimal getNotional() {
        return notional;
    }

    public void setNotional(BigDecimal notional) {
        this.notional = notional;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public BuySell getBuySell() {
        return buySell;
    }

    public void setBuySell(BuySell buySell) {
        this.buySell = buySell;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
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
        if (getTradeId() != null ? !getTradeId().equals(order.getTradeId()) : order.getTradeId() != null) return false;
        if (getStatus() != order.getStatus()) return false;
        if (getVersion() != null ? !getVersion().equals(order.getVersion()) : order.getVersion() != null) return false;
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
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTradeId() != null ? getTradeId().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getBaseCurrency() != null ? getBaseCurrency().hashCode() : 0);
        result = 31 * result + (getQuoteCurrency() != null ? getQuoteCurrency().hashCode() : 0);
        result = 31 * result + (getNotional() != null ? getNotional().hashCode() : 0);
        result = 31 * result + (getRate() != null ? getRate().hashCode() : 0);
        result = 31 * result + (getBuySell() != null ? getBuySell().hashCode() : 0);
        result = 31 * result + (getEnteredBy() != null ? getEnteredBy().hashCode() : 0);
        result = 31 * result + (getTradeDate() != null ? getTradeDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "tradeId=" + tradeId +
                ", status=" + status +
                ", version=" + version +
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