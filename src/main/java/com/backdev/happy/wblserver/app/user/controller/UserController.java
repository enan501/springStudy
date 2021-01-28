package com.backdev.happy.wblserver.app.user.controller;

import com.backdev.happy.wblserver.app.user.model.UserVO;
import com.backdev.happy.wblserver.app.user.service.UserService;
import com.backdev.happy.wblserver.enums.role.UserRole;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/user")
@Log4j2
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/loginView")
    public String loginView(){
        return "user/login";
    }


    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, RedirectAttributes redirectAttributes, @ModelAttribute UserVO userVO){
        log.error("@@@");
        log.info("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
        log.info(userVO.getUserEmail());
        log.info(userVO.getUserPw());
        String userPw = userVO.getUserPw();
        userVO = userService.findUserByUserEmail(userVO.getUserEmail());
        if(userVO == null || !passwordEncoder.matches(userPw, userVO.getUserPw())){
            redirectAttributes.addFlashAttribute("rsMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/user/loginView";
        }

        request.getSession().setAttribute("userVO", userVO);
        return "redirect:/index";
    }


    @GetMapping(value = "/init")
    public String createAdmin(@ModelAttribute UserVO userVO){
        userVO.setUserEmail("user@naver.com");
        userVO.setUserPw(passwordEncoder.encode("test"));
        userVO.setRole(UserRole.USER);
        if(userService.createUser(userVO) == null){
            log.error("Create Admin Error");
        }

        userVO.setUserEmail("admin@naver.com");
        userVO.setUserPw(passwordEncoder.encode("test"));
        userVO.setRole(UserRole.ADMIN);
        if(userService.createUser(userVO) == null){
            log.error("Create Admin Error");
        }
        return "redirect:/index";
    }

}
