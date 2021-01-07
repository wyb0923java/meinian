package com.atguigu.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderSetting implements Serializable {
    private Integer id;

    private Date orderdate;

    private Integer number;

    private Integer reservations=0;

    private static final long serialVersionUID = 1L;

    public OrderSetting() {
    }

    public OrderSetting(Date orderdate, Integer number) {
        this.orderdate = orderdate;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getReservations() {
        return reservations;
    }

    public void setReservations(Integer reservations) {
        this.reservations = reservations;
    }
}