package com.fruitbasket.orange.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.function.Supplier;

/**
 * 日志虽然会按级别输出，但是参数可能会进行字符串拼接运算，浪费开销
 * 解决办法：打印前判断日志输出级别是否打开，避免没必要的拼接字符串操作
 *
 * @author LiuBing
 * @date 2020/11/6
 */
public class LoggerMediator {

	private Logger logger;

	public static LoggerMediator getLogger(Class<?> clazz) {
		return new LoggerMediator(LoggerFactory.getLogger(clazz));
	}

	public static LoggerMediator getLogger(String name) {
		return new LoggerMediator(LoggerFactory.getLogger(name));
	}

	boolean isTraceEnabled() {
		return this.logger.isTraceEnabled();
	}

	public void trace(Supplier<String> contentSupplier) {
		if (this.logger.isTraceEnabled()) {
			this.logger.trace(contentSupplier.get());
		}
	}

	public void trace(Supplier<String> contentSupplier, Object var2) {
		if (this.logger.isTraceEnabled()) {
			this.logger.trace(contentSupplier.get(), var2);
		}
	}

	public void trace(Supplier<String> contentSupplier, Object var2, Object var3) {
		if (this.logger.isTraceEnabled()) {
			this.logger.trace(contentSupplier.get(), var2, var3);
		}
	}

	public void trace(Supplier<String> contentSupplier, Object... var2) {
		if (this.logger.isTraceEnabled()) {
			this.logger.trace(contentSupplier.get(), var2);
		}
	}

	public void trace(Supplier<String> contentSupplier, Throwable var2) {
		if (this.logger.isTraceEnabled()) {
			this.logger.trace(contentSupplier.get(), var2);
		}
	}

	boolean isTraceEnabled(Marker var1) {
		return this.logger.isTraceEnabled(var1);
	}


	public void trace(Marker var1, Supplier<String> contentSupplier) {
		if (this.logger.isTraceEnabled(var1)) {
			this.logger.trace(var1, contentSupplier.get());
		}
	}

	public void trace(Marker var1, Supplier<String> contentSupplier, Object var3) {
		if (this.logger.isTraceEnabled(var1)) {
			this.logger.trace(var1, contentSupplier.get(), var3);
		}
	}

	public void trace(Marker var1, Supplier<String> contentSupplier, Object var3, Object var4) {
		if (this.logger.isTraceEnabled(var1)) {
			this.logger.trace(var1, contentSupplier.get(), var3, var4);
		}
	}

	public void trace(Marker var1, Supplier<String> contentSupplier, Object... var3) {
		if (this.logger.isTraceEnabled(var1)) {
			this.logger.trace(var1, contentSupplier.get(), var3);
		}
	}

	public void trace(Marker var1, Supplier<String> contentSupplier, Throwable var3) {
		if (this.logger.isTraceEnabled(var1)) {
			this.logger.trace(var1, contentSupplier.get(), var3);
		}
	}

	public boolean isDebugEnabled() {
		return this.logger.isDebugEnabled();
	}

	public void debug(Supplier<String> contentSupplier) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(contentSupplier.get());
		}
	}

	public void debug(Supplier<String> contentSupplier, Object var2) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(contentSupplier.get(), var2);
		}
	}

	public void debug(Supplier<String> contentSupplier, Object var2, Object var3) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(contentSupplier.get(), var2, var3);
		}
	}

	public void debug(Supplier<String> contentSupplier, Object... var2) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(contentSupplier.get(), var2);
		}
	}

	public void debug(Supplier<String> contentSupplier, Throwable var2) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(contentSupplier.get(), var2);
		}
	}

	boolean isDebugEnabled(Marker var1) {
		return this.logger.isDebugEnabled(var1);
	}

	public void debug(Marker var1, Supplier<String> contentSupplier) {
		if (this.logger.isDebugEnabled(var1)) {
			this.logger.debug(var1, contentSupplier.get());
		}
	}

	public void debug(Marker var1, Supplier<String> contentSupplier, Object var3) {
		if (this.logger.isDebugEnabled(var1)) {
			this.logger.debug(var1, contentSupplier.get(), var3);
		}
	}

	public void debug(Marker var1, Supplier<String> contentSupplier, Object var3, Object var4) {
		if (this.logger.isDebugEnabled(var1)) {
			this.logger.debug(var1, contentSupplier.get(), var3, var4);
		}
	}

	public void debug(Marker var1, Supplier<String> contentSupplier, Object... var3) {
		if (this.logger.isDebugEnabled(var1)) {
			this.logger.debug(var1, contentSupplier.get(), var3);
		}
	}

	public void debug(Marker var1, Supplier<String> contentSupplier, Throwable var3) {
		if (this.logger.isDebugEnabled(var1)) {
			this.logger.debug(var1, contentSupplier.get(), var3);
		}
	}

	boolean isInfoEnabled() {
		return this.logger.isInfoEnabled();
	}

	public void info(Supplier<String> contentSupplier) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(contentSupplier.get());
		}
	}

	public void info(Supplier<String> contentSupplier, Object var2) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(contentSupplier.get(), var2);
		}
	}

	public void info(Supplier<String> contentSupplier, Object var2, Object var3) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(contentSupplier.get(), var2, var3);
		}
	}

	public void info(Supplier<String> contentSupplier, Object... var2) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(contentSupplier.get(), var2);
		}
	}

	public void info(Supplier<String> contentSupplier, Throwable var2) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(contentSupplier.get(), var2);
		}
	}

	public boolean isInfoEnabled(Marker var1) {
		return this.logger.isInfoEnabled(var1);
	}

	public void info(Marker var1, Supplier<String> contentSupplier) {
		if (this.logger.isInfoEnabled(var1)) {
			this.logger.info(var1, contentSupplier.get());
		}
	}

	public void info(Marker var1, Supplier<String> contentSupplier, Object var3) {
		if (this.logger.isInfoEnabled(var1)) {
			this.logger.info(var1, contentSupplier.get(), var3);
		}
	}

	public void info(Marker var1, Supplier<String> contentSupplier, Object var3, Object var4) {

		if (this.logger.isInfoEnabled(var1)) {
			this.logger.info(var1, contentSupplier.get(), var3, var4);
		}
	}

	public void info(Marker var1, Supplier<String> contentSupplier, Object... var3) {
		if (this.logger.isInfoEnabled(var1)) {
			this.logger.info(var1, contentSupplier.get(), var3);
		}
	}

	public void info(Marker var1, Supplier<String> contentSupplier, Throwable var3) {
		if (this.logger.isInfoEnabled(var1)) {
			this.logger.info(var1, contentSupplier.get(), var3);
		}
	}

	public boolean isWarnEnabled() {
		return this.logger.isWarnEnabled();
	}

	public void warn(Supplier<String> contentSupplier) {
		if (this.logger.isWarnEnabled()) {
			this.logger.warn(contentSupplier.get());
		}
	}

	public void warn(Supplier<String> contentSupplier, Object var2) {
		if (this.logger.isWarnEnabled()) {
			this.logger.warn(contentSupplier.get(), var2);
		}
	}

	public void warn(Supplier<String> contentSupplier, Object... var2) {
		if (this.logger.isWarnEnabled()) {
			this.logger.warn(contentSupplier.get(), var2);
		}
	}

	public void warn(Supplier<String> contentSupplier, Object var2, Object var3) {

		if (this.logger.isWarnEnabled()) {
			this.logger.warn(contentSupplier.get(), var2, var3);
		}
	}

	public void warn(Supplier<String> contentSupplier, Throwable var2) {
		if (this.logger.isWarnEnabled()) {
			this.logger.warn(contentSupplier.get(), var2);
		}
	}

	public boolean isWarnEnabled(Marker var1) {
		return this.logger.isWarnEnabled(var1);
	}

	public void warn(Marker var1, Supplier<String> contentSupplier) {
		if (this.logger.isWarnEnabled(var1)) {
			this.logger.warn(var1, contentSupplier.get());
		}
	}

	public void warn(Marker var1, Supplier<String> contentSupplier, Object var3) {
		if (this.logger.isWarnEnabled(var1)) {
			this.logger.warn(var1, contentSupplier.get(), var3);
		}
	}

	public void warn(Marker var1, Supplier<String> contentSupplier, Object var3, Object var4) {
		if (this.logger.isWarnEnabled(var1)) {
			this.logger.warn(var1, contentSupplier.get(), var3, var4);
		}
	}

	public void warn(Marker var1, Supplier<String> contentSupplier, Object... var3) {

		if (this.logger.isWarnEnabled(var1)) {
			this.logger.warn(var1, contentSupplier.get(), var3);
		}
	}

	public void warn(Marker var1, Supplier<String> contentSupplier, Throwable var3) {
		if (this.logger.isWarnEnabled(var1)) {
			this.logger.warn(var1, contentSupplier.get(), var3);
		}
	}

	public boolean isErrorEnabled() {
		return this.isErrorEnabled();
	}

	public void error(Supplier<String> contentSupplier) {
		if (this.logger.isErrorEnabled()) {
			this.logger.error(contentSupplier.get());
		}
	}

	public void error(Supplier<String> contentSupplier, Object var2) {
		if (this.logger.isErrorEnabled()) {
			this.logger.error(contentSupplier.get(), var2);
		}
	}

	public void error(Supplier<String> contentSupplier, Object var2, Object var3) {
		if (this.logger.isErrorEnabled()) {
			this.logger.error(contentSupplier.get(), var2, var3);
		}
	}

	public void error(Supplier<String> contentSupplier, Object... var2) {
		if (this.logger.isErrorEnabled()) {
			this.logger.error(contentSupplier.get(), var2);
		}
	}

	public void error(Supplier<String> contentSupplier, Throwable var2) {
		if (this.logger.isErrorEnabled()) {
			this.logger.error(contentSupplier.get(), var2);
		}
	}

	public boolean isErrorEnabled(Marker var1) {
		return this.logger.isErrorEnabled(var1);
	}

	public void error(Marker var1, Supplier<String> contentSupplier) {
		if (this.logger.isErrorEnabled(var1)) {
			this.logger.error(var1, contentSupplier.get());
		}
	}

	public void error(Marker var1, Supplier<String> contentSupplier, Object var3) {
		if (this.logger.isErrorEnabled(var1)) {
			this.logger.error(var1, contentSupplier.get(), var3);
		}
	}

	public void error(Marker var1, Supplier<String> contentSupplier, Object var3, Object var4) {
		if (this.logger.isErrorEnabled(var1)) {
			this.logger.error(var1, contentSupplier.get(), var3, var4);
		}
	}

	public void error(Marker var1, Supplier<String> contentSupplier, Object... var3) {
		if (this.logger.isErrorEnabled(var1)) {
			this.logger.error(var1, contentSupplier.get(), var3);
		}
	}

	public void error(Marker var1, Supplier<String> contentSupplier, Throwable var3) {
		if (this.logger.isErrorEnabled(var1)) {
			this.logger.error(var1, contentSupplier.get(), var3);
		}
	}

	public LoggerMediator(Logger logger) {
		this.logger = logger;
	}
}
