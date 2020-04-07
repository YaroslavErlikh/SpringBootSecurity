package yaroslav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import yaroslav.model.User;
import yaroslav.service.interfaces.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/profile")
    public ModelAndView profile(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/profile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping(value = "/user/editProfile")
    public ModelAndView editProfile(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/editProfile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/user/editProfileFine")
    public ModelAndView editProfileFine(@ModelAttribute("username") String username, @ModelAttribute("password") String password, @AuthenticationPrincipal User user) {
        user.setUsername(username);
        user.setPassword(password);
        ModelAndView modelAndView = new ModelAndView();

        if (userService.userIsExist(user)) {
            if (!userService.getUserByUsername(username).getId().equals(user.getId())) {
                modelAndView.addObject("message", "Имя занято");
                modelAndView.setViewName("/user/editProfile");
                return modelAndView;
            }
        }
        modelAndView.setViewName("redirect:/user/profile");
        userService.editUser(user);
        return modelAndView;
    }

    @GetMapping(value = "/user/deleteProfile")
    public ModelAndView deleteProfile(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/deleteProfile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/user/deleteProfile")
    public ModelAndView deleteUser(@ModelAttribute("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/logout");
        userService.deleteUser(id);
        return modelAndView;
    }
}
