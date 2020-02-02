package io.github.aioves.community.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-02 20:35
 */
@Data
public class GithubUser {

    private Long id;
    private String login;
    private String name;
    private String bio;

    @JSONField(name = "avatar_url")
    private String avatarUrl;
}
