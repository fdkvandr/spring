package com.corp.spring.http.controller;

import com.corp.spring.dto.UserReadDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/v1")
@SessionAttributes({"user"}) // Устанавливаем этот ключ в SessionScope
public class GreetingController {

    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().setAttribute("ivan", new UserReadDto(1L, "Ivan")); // Так мы делали раньше в SessionScope через объект сессии
        modelAndView.setViewName("greeting/hello");
        modelAndView.addObject("user", new UserReadDto(1L, "Ivan")); // Так делаем сейчас
        return modelAndView;
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView, HttpServletRequest request,
            @RequestParam Integer age,
            @RequestHeader String accept,
            @CookieValue("JSESSIONID") String jsessionid,
            @PathVariable Integer id) {
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    @GetMapping("/by")
    public ModelAndView bye(@SessionAttribute("user") UserReadDto user, ModelAndView modelAndView) {
        // request.getSession().getAttribute("user")
        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }
}
