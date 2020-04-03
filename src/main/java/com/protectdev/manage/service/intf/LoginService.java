package com.protectdev.manage.service.intf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public interface LoginService {

    String login(HttpServletRequest request,HttpServletResponse response);

}
