package com.protectdev.manage.aspect;



import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.PermissionMapper;
import com.protectdev.manage.pojo.Permission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Aspect
@Component
public class PermissionAspect {

    @Pointcut("@annotation(com.protectdev.manage.annotation.PermissionCheck)")
    private void PermissionPointCut(){}


    @Around("PermissionPointCut() && @annotation(permissionCheck) && args(request)")
    public Object PermissionCheck(ProceedingJoinPoint pjp , PermissionCheck permissionCheck , HttpServletRequest request) throws Throwable {

        HttpSession session = request.getSession();
        System.out.println("进入了环绕通知");

        String permissionValue = permissionCheck.value();

        // todo 要搞清楚线程私有和共有，搞清楚线程的运行方式，面对并发问题


        if ((Boolean) session.getAttribute(permissionValue)){
            System.out.println("权限对应"+",放行");
            return pjp.proceed(new HttpServletRequest[]{request});
        }else {
            System.out.println("不放行");
            return null;
        }

    }

}
