package io.github.aioves.community.dto;

import io.github.aioves.community.model.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-04 0:17
 */
@Data
public class QuestionDTO  implements Serializable {

    private int id;
    private String title;
    private String detail;
    private int createdBy;
    private Date createDate;
    private Date updateDate;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tags;

    private User user;
}
