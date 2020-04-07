package com.protectdev.manage.aspect;



import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.PermissionMapper;
import com.protectdev.manage.pojo.Permission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class PermissionAspect {

    @Resource
    private PermissionMapper permissionMapper;

    @Pointcut("@annotation(com.protectdev.manage.annotation.PermissionCheck)")
    private void PermissionPointCut(){}


    @Around("PermissionPointCut() && @annotation(permissionCheck) && args(request)")
    public Object PermissionCheck(ProceedingJoinPoint pjp , PermissionCheck permissionCheck , HttpServletRequest request) throws Throwable {

        HttpSession session = request.getSession();
        System.out.println("进入了环绕通知");


        String userPermission = (String) session.getAttribute("permission");

        String permissionValue = permissionCheck.value();

        // todo 从request中取出permission，检查permission表中对应注解字符串的字段值是否为1:true
        Permission permission = permissionMapper.getPermission(userPermission);

        //记录权限参数，放入map方便取用
        Map<String,Boolean> map = new ConcurrentHashMap<>();

        // todo 每个线程进入这个方法都会创建一个独立的map吗？要搞清楚线程私有和共有，搞清楚线程的运行方式，面对并发问题

        map.put("userGet",permission.getUserGet());
        map.put("userPost",permission.getUserPost());
        map.put("userAdd",permission.getUserAdd());
        map.put("userDelete",permission.getUserDelete());

        map.put("deviceGet",permission.getDeviceGet());
        map.put("devicePost",permission.getDeviceGet());
        map.put("deviceAdd",permission.getDeviceAdd());
        map.put("deviceDelete",permission.getDeviceDelete());

        map.put("temGet",permission.getTemGet());
        map.put("temPost",permission.getTemPost());
        map.put("temAdd",permission.getTemAdd());
        map.put("temDelete",permission.getTemDelete());

        map.put("softGet",permission.getSoftGet());
        map.put("softPost",permission.getSoftPost());
        map.put("softAdd",permission.getSoftAdd());
        map.put("softDelete",permission.getSoftDelete());

        map.put("faultGet",permission.getFaultGet());
        map.put("faultPost",permission.getFaultPost());
        map.put("faultAdd",permission.getFaultAdd());
        map.put("faultDelete",permission.getFaultDelete());

        map.put("permissionGet",permission.getPermissionGet());
        map.put("permissionPost",permission.getPermissionPost());
        map.put("permissionAdd",permission.getPermissionAdd());
        map.put("permissionDelete",permission.getPermissionDelete());

        if (map.get(permissionValue)){
            System.out.println("权限对应"+",放行");
            return pjp.proceed(new HttpServletRequest[]{request});
        }else {
            return null;
        }

    }

}
