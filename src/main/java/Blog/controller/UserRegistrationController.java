package Blog.controller;

import javax.validation.Valid;

import Blog.model.User;
import Blog.service.UserService;
import Blog.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@SessionAttributes("user")
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user")
            @Valid UserRegistrationDto userDto,
            BindingResult result){

        User existing = userService.findByUsername(userDto.getUsername());
        if(existing!=null){
            result.rejectValue("username", null,
                    "There is already an account registered with that username");
            return "redirect:/registration?error";
        }
        userService.save(userDto);
        return "redirect:/registration?success";
    }




//    @Autowired
//    public SignupController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/signup")
//    public String getRegisterForm(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "register_form";
//    }
//
////    @PostMapping("/register")
////    public User signup(@RequestBody User user){
////        return userService.saveUser(user);
////    }
////}
//
//    @PostMapping("/register")
//    public String registerNewUser(@ModelAttribute User user, BindingResult bindingResult, SessionStatus sessionStatus) {
//        if (userService.findbyUsername(user.getUsername()).isPresent()) {
//            bindingResult.rejectValue("username", "error.username", "Username is already registered try other one or go away");
//            System.err.println("Username already taken error message");
//        }
//        if (bindingResult.hasErrors()) {
//            System.err.println("New user did not validate");
//            return "registerForm";
//        }        // Persist new blog user
//        this.userService.saveUser(user);
//        return "redirect:/";
//    }
}