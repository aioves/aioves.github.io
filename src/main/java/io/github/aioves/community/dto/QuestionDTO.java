package io.github.aioves.community.dto;

import io.github.aioves.community.model.User;
import lombok.Data;

import java.util.Date;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-04 0:17
 */
@Data
public class QuestionDTO {

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

    private User user;
}
