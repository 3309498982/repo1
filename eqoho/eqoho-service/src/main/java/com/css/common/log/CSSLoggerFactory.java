package com.css.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSSLoggerFactory {

    private CSSLoggerFactory() {

    }

    public static CSSLogger getLogger(Class clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return new CSSLoggerImpl(logger);
    }

    public static CSSLogger getLogger(String name) {
        Logger logger = LoggerFactory.getLogger(name);
        return new CSSLoggerImpl(logger);
    }
}
