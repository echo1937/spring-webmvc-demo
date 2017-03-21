package cn.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Eric on 3/21/17.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(value = "/login")
    public String login() throws Exception {

        return "login";
    }

    @RequestMapping(value = "/submit")
    public String submit(String username, String pwd, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(username);
        if (username != null) {

            session.setAttribute("username", username);
        }

        return "redirect:/list";
    }
}

