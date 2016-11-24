package com.example.web;


import com.example.model.User;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by 강홍구 on 2016-11-24.
 */

// 최근에는 xml 보다는 annotation 기반으로 설정을 해준다.
@Controller
// @RequestMapping("/user")
public class UserController {
    private ArrayList<User> users = new ArrayList<>();
    // controller return 값 string
    // @RequestBody로 바꿔보기
    @PostMapping("/user/create") // spring 4.x 부터 생김
    public String create(User user) { // default로 requestParam (?)
        System.out.println(user);
        users.add(user);

        return "redirect:/users";
    }

    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users", users);

        return "/user/list"; // templates 에 매핑된곳에 간다
    }
}
