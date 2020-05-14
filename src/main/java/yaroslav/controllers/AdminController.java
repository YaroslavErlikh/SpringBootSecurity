package yaroslav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import yaroslav.model.User;
import yaroslav.model.role.Role;
import yaroslav.service.interfaces.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/getUsers")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/admin/getUserEdit/{id}")
    public ResponseEntity<User> getUsers(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/admin/getAllRoles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok(userService.getAllRoles());
    }

    @PostMapping("/admin/add")
    public ResponseEntity<Void> addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();

        if (!userService.userIsExist(user)) {
            modelAndView.setViewName("redirect:/admin");
            userService.addUser(user);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        modelAndView.addObject("message", "Имя занято");
        modelAndView.setViewName("/admin/addUser");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/edit")
    public ResponseEntity<Void> editUser(
            @ModelAttribute("id") Long id,
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            @ModelAttribute("email") String email,
            @RequestParam("roles") String[] roles
    ) {
        User user = userService.getUserById(id);
        user.setUsername(username);
        user.setPassword(password);

        Set<Role> rolesNew = new HashSet<>();

        for (String role : roles) {
            if (role.equals("2")) {
                rolesNew.add(new Role(2L, "ROLE_ADMIN"));
            }
            if (role.equals("1")) {
                rolesNew.add(new Role(1L, "ROLE_USER"));
            }
        }

        user.setRoles(rolesNew);

        ModelAndView modelAndView = new ModelAndView();
        if (!userService.getUserByUsername(username).getId().equals(id) && userService.userIsExist(user)) {
            modelAndView.addObject("message", "Имя занято");
            modelAndView.setViewName("/admin/editUser");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        modelAndView.setViewName("redirect:/admin");
        userService.editUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
