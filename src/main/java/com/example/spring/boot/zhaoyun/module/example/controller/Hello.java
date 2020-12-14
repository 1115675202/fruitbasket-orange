package com.example.spring.boot.zhaoyun.module.example.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author LiuBing
 * @date 2020/12/3
 */
public class Hello {

	private LocalDateTime localDateTime = LocalDateTime.now();

	private LocalDate localDate = LocalDate.now();

	private LocalTime localTime = LocalTime.now();

	private String str = "中文";

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public LocalTime getLocalTime() {
		return localTime;
	}

	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}
}
