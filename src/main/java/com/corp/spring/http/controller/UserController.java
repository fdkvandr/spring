package com.corp.spring.http.controller;

import com.corp.spring.dto.UserCreateEditDto;
import com.corp.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users") // Укажем общий для всех путь
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // Заинжектим сервис чтобы получать инфу с него

    @GetMapping // По умолчанию, будем ходить на /users
    public String findAll(Model model) { // Инжектаем модель, чтобы установить туда аттрибут для рендеринга JSP
        // model.addAttribute("users", userService.findAll());
        // model.addAttribute("users", userService.findAll(filter));
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        // model.getAttribute("user", userService.findById(id));
        return "user/user";
    }

    @PostMapping// Создание сущности тоже по тому же пути /users
    public String create(UserCreateEditDto user) { // Тут неявно используется @ModelAttribute
        // userService.create(user); // Получим новосозданный айдишник
        return "redirect:/users/" + 25; // Тут потом сделаем на редирект странички с пользователем. Добавить айдишник
    }

    // @PutMapping("/{id}") // Обновление сущности
    @PostMapping("/{id}/update") // Временно сделаем @PostMapping пока не прошли @ResponseBody
    public String update(@PathVariable("id") Long id, UserCreateEditDto user) {
        // userService.update(id, user);
        return "redirect:/users/{id}"; // Spring хранит все эти @PathVariable в отдельной Map который мы потом также можем вставлять в return. Но тут есть два момента. Первое чт омы можем по этому айди не найти сущность. А второе - это то что мы не можем отправлять из нашей формы from PUT запрос. К сожалению, т.к. мы не используем JS, то мы можем использовать либо GET, либо POST.
    }

    // @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete") // Временно сделаем @PostMapping пока не прошли @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        // userService.delete(id);
        return "redirect:/users";
    }
}
