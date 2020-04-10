package com.protectdev.manage.session;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听session创建和销毁事件
 * session创建时将session加入
 *
 */
public class MySessionListener implements HttpSessionListener {

    //获得一个MySessionContext实例
    private   MySessionContext myc = MySessionContext.getInstance();

    //调用MySessionContext方法加入sessionId和对应session
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        myc.AddSession(httpSessionEvent.getSession());
    }

    //调用MySessionContext方法删除sessionId和对应session
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.DelSession(session);
    }

}
