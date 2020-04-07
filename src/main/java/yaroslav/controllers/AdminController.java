package yaroslav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import yaroslav.model.User;
import yaroslav.model.role.Role;
import yaroslav.service.interfaces.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ModelAndView allUsers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/adminUsers");
        modelAndView.addObject("users", userService.getAllUsers());
        return modelAndView;
    }

    @GetMapping("/admin/add")
    public ModelAndView addPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/addUser");
        return modelAndView;
    }

    @PostMapping("/admin/add")
    public ModelAndView addUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();

        if (!userService.userIsExist(user)){
            modelAndView.setViewName("redirect:/admin");
            userService.addUser(user);
            return modelAndView;
        }

        modelAndView.addObject("message", "Имя занято");
        modelAndView.setViewName("redirect:/admin/addUser");
        return modelAndView;
    }

    @GetMapping("/admin/editUser/{id}")
    public ModelAndView editPage(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/editUser");
        modelAndView.addObject("userEditing", userService.getUserById(id));
        modelAndView.addObject("rolelist", userService.getAllRoles());
        return modelAndView;
    }

    @PostMapping("/admin/edit")
    public ModelAndView editUser(
            @ModelAttribute("id") Long id,
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            @ModelAttribute("email") String email,
            @RequestParam("roles") String[] roles
    ){
        User user = userService.getUserById(id);
        user.setUsername(username);
        user.setPassword(password);

        Set<Role> rolesNew = new HashSet<>();

        for (String role : roles) {
            if (role.equals("ROLE_ADMIN")){
                rolesNew.add(new Role(2L,"ROLE_ADMIN"));
            }
            if (role.equals("ROLE_USER")){
                rolesNew.add(new Role(1L, "ROLE_USER"));
            }
        }

        user.setRoles(rolesNew);

        ModelAndView modelAndView = new ModelAndView();
        if (!userService.getUserByUsername(username).getId().equals(id) && userService.userIsExist(user)){
            modelAndView.addObject("message", "Имя занято");
            modelAndView.setViewName("/admin/editUser");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/admin");
        userService.editUser(user);
        return modelAndView;
    }

    @PostMapping("/admin/deleteUser/{id}")
    public ModelAndView deletePage(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/deleteUser");
        modelAndView.addObject("user", userService.getUserById(id));
        return modelAndView;
    }

    @GetMapping("/admin/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.deleteUser(id);
        return modelAndView;
    }
}
