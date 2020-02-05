package io.github.aioves.community.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-02 19:55
 */
@Data
public class AccessTokenDTO implements Serializable {

    @JSONField(name = "client_id")
    private String clientId;

    @JSONField(name = "client_secret")
    private String clientSecret;
    private String code;

    @JSONField(name = "redirect_uri")
    private String redirectUri;
    private String state;

}
