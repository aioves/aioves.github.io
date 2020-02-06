package io.github.aioves.community.web;

import io.github.aioves.community.model.Question;
import io.github.aioves.community.model.User;
import io.github.aioves.community.service.QuestionService;
import io.github.aioves.community.utils.Contents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-03 16:34
 */
@Slf4j
@Controller
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(path = "{id}")
    public String edit(@PathVariable("id") int id,
                       Model model) {

       Question question = questionService.findQuestionById(id);
        model.addAttribute("question", question);
       return "publish";
    }

    @GetMapping
    public String goPublish(Model model) {
        model.addAttribute("question", new Question());
        return "publish";
    }

    @PostMapping
    public String doPublish(@RequestParam(name = "id") Integer id,
                            @RequestParam(name = "title") String title,
                            @RequestParam(name = "detail") String detail,
                            @RequestParam(name = "tags") String tags,
                            HttpServletRequest request,
                            Model model) {

        User user = (User) request.getSession().getAttribute(Contents.SESSION_NAME);
        if(null == user) {
            model.addAttribute(Contents.ERROR, Contents.USER_NOT_LOGIN);
            log.warn("{}", Contents.USER_NOT_LOGIN);
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDetail(detail);
        question.setTags(tags);
        question.setCreatedBy(user.getUserId());
        model.addAttribute("question", question);

        /*合法性校验*/
        /*标题*/
        if(StringUtils.isEmpty(title)) {
            model.addAttribute(Contents.ERROR, Contents.QUESTION_TITLE_IS_NULL);
            log.warn("{}", Contents.QUESTION_TITLE_IS_NULL);
            return "publish";
        }

        if(title.length()>Contents.QUESTION_TITLE_LENGTH) {
            model.addAttribute(Contents.ERROR, Contents.QUESTION_TITLE_TOO_LONG);
            log.warn("{}", Contents.QUESTION_TITLE_TOO_LONG);
            return "publish";
        }
        log.info("{}", model);
        /*问题详情*/
        if(StringUtils.isEmpty(detail)) {
            model.addAttribute(Contents.ERROR, Contents.QUESTION_DETAIL_IS_NULL);
            log.warn("{}", Contents.QUESTION_DETAIL_IS_NULL);
            return "publish";
        }

        /*标签*/
        if(StringUtils.isEmpty(tags)) {
            model.addAttribute(Contents.ERROR, Contents.QUESTION_TAGS_IS_NULL);
            log.warn("{}", Contents.QUESTION_TAGS_IS_NULL);
            return "publish";
        }

        if(null==id) {//新增
            questionService.insert(question);
            model.addAttribute(Contents.SUCCESS, Contents.QUESTION_SEND_SUCCESS);
            log.info(Contents.QUESTION_SEND_SUCCESS);
        } else  {//修改
            question.setId(id);
            question.setUpdateDate(Calendar.getInstance().getTime());
            questionService.update(question);
            model.addAttribute(Contents.SUCCESS, Contents.QUESTION_UPDATE_SUCCESS);
            log.info(Contents.QUESTION_UPDATE_SUCCESS);
        }

        return "index";
    }
}
