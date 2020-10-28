package com.aowin.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Vender {

    private Integer vender_id;
    private String vender_code;
    @NotEmpty(message = "vender_type is not null")
    private String vender_type;

    @NotEmpty(message = "vender_name is not null")
    private String vender_name;
    @NotEmpty(message = "address is not null")
    private String address;
    @NotEmpty(message = "telPhone is not null")
    private String telPhone;
    @NotEmpty(message = "contacts is not null")
    private String contacts;
    @NotEmpty(message = "product_license is not null")
    private String product_license;
    @NotEmpty(message = "bussiness_registration_no is not null")
    private String bussiness_registration_no;
    private Double registered_capital;
    private Integer user_id;
    private String create_time;
    private String zxbj;

    private String remark;


    public Integer getVender_id() {
        return vender_id;
    }

    public void setVender_id(Integer vender_id) {
        this.vender_id = vender_id;
    }

    public String getVender_code() {
        return vender_code;
    }

    public void setVender_code(String vender_code) {
        this.vender_code = vender_code;
    }

    public String getVender_type() {
        return vender_type;
    }

    public void setVender_type(String vender_type) {
        this.vender_type = vender_type;
    }

    public String getTypeName() {
        if(getVender_type().equals("1")){
            return "生产商";
        }else {
            return "供应商";
        }

    }


    public String getVender_name() {
        return vender_name;
    }

    public void setVender_name(String vender_name) {
        this.vender_name = vender_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getProduct_license() {
        return product_license;
    }

    public void setProduct_license(String product_license) {
        this.product_license = product_license;
    }

    public String getBussiness_registration_no() {
        return bussiness_registration_no;
    }

    public void setBussiness_registration_no(String bussiness_registration_no) {
        this.bussiness_registration_no = bussiness_registration_no;
    }

    public Double getRegistered_capital() {
        return registered_capital;
    }

    public void setRegistered_capital(Double registered_capital) {
        this.registered_capital = registered_capital;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getZxbj() {
        return zxbj;
    }

    public void setZxbj(String zxbj) {
        this.zxbj = zxbj;
    }

    public String getZxbjName() {
        if(getZxbj().equals("0")){
            return "正常";
        }else {
            return "已注销";
        }
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
