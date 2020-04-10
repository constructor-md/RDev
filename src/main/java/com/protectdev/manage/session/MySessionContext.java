package com.protectdev.manage.session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 *
 * 实现通过sessionId操作session
 *
 */
public class MySessionContext {

        private static MySessionContext instance;

        private HashMap map;

        private MySessionContext() {
            map = new HashMap();
        }

        //单例模式
        public static MySessionContext getInstance() {
            if (instance == null) {
                instance = new MySessionContext();
            }
            return instance;
        }

        public synchronized void AddSession(HttpSession session) {
            if (session != null) {
                map.put(session.getId(), session);
            }
        }

        public synchronized void DelSession(HttpSession session) {
            if (session != null) {
                map.remove(session.getId());
            }
        }

        public synchronized HttpSession getSession(String session_id) {
            if (session_id == null) return null;
            return (HttpSession) map.get(session_id);
        }

}
