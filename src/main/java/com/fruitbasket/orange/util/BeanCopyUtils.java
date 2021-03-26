package com.fruitbasket.orange.util;

import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Stream;

/**
 * JavaBean 拷贝工具类
 *
 * @author LiuBing
 * @date 2020/12/18
 */
public class BeanCopyUtils {

	/**
	 * 创建同样数量的 targetClass 对象，并将 sourceList 拷贝到 targetClass 中
	 * 由于要实例化 targetClass，需要提 targetClass 供空构造函数
	 *
	 * @param sourceList  源数据列表
	 * @param targetClass 目标类型
	 * @param <S>
	 * @param <T>
	 * @return targetClass 操作流
	 */
	public static <S, T> Stream<T> instantiateCopy(List<S> sourceList, Class<T> targetClass) {
		if (Objects.requireNonNull(sourceList).isEmpty()) {
			return Stream.empty();
		}

		return sourceList.stream()
				.map(source -> {
					T target = BeanUtils.instantiateClass(targetClass);
					BeanUtils.copyProperties(source, target);
					return target;
				});
	}

	private BeanCopyUtils() {
	}
}
