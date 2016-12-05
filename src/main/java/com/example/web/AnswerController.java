package com.example.web;

import com.example.model.Answer;
import com.example.model.AnswerRepository;
import com.example.model.Question;
import com.example.model.QuestionRepository;
import com.example.model.User;
import com.example.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by kanghonggu on 2016-12-05.
 */
@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("")
    public String createAnswer(Answer answer, @PathVariable Long questionId, HttpSession session) {
        Object sessionUser = session.getAttribute("loginUser");

        if (sessionUser == null) {
            return "redirect:/users/login";
        }

        User loginUser = (User) sessionUser;
        Question question = questionRepository.findOne(questionId);

        if (question == null) {
            return "redirect:/";
        }

        answer.setUser(loginUser);
        answer.setQuestion(question);

        answerRepository.save(answer);

        return "redirect:/questions/" + questionId;
    }

    @DeleteMapping("/{id}")
    public String deleteAnswer (@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
        Question question = questionRepository.findOne(questionId);

        if (question == null) {
            return "redirect:/";
        }

        Object sessionUser = session.getAttribute("loginUser");

        if (sessionUser == null) {
            return "redirect:/questions/" + questionId;
        }

        User dbUser = question.getUser();
        User loginUser = (User) sessionUser;


        if (!loginUser.matchId(dbUser.getId() )) {
            throw new IllegalStateException("다른 사람의 글을 삭제 할 수 없습니다.");
        }


        Answer answer = answerRepository.findOne(id);
        answerRepository.delete(answer);

        return "redirect:/questions/" + questionId;
    }

}
