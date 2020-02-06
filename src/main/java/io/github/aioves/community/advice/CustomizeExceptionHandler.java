package io.github.aioves.community.advice;

import io.github.aioves.community.exception.CustomizeException;
import io.github.aioves.community.utils.Contents;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-06 17:09
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handler(HttpServletRequest request, Throwable e, Model model){

        if(e instanceof CustomizeException) {
            model.addAttribute(Contents.MESSAGE, e.getMessage());
        } else {
            model.addAttribute(Contents.MESSAGE, Contents.GREETING);
        }


        return new ModelAndView("error");
    }

}
