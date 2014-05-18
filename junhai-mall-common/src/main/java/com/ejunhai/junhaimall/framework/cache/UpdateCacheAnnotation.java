package com.ejunhai.junhaimall.framework.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 更新缓存注解
 *
 * @author 罗正加
 * @date 2011-7-20
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UpdateCacheAnnotation {
	/**
	 * 命名空间
	 */
	String namespace() default AnnotationConstants.NAMESPACE;

	/**
	 * 缓存服务器客户端名称
	 */
	CacheClientEnum clientName();

	/**
	 *	緩存key
	 */
	String assignCacheKey();
	
	/**
	 * 缓存服务器上缓存的时间(大于0时生效)
	 */
	int remoteExpire() default 0;
}
