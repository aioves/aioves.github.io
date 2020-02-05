package io.github.aioves.community.interceptor;

import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.User;
import io.github.aioves.community.model.UserExample;
import io.github.aioves.community.utils.Contents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-04 15:28
 */
@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if(null!=cookies) {
            for(int index = 0, len = cookies.length; index<len; index++) {
                Cookie cookie = cookies[index];
                if(Contents.COOKIE_NAME.equals(cookie.getName())) {
                    String token = cookie.getValue();
                    log.info("token={}", token);
                    UserExample example = new UserExample();
                    example.createCriteria().andTokenEqualTo(token);
                    List<User> list = userMapper.selectByExample(example);
                    if(null!=list && list.size()!=0) {
                        request.getSession().setAttribute(Contents.SESSION_NAME, list.get(0));
                    }

                    break;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
