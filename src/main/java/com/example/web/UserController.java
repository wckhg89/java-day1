package com.example.web;

import com.example.model.User;
import com.example.model.UserRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by 강홍구 on 2016-11-24.
 */

// 최근에는 xml 보다는 annotation 기반으로 설정을 해준다.
@Controller
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

    @GetMapping("/login")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User dbUser = userRepository.findByUserId(userId);

        // else는 안쓰는게 좋다 (가독성)

        // 예외처리 먼저하고
        if (dbUser == null) {
            return "redirect:/users/login";
        }

        if(!dbUser.matchPassword(password)) {
            return "redirect:/users/login";
        }

        // 로직 들어가자
        session.setAttribute("loginUser", dbUser);


        return "redirect:/";
    }

    @GetMapping("/form")
    public String getUserForm() {

        return "/user/form";
    }

    @GetMapping("/{id}/form")
    public String updateForm (@PathVariable Long id, Model model, HttpSession session) {

        Object sessionUser = session.getAttribute("loginUser");

        if (sessionUser == null) {
            return "redirect:/users/login";
        }
        User loginUser = (User) sessionUser;

        if (!loginUser.matchId(id)) {
            throw new IllegalStateException("다른 사람의 정보를 수정 할 수 없습니다.");
        }

        model.addAttribute("user", userRepository.findOne(id));

        return "user/updateForm";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable Long id, User user, HttpSession session) {

        Object sessionUser = session.getAttribute("loginUser");

        if (sessionUser == null) {
            return "redirect:/users/login";
        }
        User loginUser = (User) sessionUser;

        if (!loginUser.matchId(id)) {
            throw new IllegalStateException("다른 사람의 정보를 수정 할 수 없습니다.");
        }

        // userRepository.save(user); : 전체 필드를 업데이트 치기 때문에 좋은 습관이 아니다
                                     // 실제 개발에서는 사실 일부만 업데이트 되는 경우가 대다수다.
                                     // 불필요하게 쓰지 않는 필드까지 클라이언트까지 전달해야 한다.

        // 수정을 할때에는 조회를 해서 필요한 필드만 업데이트 해줘야 좀 더 안전하게 관리할 수 있다.
        // 따라서 반드시 이렇게 해주는게 좋다.
        User dbUser = userRepository.findOne(id);
        dbUser.update(user); // 객체지향 : 객체에게 해야할 일을 위임해줘야 가독성이 훨씬 좋아진다.
        userRepository.save(dbUser);

        return "redirect:/users";
    }

    


}
