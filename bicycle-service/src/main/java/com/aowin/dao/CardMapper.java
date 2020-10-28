package com.aowin.dao;

import com.aowin.model.BicyclePile;
import com.aowin.model.Card;

import java.util.List;
import java.util.Map;

public interface CardMapper {

    public Card getUserCardMsg(String card_code);

    public int rentUpdateCard(Map<String,Object> map);
}
