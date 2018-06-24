package com.bloom.annotation;
/**
 * 标注了此注解的方法，需要进行角色权限校验。
 * 只有value中的角色可以执行此操作。
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bloom.domain.gardener.meta.HighGradeRole;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleCheck {
	HighGradeRole[] value() default {} ;
	
}
