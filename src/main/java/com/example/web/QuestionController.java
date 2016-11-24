package com.example.web;

import com.example.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

/**
 * Created by 강홍구 on 2016-11-24.
 */
@Controller
public class QuestionController {
    private ArrayList<Question> questions = new ArrayList<>();

    @PostMapping("/questions")
    // data 가 json 일때..
    public String create(Question question) {
        System.out.println(question);

        questions.add(question);

        return "redirect:/";
    }

    @GetMapping("/")
    public String qnaList(Model model) {
        model.addAttribute("questions", questions);

        return "/qna/index"; // templates 에 매핑된곳에 간다
    }

    @GetMapping("/qna/form")
    public String getQnaForm(Model model) {
        model.addAttribute("questions", questions);

        return "/qna/form"; // templates 에 매핑된곳에 간다
    }
}
