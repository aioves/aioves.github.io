package io.github.aioves.community.web;

import io.github.aioves.community.dto.AccessTokenDTO;
import io.github.aioves.community.dto.GithubUser;
import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.User;
import io.github.aioves.community.provider.GithubProvider;
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
    private UserMapper userMapper;

    @GetMapping(path = "/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {

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

                User user = userMapper.findUserByAccountId(githubUser.getId());
                String token = UUID.randomUUID().toString();
                if(null != user) {
                    user.setToken(token);
                    userMapper.updateTokenByUserId(user.getUserId(), token);
                    log.info("user={}", user);
                } else {
                    user = new User();
                    BeanUtils.copyProperties(githubUser, user);
                    user.setAccountId(String.valueOf(githubUser.getId()));
                    user.setToken(token);
                    log.info("user={}", user);
                    userMapper.insert(user);
                }

                response.addCookie(new Cookie("token", token));
            } else {
                log.error("{}", "获取Github信息失败！");
            }


        } catch (IOException e) {
            log.error("{}", "get github access token fail! " + e.getMessage());
        }

        return "redirect:index";
    }


}
