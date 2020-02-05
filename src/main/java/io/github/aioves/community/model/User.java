package io.github.aioves.community.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-02 21:10
 */
@Data
public class User implements Serializable {

    private Long userId;
    private String login;
    private String name;
    private String bio;

    @JSONField(name = "avatar_url")
    private String avatarUrl;

    private String accountId;
    private String token;
    private Date createDate;
    private Date updateDate;
}
