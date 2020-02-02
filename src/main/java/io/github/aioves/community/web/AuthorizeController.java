package io.github.aioves.community.web;

import io.github.aioves.community.dto.AccessTokenDTO;
import io.github.aioves.community.dto.GithubUser;
import io.github.aioves.community.provider.GithubProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

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

    @GetMapping(path = "/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

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

        } catch (IOException e) {
            log.error("{}", "get github access token fail!");
        }


        return "index";
    }


}
