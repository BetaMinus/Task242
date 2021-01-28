package controllers;

import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.AppService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppService appService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String admin() {
        return "admin";
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("usersList", appService.readAllUsers());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String read(@PathVariable("id") long id, Model model){
        model.addAttribute("user", appService.readUser(id));
        return "read";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/users/create")
    public String create(@ModelAttribute("user") User user, HttpServletRequest request) {
        user.setPassword(passwordEncoder.encode(request.getParameter("password")));

        String[] userRoles = request.getParameterValues("userRoles");
        List<Role> list = new ArrayList<>();
        for(String role: userRoles) {
            list.add(appService.findRoleByName(role));
        }
        user.setUserRoles(list);
        appService.createUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", appService.readUser(id));
        return "edit";
    }

    @PostMapping("/users/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id, HttpServletRequest request) {

        if (request.getParameter("login").isEmpty()) {
            user.setLogin(appService.readUser(id).getLogin());
        }

        if (request.getParameter("password").isEmpty()) {
            user.setPassword(appService.readUser(id).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(request.getParameter("password")));
        }

        String[] userRoles = request.getParameterValues("userRoles");
        if (userRoles == null) {
            user.setUserRoles(appService.readUser(id).getUserRoles());
        } else {
            List<Role> list = new ArrayList<>();
            for (String role: userRoles) {
                list.add(appService.findRoleByName(role));
            }
            user.setUserRoles(list);
        }
        appService.updateUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        appService.deleteUser(appService.readUser(id));
        return "redirect:/admin/users";
    }
}
