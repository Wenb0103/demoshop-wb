package com.example.demodanei.entity;

import java.util.Date;

public class Order extends BaseEntity{

    private static final long serialVersionUID = -2949246051611079678L;

    private Integer oid;
    private Integer uid;
    private String name;
    private String phone;
    private String address;
    private Integer status;
    private Long price;
    private Date orderTime;
    private Date payTime;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("oid=").append(oid);
        sb.append(", uid=").append(uid);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", status=").append(status);
        sb.append(", price=").append(price);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", payTime=").append(payTime);
        sb.append('}');
        return sb.toString();
    }
}
