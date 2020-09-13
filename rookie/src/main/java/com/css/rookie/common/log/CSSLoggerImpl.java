package com.css.rookie.common.log;

import org.slf4j.Logger;

public class CSSLoggerImpl implements CSSLogger {

	Logger logger;

	public CSSLoggerImpl(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void info(String o) {
		logger.info(o);
	}

	@Override
	public void info(String o, Throwable t) {
		logger.info(o, t);
	}

	@Override
	public void debug(String o) {
		logger.debug(o);
	}

	@Override
	public void debug(String o, Throwable t) {
		logger.debug(o, t);
	}

	@Override
	public void warn(String o) {
		logger.warn(o);
	}

	@Override
	public void warn(String o, Throwable t) {
		logger.warn(o, t);
	}

	@Override
	public void error(String o) {
		logger.error(o);
	}

	@Override
	public void error(String o, Throwable t) {
		logger.error(o, t);
	}
	
	@Override
	public void info(String o, Object... arg1) {
		logger.info(o, arg1);
	}

	@Override
	public void debug(String o, Object... arg1) {
		logger.debug(o, arg1);
	}

	@Override
	public void warn(String o, Object... arg1) { logger.warn(o, arg1); }

	@Override
	public void error(String o, Object... arg1) {
		logger.error(o, arg1);
	}

}
