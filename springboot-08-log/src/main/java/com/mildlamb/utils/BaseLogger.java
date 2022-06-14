package com.mildlamb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BaseLogger {
    static Logger getLogger(Class clazz){
        return LoggerFactory.getLogger(clazz);
    }
}
