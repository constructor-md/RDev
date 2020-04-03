package com.protectdev.manage.annotation;


import java.lang.annotation.*;


/**
 * 作用于方法域，运行时注解
 * 被这个注解标注的方法需要进行权限校验
 *
 * Java 不允许在同一个条目上重复出现相同类型的多个注解，多角色的时候不好处理
 * 基于多角色的权限管理，为每一个角色定义一个注解
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionCheck {

     String[] value();
}
