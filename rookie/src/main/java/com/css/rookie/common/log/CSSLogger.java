package com.css.rookie.common.log;

public interface CSSLogger {

    void info(String o);

    void info(String o, Object... arg1);

    void info(String o, Throwable t);

    void debug(String o);

    void debug(String o, Object... arg1);

    void debug(String o, Throwable t);

    void warn(String o);

    void warn(String o, Object... arg1);

    void warn(String o, Throwable t);

    void error(String o);

    void error(String o, Throwable t);

    void error(String o, Object... arg1);
} 
