package io.github.aioves.community.web;

import io.github.aioves.community.mapper.QuestionMapper;
import io.github.aioves.community.mapper.UserMapper;
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

import javax.servlet.http.Cookie;
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

        User user = (User) request.getSession().getAttribute("usr");
        String info = "";
        if(null == user) {
            info = "用户未登录";
            model.addAttribute(Contents.ERROR, info);
            log.warn("{}", info);
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
            info = "标题不能为空";
            model.addAttribute(Contents.ERROR, info);
            log.warn("{}", info);
            return "publish";
        }

        if(title.length()>50) {
            info = "标题太长，不能超过25个字符";
            model.addAttribute(Contents.ERROR, info);
            log.warn("{}", info);
            return "publish";
        }
        log.info("{}", model);
        /*问题详情*/
        if(StringUtils.isEmpty(detail)) {
            info = "问题详情不能为空";
            model.addAttribute(Contents.ERROR, info);
            log.warn("{}", info);
            return "publish";
        }

        /*标签*/
        if(StringUtils.isEmpty(tags)) {
            info = "标签不能为空";
            model.addAttribute(Contents.ERROR, info);
            log.warn("{}", info);
            return "publish";
        }

        if(null==id) {//新增
            questionService.insert(question);
            info = "发布成功！";
            model.addAttribute(Contents.SUCCESS, info);
            log.info(info);
        } else  {//修改
            question.setId(id);
            question.setUpdateDate(Calendar.getInstance().getTime());
            questionService.update(question);
            info = "修改成功！";
            model.addAttribute(Contents.SUCCESS, info);
            log.info(info);
        }

        return "index";
    }
}
