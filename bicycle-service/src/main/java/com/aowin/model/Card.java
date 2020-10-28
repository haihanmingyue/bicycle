package com.aowin.model;

public class Card {
    private Integer card_id;
    private String card_code;
    private Integer card_type;
    private String name;
    private String idCard;
    private String sex;
    private String telPhone;
    private String mobile;
    private String address;
    private String work;
    private String zxbj;
    private double monthly_money;
    private double frozen_money;
    private double wallet_money;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public String getCard_code() {
        return card_code;
    }

    public void setCard_code(String card_code) {
        this.card_code = card_code;
    }

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getZxbj() {
        return zxbj;
    }

    public void setZxbj(String zxbj) {
        this.zxbj = zxbj;
    }

    public double getMonthly_money() {
        return monthly_money;
    }

    public void setMonthly_money(double monthly_money) {
        this.monthly_money = monthly_money;
    }

    public double getFrozen_money() {
        return frozen_money;
    }

    public void setFrozen_money(double frozen_money) {
        this.frozen_money = frozen_money;
    }

    public double getWallet_money() {
        return wallet_money;
    }

    public void setWallet_money(double wallet_money) {
        this.wallet_money = wallet_money;
    }
}
