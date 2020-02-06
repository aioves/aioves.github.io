package io.github.aioves.community.utils;

/**
 * @Title:      通用变量
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-05 14:30
 */
public class Contents {

    /*通用*/
    /*Cookie名称*/
    public final static String COOKIE_NAME = "token";

    /*Cookie存放路径*/
    public final static String COOKIE_PATH = "/";

    /*Session名称*/
    public final static String SESSION_NAME = "usr";

    /*成功*/
    public final static String SUCCESS = "success";

    /*失败*/
    public final static String ERROR = "error";

    /*消息*/
    public final static String MESSAGE = "message";

    public final static String GREETING = "姿势错了！ 换个姿势再来一次！";

    public final static String SERVER_ERROR = "人生难免会犯错，人家也会犯错。喝杯茶，歇一歇吧！";

    /*用户相关*/
    public final static String USER_NOT_LOGIN = "用户未登录";

    /*问题相关*/
    public final static Integer QUESTION_TITLE_LENGTH = 25;
    public final static String QUESTION_TITLE_IS_NULL = "问题标题不能为空";
    public final static String QUESTION_TITLE_TOO_LONG = "问题标题已超出"+QUESTION_TITLE_LENGTH+"个字符";

    public final static String QUESTION_DETAIL_IS_NULL = "问题详情不能为空";

    public final static String QUESTION_TAGS_IS_NULL = "问题标签不能为空";

    public final static String QUESTION_SEND_SUCCESS = "问题发布成功";

    public final static String QUESTION_UPDATE_SUCCESS = "问题重新发布成功";

    public final static String QUESTION_ERROR = "问题发布失败";
}
