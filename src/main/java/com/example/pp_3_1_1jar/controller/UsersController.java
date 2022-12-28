package com.example.pp_3_1_1jar.controller;

import com.example.pp_3_1_1jar.model.User;
import com.example.pp_3_1_1jar.service.UserService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class UsersController {

    private final UserService userService;

    // @Autowired  since spring 4.3 no longer need to specify an explicit injection annotation in such a single-constructor scenario
    public UsersController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "main";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "new";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()){
            return "edit";
        }
        userService.updateUserById(id, user);
        return  "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return  "redirect:/";
    }
}
