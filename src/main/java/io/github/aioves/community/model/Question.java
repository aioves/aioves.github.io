package io.github.aioves.community.model;

import lombok.Data;

import java.util.Date;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-03 18:01
 */
@Data
public class Question {

    private Long id;
    private String title;
    private String detail;
    private Long createdBy;
    private Date createDate;
    private Date updateDate;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tags;
}
