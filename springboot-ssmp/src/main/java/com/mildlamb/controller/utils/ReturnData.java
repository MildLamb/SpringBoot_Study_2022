package com.mildlamb.controller.utils;

import lombok.Data;

@Data
public class ReturnData {
    private Boolean flag;
    private Object object;
    private String respMsg;

    public ReturnData() {
    }


    public ReturnData(Boolean flag) {
        this.flag = flag;
    }

    public ReturnData(Boolean flag, Object object) {
        this.flag = flag;
        this.object = object;
    }

    public ReturnData(String respMsg){
        this.respMsg = respMsg;
    }

    public ReturnData(String respMsg,Boolean flag){
        this.respMsg = respMsg;
        this.flag = flag;
    }

}
