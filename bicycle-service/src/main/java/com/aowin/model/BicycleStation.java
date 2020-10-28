package com.aowin.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BicycleStation {
    private Integer station_id;
    @NotEmpty
    private String station_code;
    @NotEmpty
    private String station_name;
    @NotNull
    private Double longitude;//经度
    @NotNull
    private Double latitude;//维度
    @NotNull
    private Integer bicycle_pile_num;//车点 车桩数
    @NotEmpty
    private String address;
    private String person_in_charge;//负责人
    @NotEmpty
    private String build_time; //新建日期
    @NotEmpty
    private String run_time;//运行日期
    private Integer user_id;
    private String create_time;
    private String remark;

    public Integer getStation_id() {
        return station_id;
    }

    public void setStation_id(Integer station_id) {
        this.station_id = station_id;
    }

    public String getStation_code() {
        return station_code;
    }

    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getBicycle_pile_num() {
        return bicycle_pile_num;
    }

    public void setBicycle_pile_num(Integer bicycle_pile_num) {
        this.bicycle_pile_num = bicycle_pile_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerson_in_charge() {
        return person_in_charge;
    }

    public void setPerson_in_charge(String person_in_charge) {
        this.person_in_charge = person_in_charge;
    }

    public String getBuild_time() {
        return build_time;
    }

    public void setBuild_time(String build_time) {
        this.build_time = build_time;
    }

    public String getRun_time() {
        return run_time;
    }

    public void setRun_time(String run_time) {
        this.run_time = run_time;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString(){

        return address+" "+station_code+" "+station_name+" "+longitude+" "+latitude+" "+bicycle_pile_num+" "+person_in_charge;
    }
}
