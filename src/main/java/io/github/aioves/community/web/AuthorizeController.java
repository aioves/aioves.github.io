package io.github.aioves.community.web;

import io.github.aioves.community.dto.AccessTokenDTO;
import io.github.aioves.community.dto.GithubUser;
import io.github.aioves.community.model.User;
import io.github.aioves.community.provider.GithubProvider;
import io.github.aioves.community.service.UserService;
import io.github.aioves.community.utils.Contents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-02 19:37
 */
@Slf4j
@Controller
public class AuthorizeController {

    @Value("${github.app.client_id}")
    private String clientId;

    @Value("${github.app.client_secret}")
    private String clientSecret;

    @Value("${github.app.redirect_uri}")
    private String redirectUri;

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           Model model) {

        //封装请求信息
        AccessTokenDTO tokenDTO = new AccessTokenDTO();
        tokenDTO.setCode(code);
        tokenDTO.setState(state);
        tokenDTO.setClientId(clientId);
        tokenDTO.setClientSecret(clientSecret);
        tokenDTO.setRedirectUri(redirectUri);

        try {
            //获取Github accessToken
            String accessToken = githubProvider.getAccessToken(tokenDTO);
            log.info("accessToken={}", accessToken);

            //获取Github 用户信息
            GithubUser githubUser = githubProvider.getGithubUser(accessToken);

            if(null != githubUser) {

                User user = new User();
                String token = UUID.randomUUID().toString();
                BeanUtils.copyProperties(githubUser, user);
                user.setToken(token);
                user.setAccountId(String.valueOf(githubUser.getId()));
                userService.saveOrUpdate(user);

                Cookie cookie = new Cookie(Contents.COOKIE_NAME, token);
                cookie.setPath(Contents.COOKIE_PATH);

                response.addCookie(cookie);
            } else {
                log.error("{}", "获取Github信息失败！");
            }


        } catch (IOException e) {
            log.error("{}", "get github access token fail! " + e.getMessage());
            model.addAttribute(Contents.ERROR, "获取GitHub账户信息失败！");
        }

        return "redirect:/";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) {

        /*清除Cookie*/
        Cookie[] cookies =  request.getCookies();
        if(null!=cookies) {
            for(int index=0, len=cookies.length; index<len; index++) {
                Cookie cookie = cookies[index];
                if(Contents.COOKIE_NAME.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath(Contents.COOKIE_PATH);
                    response.addCookie(cookie);

                    log.info("已清除cookie-{}", cookie.getValue());
                    break;
                }
            }
        }

        /*清除Session*/
        request.getSession().invalidate();
        log.info("已清除Session");

        model.addAttribute(Contents.SUCCESS, "退出登录成功");

        return "redirect:/index";
    }
}
