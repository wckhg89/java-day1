package com.example.web;

import com.example.model.User;
import com.example.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by 강홍구 on 2016-11-24.
 */

// 최근에는 xml 보다는 annotation 기반으로 설정을 해준다.
@Controller
@Transactional
@RequestMapping("/users")
public class UserController {
    private ArrayList<User> users = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    // controller return 값 string
    // @RequestBody로 바꿔보기
    @PostMapping("") // spring 4.x 부터 생김
    public String create(User user) { // default로 requestParam (?)
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "/user/list"; // templates 에 매핑된곳에 간다
    }

    @GetMapping("/form")
    public String getUserForm() {

        return "/user/form";
    }

    @GetMapping("/{id}/form")
    public String updateForm (@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findOne(id));

        return "user/updateForm";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, User user) {
        User dbUser = userRepository.findOne(id);
        dbUser.update(user);
        userRepository.save(dbUser);

        return "redirect:/users";
    }

    


}
