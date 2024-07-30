package com.scm.controllers;

import com.scm.Entities.User;
import com.scm.Exception.ResourceNotFoundException;
import com.scm.Helper.Message;
import com.scm.Helper.MessageType;
import com.scm.Repo.UserRepo;
import com.scm.forms.UserForm;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    private UserRepo userRepo;
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");

        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Learn Code With Durgesh");
        model.addAttribute("githubRepo", "https://github.com/learncodewithdurgesh/");
        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        return "about";
    }


    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }


    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }



    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,
            HttpSession session) {
        log.info("Processing registration");
      log.info("{}",userForm);

        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (this.userRepo.existsByEmail(userForm.getEmail())) {
            Message message = Message.builder().content("Email Already In Use").type(MessageType.red).build();
            session.setAttribute("message", message);
            return "redirect:/register";
        } if (this.userRepo.existsByPhoneNumber(userForm.getPhoneNumber())) {
            Message message = Message.builder().content("This Phone Number is  Already In Use").type(MessageType.red).build();
            session.setAttribute("message", message);
            return "redirect:/register";
        }
            User user = new User();
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setAbout(userForm.getAbout());
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setEnabled(false);
            user.setProfilePic(
                    "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");

            User savedUser = userService.saveUser(user);
            System.out.println("user saved :");
            Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
            session.setAttribute("message", message);
            return "redirect:/register";

    }


}
