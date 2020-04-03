package com.protectdev.manage.aspect;



import com.protectdev.manage.annotation.PermissionCheck;
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
        boolean check = false;
        for (String s : permissionCheck.value()){

            if (s.equals(session.getAttribute("permission"))){
                //权限对应，则放行

                check = true;

                break;
            }
            System.out.println(s);
        }

        if (check){
            System.out.println(session.getAttribute("permission")+"和" + permissionCheck.value()+"权限对应"+",放行");

            return pjp.proceed(new HttpServletRequest[]{request});
            //System.out.println("方法放行");

        }else{
            System.out.println("权限不足");
            return null;
        }

    }

}
