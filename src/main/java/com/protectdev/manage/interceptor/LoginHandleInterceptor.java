package com.protectdev.manage.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //拦截器，主要实现静态资源的调用拦截，和表单提交的拦截
        //根据访问的URL，限制未登录只能访问登陆和注册页面
        //登录之后，另写切面，拦截对controller的访问，在方法层面做切面验证用户session或缓存中的用户信息来进行权限认证，决定是否执行
        //真正的权限设计还是和用户绑定，在数据库中标记清楚，映射在登陆后的用户对象中，或者是用户session中

        //如果在登陆的时候记录session并定时，那么用户登陆系统一定时间后就必须重新登陆，太死板了
        //希望实现用户不操作后一段时间，将session设置为过期
        //也就是说，登陆后，将用户信息保存到session，做一个定时器，每有一次操作（一个带有该session的访问记录）就重置定时器
        //定时器希望写在切面当中，可以是操作一发生就将操作时间记录下来，下次操作的时候判断时间间隔，如果到期则session失效
        //如果长久不操作，较长时间后session自动失效，保证内存
        //长时间要么不操作，要么一操作就过期，不操作只好保证内存即可，时间稍微超过计时器时间即可
        //如果访问静态资源不计时？页面请求不计时的话，页面请求结束后有可能突然没了权限，必须要操作一下子，所以要定义好操作这个行为
        //是表单提交算操作，还是所有资源交互都算操作，应该算后者
        //定义一个计算时间的切面
        //定义一个权限管理的切面
        //做一个切面监视访问行为
        //定时到期就把session失效
        //登陆认证
        //权限认证，有些重叠，可能需要写切面分开
        //注意，前端也需要权限信息，且某些页面只能由有权限的管理者才能访问，比如员工管理、设备信息提交管理页面
        //而cookie中只有sessionID，所以要另外传权限信息
        //cookie中是sessionID，根据这个玩意找到session对象
        //这里可以获得未发王dispatcherServlet的session


        // todo session写入访问时间，一段时间后失效，redis缓存应该与用户相关，验证码发送应该隔离在拦截器外？将验证码和sessionID键值对保存在redis中

        Object loginUser = request.getSession().getAttribute("username");

        if (loginUser == null){
            //未登录
            System.out.println("没有权限，请先登录");
            //不放行,重定向
            response.sendRedirect("http://localhost:8081/index.html");
            return false;
        }

        System.out.println("没拦截");
        return true;

    }

}
