package com.aowin.model;

import java.util.PrimitiveIterator;

public class BicycleInfo {

    private Integer bicycle_id;
    private String bicycle_code;
    private String status;
    private Integer pile_id;
    private String destory_date;
    private Integer user_id;
    private String operator_time;
    private Integer card_id;
    private String remark;

    public Integer getBicycle_id() {
        return bicycle_id;
    }

    public void setBicycle_id(Integer bicycle_id) {
        this.bicycle_id = bicycle_id;
    }

    public String getBicycle_code() {
        return bicycle_code;
    }

    public void setBicycle_code(String bicycle_code) {
        this.bicycle_code = bicycle_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPile_id() {
        return pile_id;
    }

    public void setPile_id(Integer pile_id) {
        this.pile_id = pile_id;
    }

    public String getDestory_date() {
        return destory_date;
    }

    public void setDestory_date(String destory_date) {
        this.destory_date = destory_date;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getOperator_time() {
        return operator_time;
    }

    public void setOperator_time(String operator_time) {
        this.operator_time = operator_time;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
