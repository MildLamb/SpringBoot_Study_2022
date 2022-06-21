package com.mildlamb.service;

public interface MsgService {
    public abstract String get(String tale);
    boolean checkTel(String tale,String code);
}
