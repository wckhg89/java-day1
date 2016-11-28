package com.example.web;

import com.example.model.Question;
import com.example.model.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 강홍구 on 2016-11-24.
 */
@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    // data 가 json 일때..
    public String create(Question question) {
        questionRepository.save(question);

        return "redirect:/questions";
    }

    @GetMapping("")
    public String qnaList(Model model) {
        model.addAttribute("questions", questionRepository.findAll());

        return "/qna/index"; // templates 에 매핑된곳에 간다
    }

    @GetMapping("/form")
    public String getQnaForm() {
        return "/qna/form"; // templates 에 매핑된곳에 간다
    }
}
