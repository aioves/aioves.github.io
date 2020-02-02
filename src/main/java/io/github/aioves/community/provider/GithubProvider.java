package io.github.aioves.community.provider;

import com.alibaba.fastjson.JSONObject;
import io.github.aioves.community.dto.AccessTokenDTO;
import io.github.aioves.community.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-02 19:51
 */
@Slf4j
@Component
public class GithubProvider {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    @Value("${github.app.login.access_token.url}")
    private String accessTokenUrl;

    /**
     * @method: getAccessToken
     * @remarks:    获取Github的accesstoken
     * @author: <a href="mailto:aioves@foxmail.com>aioves</a>
     * @version: 1.0.0
     * @date: 2020/2/2 20:19
     * @params:  * @param accessTokenDTO
     * @return: java.lang.String
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String json = JSONObject.toJSONString(accessTokenDTO);
        log.info("{}", json);

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(accessTokenUrl)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String info = response.body().string();
            log.info("src return info: {}", info);
            info = info.substring(info.indexOf("=")+1, info.indexOf("&"));
            log.info("dest info: {}", info);
            return info;
        }

    }

    @Value("${github.app.user.url}")
    private String userUrl;

    /**
     * @method: getGithubUser
     * @remarks:    获取Github用户信息
     * @author: <a href="mailto:aioves@foxmail.com>aioves</a>
     * @version: 1.0.0
     * @date: 2020/2/2 20:43
     * @params:  * @param accessToken
     * @return: io.github.aioves.community.dto.GithubUser
     */
    public GithubUser getGithubUser(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = userUrl + "access_token="+accessToken;
        log.info("user user: {}", url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String info = response.body().string();
            log.info("github user info: {}", info);
            return JSONObject.parseObject(info, GithubUser.class);
        }
    }
}
