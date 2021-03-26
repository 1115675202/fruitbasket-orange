package com.fruitbasket.orange.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * 时间序列化格式
 *
 * @author LiuBing
 * @date 2020/12/2
 */
@Configuration
public class JacksonDateSerializerConfiguration {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	/**
	 * LocalDateTime LocalDate LocalTime
	 * 3 个 Converter 是接收时间时，String 转换 成 对应时间类型
	 * 用 lambda 会导致泛型丢失，springMVC 添加 Convert 的时候无法识别具体的类型
	 *
	 * @return
	 */
	@Bean
	public Converter<String, LocalDateTime> localDateTimeConvert() {
		return new Converter<String, LocalDateTime>() {
			@Override
			public LocalDateTime convert(String source) {
				return StringUtils.hasText(source) ? LocalDateTime.parse(source, DATE_TIME_FORMATTER) : null;
			}
		};
	}

	@Bean
	public Converter<String, LocalDate> localDateConvert() {
		return new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(String source) {
				return StringUtils.hasText(source) ? LocalDate.parse(source, DATE_FORMATTER) : null;
			}
		};
	}

	@Bean
	public Converter<String, LocalTime> localTimeConvert() {
		return new Converter<String, LocalTime>() {
			@Override
			public LocalTime convert(String source) {
				return StringUtils.hasText(source) ? LocalTime.parse(source, TIME_FORMATTER) : null;
			}
		};
	}

	/**
	 * 返回格式
	 *
	 * @return
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return builder -> builder
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER))
				.serializerByType(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER))
				.serializerByType(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER))
				.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER))
				.deserializerByType(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER))
				.deserializerByType(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));
	}
}
