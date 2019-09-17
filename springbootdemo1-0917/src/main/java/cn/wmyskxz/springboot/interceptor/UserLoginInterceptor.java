package cn.wmyskxz.springboot.interceptor;

import cn.wmyskxz.springboot.exceptions.BusinessException;
import cn.wmyskxz.springboot.pojo.UserInfo;
import cn.wmyskxz.springboot.pojo.UserToken;
import cn.wmyskxz.springboot.pojo.User;
import cn.wmyskxz.springboot.service.UserService;
import cn.wmyskxz.springboot.util.DateTime;
import cn.wmyskxz.springboot.util.SysConstent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String url = request.getRequestURI();
        if (url == null) {
            return false;
        }
        if (url.indexOf("/login") >= 0 || url.indexOf("/logout") >= 0 || url.indexOf("api/vercode") >= 0) {
            long startTime = System.currentTimeMillis();//方法执行开始时间
            request.setAttribute("startTime", startTime);
            return true;
        } /*else {
            // 判断cookie是否有登录信息
            Cookie[] cookies = request.getCookies();
            boolean isLogin = false;
            for (Cookie c : cookies) {
                if ("loginInfo".equals(c.getName())) {
                    String v = c.getValue();
                    if (v != null && !v.equals("")) {
                        isLogin = true;
                    }
                }
            }

            // 有，登录通过
            // 没有，登录验证，设置登录信息，设置cookie有效时间
            if (isLogin) {
                System.out.println("用户已登录");
                return true;
            } else {
                System.out.println("用户未登录");
                System.out.println("用户信息验证通过");
                return false;
            }
        }*/
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if (userInfo != null && userInfo.getPassword() != null) {
            return true;
        } else {
            request.setAttribute("info", "请先登录");
            response.sendRedirect("/login.html");
            return true;
            //throw new BusinessException("请先登录");
        }


      /*  String token = request.getHeader("token");
        User user = null;
        if (token != null) {
            UserToken userToken = userService.getTimeByToken(token);
            if (DateTime.now().after(userToken.getUpdatetime())) {
                throw new Exception("会话超时,请重新登录!");
            } else {
                userService.updateTimeByToken(token);
                user = userService.getUserByToken(token);  //验证用户是否登录
            }
            request.setAttribute(SysConstent.USER_LOGIN, user);

        } else {
            throw new BusinessException("请先登录");
        }
        long startTime = System.currentTimeMillis();//方法执行开始时间
        request.setAttribute("startTime", startTime);*/
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}


