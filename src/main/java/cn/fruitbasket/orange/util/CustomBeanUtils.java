package cn.fruitbasket.orange.util;

import cn.hutool.core.bean.copier.CopyOptions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * JavaBean 拷贝工具类
 *
 * @author LiuBing
 * @date 2020/12/18
 */
public class CustomBeanUtils {

	/**
	 * hutool 拷贝对象时忽略 null 值
	 */
	public static final CopyOptions IGNORE_NULL_COPY_OPTION = CopyOptions.create().setIgnoreNullValue(true);

	/**
	 * 创建同样数量的 targetClass 对象，并将 sourceList 拷贝到 targetClass 中
	 * 由于要实例化 targetClass，需要 targetClass 提供空构造函数
	 *
	 * @param sourceList  源数据列表
	 * @param targetClass 目标类型
	 * @param <S> 源类型
	 * @param <T> 目标类型
	 * @return targetClass 操作流
	 */
	public static <S, T> Stream<T> instantiateCopy(List<S> sourceList, Class<T> targetClass) {
		if (Objects.requireNonNull(sourceList).isEmpty()) {
			return Stream.empty();
		}

		return sourceList.stream()
				.map(source -> {
					T target = org.springframework.beans.BeanUtils.instantiateClass(targetClass);
					org.springframework.beans.BeanUtils.copyProperties(source, target);
					return target;
				});
	}

	private CustomBeanUtils() {
	}
}
