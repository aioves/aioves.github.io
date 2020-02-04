package io.github.aioves.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Title:  分页
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-04 1:18
 */
@Data
public class PaginationDTO {

    /*页数从1开始*/
    private Integer firstPage=1;
    /*是否显示上一页*/
    private boolean showPrevious;
    /*是否显示第一页*/
    private boolean showFirstPage;
    /*是否显示下一页*/
    private boolean showNext;
    /*是否显示最后一页*/
    private boolean showEndPage;
    /*当前页码*/
    private Integer page;
    /*总页数*/
    private Integer totalPage;
    /*分页大小*/
    private Integer pageSize;

    private List<Integer> pages = new ArrayList<>();
    private List<QuestionDTO> questions;

    public void setPagination(Integer totalPage, Integer page, Integer size){
        this.page = page;
        this.pageSize = size;
        this.totalPage = totalPage;

        pages.add(page);
        for(int index=1; index<=3; index++) {
            if(page-index>0) {
                pages.add(page-index);
            }

            if(page+index<=totalPage) {
                pages.add(page+index);
            }
        }

        Collections.sort(pages);

        /*是否展示上一页*/
        if(page==1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        /*是否展示下一页*/
        if(totalPage == page) {
            showNext = false;
        } else {
            showNext = true;
        }

        /*是否展示第一页*/
        if(pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        /*是否展示最后一页*/
        if(pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
