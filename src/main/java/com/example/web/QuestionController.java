package com.example.web;

import com.example.model.Question;
import com.example.model.QuestionRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    public String create(Question question, HttpSession session) {
        // 방어 코드 : 로그인한 사용자만 질문하기 페이지로 갈 수 있다
        Object sessionUser = session.getAttribute("loginUser");

        if (sessionUser == null) {
            return "redirect:/users/login";
        }

        question.setUser((User)sessionUser);
        questionRepository.save(question);

        return "redirect:/";
    }

    @GetMapping("/form")
    public String getQnaForm(HttpSession session) {
        // 방어 코드 : 로그인한 사용자만 질문하기 페이지로 갈 수 있다
        Object sessionUser = session.getAttribute("loginUser");

        if (sessionUser == null) {
            return "redirect:/users/login";
        }


        return "/qna/form"; // templates 에 매핑된곳에 간다
    }

    @GetMapping("")
    public String qnaList(Model model) {

        model.addAttribute("questions", questionRepository.findAll());

        return "/qna/index"; // templates 에 매핑된곳에 간다
    }

    @GetMapping("/{id}")
    public String detailQna (@PathVariable Long id, Model model) {

        model.addAttribute("question", questionRepository.findOne(id));

        return "/qna/show";
    }
}
