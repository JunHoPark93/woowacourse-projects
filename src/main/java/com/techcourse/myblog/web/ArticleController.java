package com.techcourse.myblog.web;

import com.techcourse.myblog.domain.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
}
