package com.mildlamb.controller.utils;

import lombok.Data;

@Data
public class ReturnData {
    private Boolean flag;
    private Object object;

    public ReturnData() {
    }


    public ReturnData(Boolean flag) {
        this.flag = flag;
    }

    public ReturnData(Boolean flag, Object object) {
        this.flag = flag;
        this.object = object;
    }
}
